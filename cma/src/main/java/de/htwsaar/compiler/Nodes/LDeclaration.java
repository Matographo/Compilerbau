package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.CodeConstants;
import de.htwsaar.compiler.VarDecMap;
import de.htwsaar.compiler.Nodes.Node;

public class LDeclaration extends Node {

    public LDeclaration(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        return children.get(0).generate();
    }

}
