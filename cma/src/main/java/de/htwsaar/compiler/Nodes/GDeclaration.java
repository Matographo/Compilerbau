package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.CodeConstants;
import de.htwsaar.compiler.VarDecMap;

public class GDeclaration extends Node {

    public GDeclaration(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        Node id = children.get(0);
        for (Node child : id.getChildren()) {
            if (child.getValue().equals(CodeConstants.IDENTIFIER)) {
                id = child;
                break;
            }
        }
        StringBuilder builder = new StringBuilder();
        VarDecMap.declarateGlobalVar(id.generate());
        builder.append(generateAll());


        return builder.toString();
    }

    public String generateAll() {
        StringBuilder builder = new StringBuilder();
        builder.append("loadc 0\n");
        if (children.get(0).getChildren().get(2).generate().equals("=")) {
            builder.append(children.get(0).generate());
        } else if(children.get(0).getChildren().get(1).generate().equals("*")) {
            builder.append("loadc 1\n");
            builder.append("new\n");
            builder.append("storea ");
            builder.append(VarDecMap.getGlobal(children.get(0).getChildren().get(2).generate()));
            builder.append("\n");
        } else if(children.get(0).getChildren().get(1).getValue().equals(CodeConstants.IDENTIFIER)) {
            builder.append(children.get(0).getChildren().get(2).generate());
            builder.append("\n");
            builder.append("new\n");
            builder.append("storea ");
            builder.append(VarDecMap.getGlobal(children.get(0).getChildren().get(0).generate()));
            builder.append("\n");
        }
        return builder.toString();
    }

}
