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
		if (parent.getValue().equals(CodeConstants.G_DECLARATION)) {
			builder.append("storea ");
			builder.append(VarDecMap.getGlobal(children.get(1).generate()));
		} else {
			String funcName = parent.getParent().getParent().getParent().getChildren().get(1).generate();
			builder.append("loadc FP\n");
			builder.append("loadc ");
			builder.append(VarDecMap.getLocalVar(funcName, children.get(1).generate()));
			builder.append("\n");
			builder.append("add\n");
			builder.append("store\n");
		}
		// builder.append(children.get(1).generate());
		builder.append("\n");
		builder.append("pop\n");
		return builder.toString();
	}

	private String getExpression() {
		if (children.size() == 3)
			return "loadc 0\n";
		return children.get(3).generate();
	}
}
