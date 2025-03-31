package de.htwsaar.compiler.Nodes;

import de.htwsaar.compiler.CodeConstants;
import de.htwsaar.compiler.VarDecMap;

public class FunctionCall extends Node {

    public FunctionCall(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        builder.append("loadc 0\n");
        builder.append("mark\n");
        builder.append(calcParam());
        builder.append("\n");
        builder.append("call ");
        builder.append(getParamSize());
        builder.append("\n");

        return builder.toString();
    }

    private String calcParam() {
        StringBuilder builder = new StringBuilder();
        Node paramList = children.get(2);
        if (paramList.getValue().equals(CodeConstants.PARAM_LIST)) {
            for (Node child : paramList.getChildren()) {
                if (child.getValue().equals(CodeConstants.EXPRESSION)) {
                    builder.append(child.generate());
                }
            }
        }
        return builder.toString();

    }

    private String getFunctionAdress() {
        String functionName = children.get(0).generate();
        return "_f" + functionName;
    }

    private String getFunctionName() {
        return children.get(0).generate();
    }

    private int getParamSize() {
        int size = 0;
        Node paramList = children.get(2);
        if (paramList.getValue().equals(CodeConstants.PARAM_LIST)) {
            for (Node child : paramList.getChildren()) {
                if (child.getValue().equals(CodeConstants.TYPE)) {
                    size++;
                }
            }
        }
        return size;
    }

}
