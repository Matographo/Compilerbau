package de.htwsaar.compiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import de.htwsaar.compiler.Nodes.ArrayAccess;
import de.htwsaar.compiler.Nodes.Assignment;
import de.htwsaar.compiler.Nodes.BinaryExpression;
import de.htwsaar.compiler.Nodes.Block;
import de.htwsaar.compiler.Nodes.BracketExpression;
import de.htwsaar.compiler.Nodes.Declaration;
import de.htwsaar.compiler.Nodes.Expression;
import de.htwsaar.compiler.Nodes.ExpressionStatement;
import de.htwsaar.compiler.Nodes.FunctionCall;
import de.htwsaar.compiler.Nodes.FunctionDefinitin;
import de.htwsaar.compiler.Nodes.GDeclaration;
import de.htwsaar.compiler.Nodes.IdentifyerLeft;
import de.htwsaar.compiler.Nodes.IfCondition;
import de.htwsaar.compiler.Nodes.LDeclaration;
import de.htwsaar.compiler.Nodes.LValue;
import de.htwsaar.compiler.Nodes.Node;
import de.htwsaar.compiler.Nodes.NullValue;
import de.htwsaar.compiler.Nodes.Number;
import de.htwsaar.compiler.Nodes.PointerAccess;
import de.htwsaar.compiler.Nodes.ReturnStatement;
import de.htwsaar.compiler.Nodes.Root;
import de.htwsaar.compiler.Nodes.Statement;
import de.htwsaar.compiler.Nodes.Toplevel;
import de.htwsaar.compiler.Nodes.UnaryExpression;
import de.htwsaar.compiler.Nodes.ValueNode;
import de.htwsaar.compiler.Nodes.While;

public class App {

	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		new App().start(args);
	}

	public void start(String[] args) {
		if (args.length == 0) {
			System.err.println("Usage: java -jar cma-1.0-SNAPSHOT.jar <inputfile.c> [<inputfile2.c> ...]");
			return;
		}

		for (String inputPath : args) {
			processFile(inputPath);
		}
	}

	private void processFile(String inputPath) {
		if (!inputPath.endsWith(".c")) {
			System.err.println("Input file must have .c extension: " + inputPath);
			return;
		}
		
		File inputFile = new File(inputPath);
		if (!inputFile.exists()) {
			System.err.println("Input file not found: " + inputPath);
			return;
		}
		
		// Extract base path without extension
		String basePath = inputPath.substring(0, inputPath.lastIndexOf(".c"));
		String tempFilePath = basePath;
		
		try {
			// 1. Parse the C file using tree-sitter command
			System.out.println("Parsing " + inputPath + " with tree-sitter...");
			List<String> parsedLines = parseWithTreeSitter(inputPath);
			
			if (parsedLines.isEmpty()) {
				throw new IOException("Error: tree-sitter produced no parseable output");
			}
			
			// 2. Write the parsed output to intermediate file (without extension)
			writeLinesToFile(tempFilePath, parsedLines);
						
			// 3. Read the intermediate file and build the tree
			List<String> lines = readLinesFromFile(tempFilePath);
			
			// 4. Build tree and generate the CMA code
			Node root = buildTree(lines);
			String generated = root.generate();
			
			// 5. Write the generated code to .cma file
			String cmaFilePath = basePath + ".cma";
			writeFileContent(cmaFilePath, generated);
			System.out.println("Created " + cmaFilePath + " file");
			
			// 6. Delete intermediate file
			File intermediateFile = new File(tempFilePath);
			if (intermediateFile.exists()) {
				intermediateFile.delete();
			}
			
			System.out.println("Process completed successfully.");
		} catch (Exception e) {
			System.err.println("Error processing file " + inputPath + ": " + e.getMessage());
			e.printStackTrace();
			
			// Clean up any intermediate files if they exist
			File intermediateFile = new File(tempFilePath);
			if (intermediateFile.exists()) {
				intermediateFile.delete();
			}
		}
	}

