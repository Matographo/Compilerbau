package de.htwsaar.compiler.Nodes;

public class NullValue extends Node {

    public NullValue(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        return "";
    }
    
}
