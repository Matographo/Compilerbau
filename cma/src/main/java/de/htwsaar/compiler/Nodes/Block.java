package de.htwsaar.compiler.Nodes;

public class Block extends Node {

    public Block(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i < children.size() - 1; i++) {
            builder.append(children.get(i).generate());
        }
        return builder.toString();
    }
    
}
