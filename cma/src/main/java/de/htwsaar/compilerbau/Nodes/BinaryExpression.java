package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.*;

public class BinaryExpression extends Node {

	public BinaryExpression(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		builder.append(children.get(0).generate());
		builder.append(children.get(2).generate());
		builder.append(getExpressionType());
		builder.append("\n");
		return builder.toString();
	}

	private String getExpressionType() {
		String type = children.get(1).generate();
		return switch (type) {
			case "+" -> "add";
			case "-" -> "sub";
			case "*" -> "mul";
			case "/" -> "div";
			case "%" -> "mod";
			default -> "";
		};
	}
}
