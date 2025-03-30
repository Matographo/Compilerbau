public class Statement extends Node {

	public Statement(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		builder.append(children.get(0).generate());
		builder.append(getPop());
		return children.get(0).generate() + "\n";
	}

	private String getPop() {
		String type = children.get(0).getValue();
		return switch (type) {
			case "expression" -> "pop\n";
			default -> "";
		};
	}
}
