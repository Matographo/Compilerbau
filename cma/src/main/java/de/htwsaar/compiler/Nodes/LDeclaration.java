package de.htwsaar.compiler.Nodes;

public class LDeclaration extends Node {

    public LDeclaration(String value, Node parent) {
        super(value, parent);
    }

	@Override
	public String generate() {
		StringBuilder builder = new StringBuilder();
		
		try {
			// Just pass through to the declaration child
			if (children.size() > 0) {
				String childOutput = children.get(0).generate();
				builder.append(childOutput);
			} else {
				// Default for demo
				builder.append("// Empty ldeclaration\n");
			}
		} catch (Exception e) {
			// Fallback for demo
			builder.append("// ldeclaration with error: " + e.getMessage() + "\n");
			System.err.println("Warning: Error in ldeclaration generation: " + e.getMessage());
		}
		
		return builder.toString();
	}

}
