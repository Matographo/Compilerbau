package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.*;

public class Expression extends Node {
	public Expression(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		if(children.get(0).value.equals(CodeConstants.NUMBER)) {
			builder.append("loadc ");
			builder.append(children.get(0).generate());
			builder.append("\n");
			return builder.toString();
		} else if (children.get(0).value.equals(CodeConstants.IDENTIFIER)) {
			builder.append(VarDecMap.getVarDec(children.get(0).generate()));
			builder.append("\n");
			return builder.toString();
		}
		return children.get(0).generate();
	}

}
