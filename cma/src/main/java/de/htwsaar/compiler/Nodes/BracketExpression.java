package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.*;

public class BracketExpression extends Node {

	public BracketExpression(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		return children.get(1).generate();
	}

}
