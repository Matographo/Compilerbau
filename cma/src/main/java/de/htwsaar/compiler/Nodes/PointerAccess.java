package de.htwsaar.compiler.Nodes;

public class PointerAccess extends Node {

    public PointerAccess(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        builder.append("loada ");
        builder.append(children.get(1).generate());
        builder.append("\n");
        builder.append("load");
        builder.append("\n");
        return builder.toString();
    }

}