/**
	 * Parses a C file using tree-sitter command to get the CST representation
	 */
	private List<String> parseWithTreeSitter(String filePath) throws IOException, InterruptedException {
		List<String> output = new ArrayList<>();
		
		// Try to locate the tree-sitter-cma directory
		File treeParserDir = new File("tree-sitter-cma");
		if (!treeParserDir.exists() || !treeParserDir.isDirectory()) {
			// Try relative to parent directory
			treeParserDir = new File("../tree-sitter-cma");
			if (!treeParserDir.exists() || !treeParserDir.isDirectory()) {
				// Fallback to absolute path for development
				treeParserDir = new File("/home/safuan/Compilerbau/tree-sitter-cma");
				if (!treeParserDir.exists() || !treeParserDir.isDirectory()) {
					throw new IOException("Could not find tree-sitter-cma directory");
				}
			}
		}
		
		String treeParserCmd = "./node_modules/.bin/tree-sitter";
		
		try {
			// Convert the file path to absolute path
			File inputFile = new File(filePath);
			String absoluteFilePath = inputFile.getAbsolutePath();
			
			ProcessBuilder pb = new ProcessBuilder(treeParserCmd, "parse", "--cst", "--quiet", "--no-ranges", absoluteFilePath);
			pb.directory(treeParserDir);
			
			pb.redirectErrorStream(true);
			Process process = pb.start();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line;
			while ((line = reader.readLine()) != null) {
				// Remove ANSI color codes and other formatting chars
				line = line.replaceAll("\u001B\\[[;\\d]*m", "");
				if (!line.trim().startsWith("Parse:")) {
					output.add(line);
				}
			}
			
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				throw new IOException("tree-sitter parse failed with exit code: " + exitCode);
			}
		} catch (Exception e) {
			throw new IOException("Failed to parse C file with tree-sitter: " + e.getMessage(), e);
		}
		
		if (output.isEmpty()) {
			throw new IOException("tree-sitter produced no output for file: " + filePath);
		}
		
		return output;
	}
	
	/**
	 * Helper method to read a file's content as a string
	 */
	private String readFileContent(String filePath) throws IOException {
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		}
		return content.toString();
	}
	
	private List<String> readLinesFromFile(String path) {
		List<String> lines = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Skip any leading spaces for tree-sitter output
				if (line.startsWith("  ")) {
					lines.add(line.substring(2));
				} else {
					lines.add(line);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + path);
			e.printStackTrace();
		}

		return lines;
	}
	
	private void writeLinesToFile(String filePath, List<String> lines) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
		}
	}
	
	private void writeFileContent(String filePath, String content) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(content);
		}
	}

	private Node buildTree(List<String> lines) {
		if (lines.isEmpty()) {
			throw new IllegalArgumentException("Empty lines list; cannot build tree");
		}
		
		// Clean up input lines and skip warning/information lines
		List<String> cleanedLines = new ArrayList<>();
		boolean foundSourceFile = false;
		
		for (String line : lines) {
			// Skip null, empty lines, or warning/information lines
			if (line == null || line.trim().isEmpty() || 
			    line.contains("Warning:") || 
			    line.contains("Please run") ||
			    line.contains("configuration file")) {
				continue;
			}
			
			// Start collecting lines once we find the source_file node
			if (line.trim().equals("source_file")) {
				foundSourceFile = true;
			}
			
			if (foundSourceFile) {
				cleanedLines.add(line);
			}
		}
		
		if (cleanedLines.isEmpty()) {
			throw new IllegalArgumentException("No valid content in tree-sitter output");
		}
		
		Node root = new Root(cleanedLines.get(0).trim());
		Node current = root;
		int currentDepth = 0;
		
		for (int i = 1; i < cleanedLines.size(); i++) {
			String line = cleanedLines.get(i);
			
			if (line == null || line.trim().isEmpty()) {
				continue;
			}
			
			currentDepth = countDepth(line);
			
			try {
				if (currentDepth > current.getDepth()) {
					// Child node
					Node newNode = createNode(line, current);
					current.addNode(newNode);
					current = newNode;
				} else if (currentDepth == current.getDepth()) {
					// Sibling node
					if (current.getParent() == null) {
						// Special case for root-level siblings
						Node newNode = createNode(line, null);
						root.addNode(newNode);
						current = newNode;
					} else {
						Node newNode = createNode(line, current.getParent());
						current.getParent().addNode(newNode);
						current = newNode;
					}
				} else {
					// Go up to correct level
					while (current != null && current.getDepth() > currentDepth) {
						current = current.getParent();
					}
					
					if (current == null) {
						current = root;
					}
					
					// Check if we need a parent node
					if (current.getParent() == null) {
						// We're at the root level
						Node newNode = createNode(line, null);
						root.addNode(newNode);
						current = newNode;
					} else {
						Node parentNode = current.getParent();
						Node newNode = createNode(line, parentNode);
						parentNode.addNode(newNode);
						current = newNode;
					}
				}
			} catch (Exception e) {
				System.err.println("Error processing line: " + line + " at depth " + currentDepth);
				System.err.println("Current node: " + current);
				e.printStackTrace();
				// Continue with next line, trying to recover
			}
		}
		return root;
	}

	private Node createNode(String line, Node parent) {
		line = line.trim();
		
		// Handle nodes with field labels which might include `: `
		if (line.contains(": ")) {
			line = line.substring(line.indexOf(": ") + 2);
		}
		
		// Handle backtick-quoted values like `int` or `5`
		if (line.startsWith("`") && line.endsWith("`") && line.length() >= 2) {
			// This is a literal value like `5` or `x`
			String value = line.substring(1, line.length() - 1);
			return new ValueNode(value, parent);
		}
		
		// Handle quoted strings like "=" or ";"
		if (line.startsWith("\"") && line.endsWith("\"") && line.length() >= 2) {
			// This is a token like "=" or ";"
			return new ValueNode(line.substring(1, line.length() - 1), parent);
		}
		
		return switch (line) {
			case CodeConstants.DECLARATION -> new Declaration(line, parent);
			case CodeConstants.ASSIGNMENT -> new Assignment(line, parent);
			case CodeConstants.BINARY_EXPRESSION -> new BinaryExpression(line, parent);
			case CodeConstants.BRACKET_EXPRESSION -> new BracketExpression(line, parent);
			case CodeConstants.EXPRESSION -> new Expression(line, parent);
			case CodeConstants.IDENTIFIER -> new IdentifyerLeft(line, parent);
			case CodeConstants.ROOT -> new Root(line);
			case CodeConstants.NUMBER -> new Number(line, parent);
			case CodeConstants.STATEMENT -> new Statement(line, parent);
			case CodeConstants.TYPE -> new NullValue(line, parent);
			case CodeConstants.UNARY_EXPRESSION -> new UnaryExpression(line, parent);
			case CodeConstants.FUNCTION_DEFINITION -> new FunctionDefinitin(line, parent);
			case CodeConstants.FUNCTION_CALL -> new FunctionCall(line, parent);
			case CodeConstants.PARAM_LIST -> new NullValue(line, parent);
			case CodeConstants.TOP_LEVEL -> new Toplevel(line, parent);
			case CodeConstants.BLOCK -> new Block(line, parent);
			case CodeConstants.L_DECLARATION -> new LDeclaration(line, parent);
			case CodeConstants.IF_CONDITION -> new IfCondition(line, parent);
			case CodeConstants.IF -> new ValueNode(line, parent);
			case CodeConstants.ELSE -> new ValueNode(line, parent);
			case CodeConstants.G_DECLARATION -> new GDeclaration(line, parent);
			case CodeConstants.WHILE -> new While(line, parent);
			case CodeConstants.RETURN_STATEMENT -> new ReturnStatement(line, parent);
			case CodeConstants.POINTER_ACCESS -> new PointerAccess(line, parent);
			case CodeConstants.LOOP_BLOCK -> new Block(line, parent);
			case CodeConstants.L_VALUE -> new LValue(line, parent);
			case CodeConstants.EXPRESSION_STATEMENT -> new ExpressionStatement(line, parent);
			case CodeConstants.ARRAY_ACCESS -> new ArrayAccess(line, parent);
			default -> new ValueNode(line, parent);
		};
	}

	private int countDepth(String line) {
		int depth = 0;
		while (line.charAt(depth) == ' ') {
			depth++;
		}
		return depth / 2;
	}

}