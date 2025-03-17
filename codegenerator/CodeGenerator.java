import java.util.LinkedList;
import java.util.Stack;

public class CodeGenerator {

	private Stack<String> stack;
	private int lastDepth;

	public CodeGenerator() {
		stack = new Stack<>();
	}

	public String getGenerated(String input) {
		int depth = getDepth(input);
		input = input.trim();
		if(stack.isEmpty() && depth == 0 && input.equals(CodeConstants.FILE_START)) {
			stack.push(input);
			lastDepth++;
			return null;
		}
		if(depth < lastDepth) {
			//closeOpenStacks(depth);
		}
		switch(stack.peek()) {
			case CodeConstants.FILE_START:
				fileStart(input);
				break;
			case CodeConstants.STATEMENT:
				statement(input);
				break;
			case CodeConstants.DECLARATION:
				declaration(input, depth);
				break;
		}
		return null;
	}

	private int getDepth(String input) {
		int depth = 0;
		while(input.charAt(depth) == ' ') {
			depth++;
		}
		return depth / 2;
	}

	private void fileStart(String input) {
		if(input.equals(CodeConstants.STATEMENT)) {
			stack.push(input);
			lastDepth++;
		} else {
			throw new RuntimeException("Invalid input. Expected statement, but got " + input);
		}
	}

	private void statement(String input) {
		switch(input) {
			case CodeConstants.DECLARATION:
				stack.push(input);
				lastDepth++;
				break;
			default:
				throw new RuntimeException("Invalid input. Expected declaration, but got " + input);
		}
	}

	private void declaration(String input, int depth) {
		if(depth == lastDepth) {
			stack.pop();
			lastDepth--;
		}
	}
}
