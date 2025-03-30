public class ValueNode extends Node {

    public ValueNode(String value, Node parent) {
        super(value, parent);
    }

    @Override
    public String generate() {
        return value.substring(1, value.length() - 1);
    }

}
