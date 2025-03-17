
public class Main {

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
	new Main().start(args);
    }

    public void start(String[] args) {
	if (args.length == 0) {
	    System.err.println("You can just pass one argument");
	    return;
	}
	for (String arg : args) {
	    Reader reader = new Reader(BUFFER_SIZE, arg);
	    Writer writer = new Writer(BUFFER_SIZE, arg);
	    CodeGenerator codeGenerator = new CodeGenerator();
	    String line;
	    while ((line = reader.readLine()) != null) {
		writer.writeLine(codeGenerator.getGenerated(line));
	    }
	    writer.writeLastInput();
	    reader.close();
	    writer.close();
	}
    }
}
