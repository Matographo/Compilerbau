package de.htwsaar.compiler.Nodes;

public class ExpressionStatement extends Node {

    public ExpressionStatement(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        builder.append(children.get(0).generate());
        builder.append("\npop\n");
        return builder.toString();
    }
    
}
