import java.io.BufferedWriter;
import java.io.FileWriter;


public class Writer {


    private final int BUFFER_SIZE_WRITE;
    private BufferedWriter writer;
    private final String FILE_EXTENSION = ".cma";


    private String[] buffer;
    private int writerIndex = 0;

    public Writer(int bufferSize, String filePath) {
	this.BUFFER_SIZE_WRITE = bufferSize;
	buffer = new String[BUFFER_SIZE_WRITE];
	try {
	    this.writer = new BufferedWriter(new FileWriter(filePath + FILE_EXTENSION));
	} catch (Exception e) {
	    System.out.println("Error while opening the file");
	    throw new RuntimeException("Error while opening the file");
	}
    }

    public void writeLine(String line) {
	if (line != null) buffer[writerIndex++] = line;

    	if (writerIndex >= BUFFER_SIZE_WRITE) {
	    writerIndex = 0;
	    try {
	        for (int i = 0; i < BUFFER_SIZE_WRITE; i++) {
		    writer.write(buffer[i]);
	        }
	    } catch (Exception e) {
	        System.out.println("Error while writing the file");
	    }
        }
    }

    public void writeLastInput() {
	try {
	    for (int i = 0; i < writerIndex; i++) {
		writer.write(buffer[i]);
	    }
	} catch (Exception e) { 
	    System.out.println("Error while writing the file");
	}
    }


    public void close() {
	try {
	    writer.close();
	} catch (Exception e) {
	    System.out.println("Error while closing the file");
	}
    }
	
}
