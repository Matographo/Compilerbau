public class Source extends Node {

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		for (Node child : getChildren()) {
			builder.append(child.generate());
		}
		return builder.toString();
	}
	
}
