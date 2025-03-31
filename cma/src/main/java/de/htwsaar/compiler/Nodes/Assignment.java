package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.*;

import de.htwsaar.compiler.Nodes.Node;

public class Assignment extends Node {

	public Assignment(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		builder.append(getExpression());
		if(!children.get(0).getValue().equals("lvalue")){
			builder.append("storea ");
			builder.append(children.get(0).generate());
			builder.append("\n");
			builder.append("pop\n");
		} else {
			builder.append(children.get(0).generate());
		}
		return builder.toString();
	}

	private String getExpression() {
		if (children.size() == 3)
			return "";
		return children.get(2).generate();
	}

}
