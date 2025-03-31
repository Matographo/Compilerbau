package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.CodeConstants;
import de.htwsaar.compiler.VarDecMap;

public class IfCondition extends Node {

    public IfCondition(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        String endAdress = VarDecMap.getJumpAdress();
        builder.append(children.get(2).generate());
        builder.append(getAllInnerConditions(endAdress));

        builder.append(endAdress);
        builder.append(":\n");

        return builder.toString();
    }

    private String getAllInnerConditions(String endAdress) {
        StringBuilder builder = new StringBuilder();
        String nextJumpAdress = null;
        if (children.size() > 5 && children.get(5).getValue().equals(CodeConstants.ELSE))
            nextJumpAdress = VarDecMap.getJumpAdress();

        builder.append("jumpz ");
        builder.append(nextJumpAdress != null ? nextJumpAdress : endAdress);
        builder.append("\n");
        builder.append(children.get(2).generate());
        if(nextJumpAdress == null) {
            return builder.toString();
        }

        for (int i = 5; i < children.size(); i++) {
            Node node = children.get(i);
            if (node.getValue().equals(CodeConstants.ELSE)) {
                builder.append(nextJumpAdress);
                builder.append(":\n");
                if (children.get(i + 1).getValue().equals(CodeConstants.IF)) {
                    builder.append(children.get(i + 3).generate()); // condition
                    builder.append("jumpz ");
                    if (i + 6 < children.size() && children.get(i + 6).getValue().equals(CodeConstants.ELSE)) {
                        nextJumpAdress = VarDecMap.getJumpAdress();
                        builder.append(nextJumpAdress);
                    } else {
                        builder.append(endAdress);
                    }
                    builder.append(children.get(i + 5).generate());
                    i += 5;
                } else {
                    builder.append(children.get(i + 1).generate());
                }
            }
        }

        return builder.toString();
    }

}
