package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.*;


public class ValueNode extends Node {

    public ValueNode(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        return value.substring(1, value.length() - 1);
    }

}
