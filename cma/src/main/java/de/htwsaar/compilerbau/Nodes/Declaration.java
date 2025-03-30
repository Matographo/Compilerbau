package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.*;

public class Declaration extends Node {

	public Declaration(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		builder.append(getExpression());
		builder.append("storea ");
		builder.append(children.get(1).generate());
		builder.append("\n");
		return builder.toString();
	}

	private String getExpression() {
		if (children.size() == 3)
			return "loadc 0\n";
		return children.get(3).generate();
	}
}
