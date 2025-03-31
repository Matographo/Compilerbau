package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.*;


public class ValueNode extends Node {

    public ValueNode(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        if (value == null || value.isEmpty()) {
            return "";
        }
        
        // For quoted values (in backticks or double quotes), strip the quotes
        if ((value.startsWith("`") && value.endsWith("`") && value.length() >= 2) ||
            (value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2)) {
            return value.substring(1, value.length() - 1);
        }
        
        // For numeric values, return as is
        return value;
    }

}
