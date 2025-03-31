package de.htwsaar.compiler.Nodes;

public class ReturnStatement extends Node {

    public ReturnStatement(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        builder.append(children.get(1).generate());
        builder.append("storer -3\n");
        return builder.toString();
    }
    
}
