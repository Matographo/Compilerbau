package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.VarDecMap;

public class While extends Node {

    public While(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        String startAdress = VarDecMap.getJumpAdress();
        String endAdress = VarDecMap.getJumpAdress();
        builder.append(startAdress + ":\n");
        builder.append(children.get(2).generate());
        builder.append("jumpz " + endAdress + "\n");
        builder.append(children.get(4).generate());
        builder.append("\n");
        builder.append("jump " + startAdress + "\n");
        builder.append(endAdress + ":\n");
        return builder.toString();
    }
    
}
