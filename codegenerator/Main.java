
public class Main {

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
	new Main().start(args);
    }

    public void start(String[] args) {
	if (args.length == 0) {
	    System.err.println("You can just pass one argument");
	    return;
	}
	List<String> lines = readLines(args);
	Node root = buildTree(lines);
	List<String> generated = root.generate();
	writeLines(args[0] + ".cma", generated);
    }

    private List<String> readLines(String[] args) {
	String path = args[0];
	List<String> lines = new ArrayList<>();

	BufferedReader reader = new BufferedReader(new FileReader(path));

	String line;
	while ((line = reader.readLine()) != null) {
	    lines.add(line);
	}
	reader.close();
	
	return lines;
    }

    private void writeLines(String path, List<String> lines) {
	BufferedWriter writer = new BufferedWriter(new FileWriter(path));

	for (String line : lines) {
	    writer.write(line);
	    writer.newLine();
	}
	writer.close();
    }

    private Node buildTree(List<String> lines) {
	Node root = new Node(lines.get(0));
	Node current = root;
	int currentDepth = 0;

	for (int i = 1; i < lines.size(); i++) {
		currentDepth = countDepth(lines.get(i));
		if (currentDepth > current.getDepth()) {
			Node newNode = new Node(lines.get(i), current);
			current.addNode(newNode);
			current = newNode;
		} else if (currentDepth == current.getDepth()) {
			Node newNode = new Node(lines.get(i), current.getParent());
			current.getParent().addNode(newNode);
			current = newNode;
		} else {
			for (int j = 0; j < current.getDepth() - currentDepth; j++) {
				current = current.getParent();
			}
			Node parentNode = current.getParent(); 
			Node newNode = new Node(lines.get(i), parentNode);
			parentNode.addNode(newNode);
			current = newNode;
		}
	}
	return root;

    }

    private int countDepth(String line) {
	int depth = 0;
	while (line.charAt(depth) == ' ') {
	    depth++;
	}
	return depth / 2;
    }

}
