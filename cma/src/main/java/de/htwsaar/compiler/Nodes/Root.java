package de.htwsaar.compiler.Nodes;


import de.htwsaar.compiler.*;

public class Root extends Node {
    
    public Root(String value) {
        super(value);
    }
    
    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        for(Node child : children) {
            builder.append(child.generate());
        }
        return builder.toString();
    }
    
}
