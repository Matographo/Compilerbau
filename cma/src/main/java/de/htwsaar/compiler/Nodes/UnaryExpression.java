package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.*;

public class UnaryExpression extends Node {

	public UnaryExpression(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		// postfix
		if (children.get(0).getValue().equals(CodeConstants.IDENTIFIER)) {
			builder.append(VarDecMap.getVarDec(children.get(0).generate()));
			builder.append("\n");
			builder.append("dup\n");
			builder.append("loadc 1\n");
			builder.append(getExpressionType(children.get(1).generate()));
			builder.append("storea ");
			builder.append(children.get(0).generate());
			builder.append("pop\n");
			// prefix
		} else if (children.get(1).getValue().equals(CodeConstants.IDENTIFIER)) {
			builder.append(VarDecMap.getVarDec(children.get(1).generate()));
			builder.append("\n");
			builder.append("loadc 1\n");
			builder.append(getExpressionType(children.get(0).generate()));
			builder.append("dup\n");
			builder.append("storea ");
			builder.append(children.get(1).generate());
			builder.append("pop\n");
			// negation
		} else if (children.get(1).getValue().equals(CodeConstants.EXPRESSION)) {
			builder.append(children.get(1).generate());
			builder.append(getExpressionType(children.get(0).generate()));
		}
		return builder.toString();
	}

	private String getExpressionType(String type) {
		return switch (type) {
			case "!" -> "not\n";
			case "-" -> "neg\n";
			case "+" -> "pos\n";
			case "--" -> "sub\n";
			case "++" -> "add\n";
			case "*" -> "load\n";
			default -> "";
		};
	}
}
