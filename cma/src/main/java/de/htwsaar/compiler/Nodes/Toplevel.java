package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.Nodes.Node;

public class Toplevel extends Node {
    
    public Toplevel(String value, Node parent) {
        super(value, parent);
    }
    
    @Override
    public String generate() {
        return children.get(0).generate() + "\n";
    }
    
}
