import java.io.BufferedReader;
import java.io.FileReader;


public class Reader {

    private final int BUFFER_SIZE_READ;


    private String[] readerBuffer;
    private int readerIndex = 0;

    BufferedReader reader;

    public Reader(int bufferSize, String filePath) {
	this.BUFFER_SIZE_READ = bufferSize;
	readerBuffer = new String[BUFFER_SIZE_READ];

	try {
	    this.reader = new BufferedReader(new FileReader(filePath));
	} catch (Exception e) {
	    System.out.println("Error while opening the file");
	    throw new RuntimeException("Error while opening the file");
	}
    }

    public void close() {
	try {
	    reader.close();
	} catch (Exception e) {
	    System.out.println("Error while closing the file");
	}
    }

    /**
     * Read a line from the file and return it. It has a buffer of BUFFER_SIZE_READ lines
     * @return the line read. Null if the end of the file is reached
     */
    public String readLine() {
	if (readerIndex >= BUFFER_SIZE_READ) {
	    readerIndex = 0;
	    String line;
	    int i = 0;
	    try {
		while ((line = reader.readLine()) != null && i < BUFFER_SIZE_READ) {
		    readerBuffer[i++] = line;
		}
	    } catch (Exception e) {
		System.out.println("Error while reading the file");
		return null;
	    }
	    if (i != BUFFER_SIZE_READ) {
	        readerBuffer[i] = null;
	    }
	}
	return readerBuffer[readerIndex++];
    }

}
