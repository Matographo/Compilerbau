package de.htwsaar.compiler.Nodes;


import de.htwsaar.compiler.*;

public class UnaryExpression extends Node {

	public UnaryExpression(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		builder.append(children.get(0).generate());
		builder.append(getExpressionType());
		builder.append("\n");
		return builder.toString();
	}

	private String getExpressionType() {
		String type = children.get(1).generate();
		return switch (type) {
			case "+" -> "load";
			case "-" -> "sub";
			case "*" -> "mul";
			case "/" -> "div";
			case "%" -> "mod";
			default -> "";
		};
	}
}
