package de.htwsaar.compiler;

import de.htwsaar.compiler.Nodes.*;
import de.htwsaar.compiler.Nodes.Number;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		System.out.println(args.length + " arguments");
		// args = new String[] { "/home/leodora/Documents/Dev/Uni/Compilerbau/Compilerbau/tree-sitter-cma/example1" };
		new App().start(args);
	}

	public void start(String[] args) {
		if (args.length == 0) {
			System.err.println("You can just pass one argument");
			return;
		}
		List<String> lines = readLines(args);
		Node root = buildTree(lines);
		String generated = root.generate();
		writeLines(args[0] + ".cma", generated);
	}

	private List<String> readLines(String[] args) {
		String path = args[0];
		List<String> lines = new ArrayList<>();

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));

			String line;
			while ((line = reader.readLine()) != null) {
				if(line.trim().isEmpty()) continue;
				lines.add(line.substring(2));
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines;
	}

	private void writeLines(String path, String lines) {
		System.out.println(lines);
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(path));

			writer.write(lines);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Node buildTree(List<String> lines) {
		Node root = new Root(lines.get(0).trim());
		Node current = root;
		int currentDepth = 0;
		String line = lines.get(0);

		for (int i = 1; i < lines.size(); i++) {
			line = lines.get(i);
			currentDepth = countDepth(line);
			if (currentDepth > current.getDepth()) {
				Node newNode = createNode(line, current);
				current.addNode(newNode);
				current = newNode;
			} else if (currentDepth == current.getDepth()) {
				Node newNode = createNode(line, current.getParent());
				current.getParent().addNode(newNode);
				current = newNode;
			} else {
				while (current.getDepth() > currentDepth) {
					current = current.getParent();
				}
				Node parentNode = current.getParent();
				Node newNode = createNode(line, parentNode);
				parentNode.addNode(newNode);
				current = newNode;
			}
		}
		return root;

	}

	private Node createNode(String line, Node parent) {
		line = line.trim();
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
