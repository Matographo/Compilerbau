public class Number extends Node {

	@Override
	public String generate() {
		return children.get(0).getValue().substring(1, children.get(0).getValue().length() - 1);
	}
}
