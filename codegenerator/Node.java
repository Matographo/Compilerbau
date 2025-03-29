public abstract class Node {

	private Node parent;
	private List<Node> children;
	private String value;

	public Node(String value, Node parent) {
		this.value = value;
		this.parent = parent;
		children = new ArrayList<>();
	}

	public Node(String value) {
		this(null);
	}

	public Node getParent() {
		return parent;
	}

	public String getValue() {
		return value;
	}
	
	public void addNode(Node node) {
		children.add(node);
	}

	public int getDepth() {
		int depth = 0;
		Node current = this;
		while(current.parent != null) {
			depth++;
			current = current.parent;
		}
		return depth;
	}

	public List<Node> getChildren() {
		return children;
	}

	public abstract String generate();

}
