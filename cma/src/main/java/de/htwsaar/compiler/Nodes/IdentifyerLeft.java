package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.*;

public class IdentifyerLeft extends Node {

	public IdentifyerLeft(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		return children.get(0).generate();
	}

}
