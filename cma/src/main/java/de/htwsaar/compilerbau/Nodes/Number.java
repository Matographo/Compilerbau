package de.htwsaar.compiler.Nodes;


import de.htwsaar.compiler.*;

public class Number extends Node {

	public Number(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		return children.get(0).generate();
	}
}
