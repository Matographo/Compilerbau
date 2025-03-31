package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.CodeConstants;
import de.htwsaar.compiler.VarDecMap;

public class LValue extends Node {

    public LValue(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        switch (children.get(0).getValue()) {
            case CodeConstants.IDENTIFIER:
                builder.append(VarDecMap.getVarDec(children.get(0).generate()));
                builder.append("\n");
                break;
            case CodeConstants.POINTER_ACCESS:
                builder.append(children.get(0).generate());
                break;
            case CodeConstants.ARRAY_ACCESS:
                builder.append(children.get(0).generate());
            default:
                builder.append(children.get(0).generate());
                break;
        }
        return builder.toString();
    }

}
