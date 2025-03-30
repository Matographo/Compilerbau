public class IdentifyerLeft extends Node {

	public IdentifyerLeft(String value, Node parent) {
		super(value, parent);
	}

	@Override
	public String generate() {
		return VarDecMap.getGlobal(children.get(0).generate());
	}

}
