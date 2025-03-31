package de.htwsaar.compiler.Nodes;

public class ArrayAccess extends Node {

    public ArrayAccess(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        String first = children.get(0).generate();
        String second = children.get(2).generate();
        StringBuilder builder = new StringBuilder();
        builder.append(first);
        builder.append(second);
        builder.append("add\n");
        builder.append("store\n");
	builder.append("pop\n");
        return builder.toString();
    }

}
