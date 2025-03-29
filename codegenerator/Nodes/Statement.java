public class Statement extends Node {

	@Override
	public String generate() {
		return children.get(0).generate() + "\n";
	}
}
