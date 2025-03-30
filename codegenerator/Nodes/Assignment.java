public class Assignment extends Node {

	public Assignment(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		builder.append(getExpression());
		builder.append("storea ");
		builder.append(children.get(0).generate());
		builder.append("\n");
		return builder.toString();
	}

	private String getExpression() {
		if (children.size() == 3)
			return "";
		return children.get(2).generate();
	}

}
