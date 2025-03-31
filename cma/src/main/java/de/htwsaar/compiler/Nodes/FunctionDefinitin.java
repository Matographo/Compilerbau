package de.htwsaar.compiler.Nodes;

import java.util.ArrayList;
import java.util.List;

import de.htwsaar.compiler.*;

public class FunctionDefinitin extends Node {

    public FunctionDefinitin(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        StringBuilder builder = new StringBuilder();
        String adress = VarDecMap.getFunctionAdress(children.get(1).generate(), getAllVar());
        builder.append(adress);
        builder.append(":\n");
        builder.append("enter ");
        int localVar = getLocalVarCount();
        builder.append(calcStackSize(localVar));
        builder.append("\n");
        builder.append(getAlloc());

        builder.append(children.get(children.size() - 1).generate());

        builder.append("return\n");
        return builder.toString();
    }

    private int calcStackSize(int localVar) {
        int size = 4;
        if (!children.get(0).getValue().equals("void")) {
            size += 1;
        }
        size += localVar;
        return size;
    }

    private int getLocalVarCount() {
        int count = 0;
        for (Node child : children) {
            if (child instanceof LDeclaration) {
                count++;
            }
        }
        return count;
    }

    private int getParamSize() {
        int size = 0;
        if (children.get(3).getValue().equals(CodeConstants.PARAM_LIST)) {
            for (Node child : children.get(3).getChildren()) {
                if (child.getValue().equals(CodeConstants.TYPE)) {
                    size++;
                }
            }
        }
        return size;
    }

    private String getAlloc() {
        StringBuilder builder = new StringBuilder();
        builder.append("alloc ");
        builder.append(getAllVar().size());
        builder.append("\n");
        return builder.toString();

    }

    private String getReturn() {
        if (!children.get(0).getChildren().get(0).getValue().equals("\"void\"")) {
            return "storer -3\nreturn\n";
        }
        return "return\n";
    }

    private List<String> getAllVar() {
        List<String> vars = new ArrayList<>();
        Node paramList = children.get(3);
        if (paramList.getValue().equals(CodeConstants.PARAM_LIST)) {
            for (Node child : paramList.getChildren()) {
                if (child.getValue().equals(CodeConstants.IDENTIFIER)) {
                    vars.add(0, child.generate());
                }
            }
        }
        paramList = children.get(children.size() - 1);
        if (paramList.getValue().equals(CodeConstants.BLOCK)) {
            for (Node child : paramList.getChildren()) {
                if (child.getValue().equals(CodeConstants.STATEMENT) && child.getChildren().get(0).getValue().equals(CodeConstants.L_DECLARATION)) {
                    for (Node c : child.getChildren().get(0).getChildren()) {
                        for(Node cc : c.getChildren()) {
                            if (cc.getValue().equals(CodeConstants.IDENTIFIER)) {
                                vars.add(cc.generate());
                            }
                        }
                    }
                }
            }
        }
        return vars;
    }

}
