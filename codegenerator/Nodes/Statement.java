public class Statement extends Node {

	public Statement(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		return children.get(0).generate() + "\n";
	}
}
