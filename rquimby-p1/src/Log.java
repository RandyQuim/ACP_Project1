import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A class to handle file input/output to log all database events
 * 
 * @author Randy Quimby
 * @version 1.0
 * 
 *          COP4027 Project#: 1 File Name: Log.java
 */
public class Log {
	/**
	 * The random access file to store database events
	 */
	private RandomAccessFile file;

	/**
	 * Constructs a Log object that is not associated with a file
	 */
	public Log() {
		this.file = null;
	}

	/**
	 * Opens the log file
	 * 
	 * @param fileName the name of the file
	 */
	public void open(String fileName) throws IOException {
		if (file != null) {
			file.close();
		}

		file = new RandomAccessFile(fileName, "rw");
	}

	/**
	 * Closes the log file
	 */
	public void close() throws IOException {
		if (file != null) {
			file.close();
		}

		file = null;
	}

	/**
	 * Writes database events to file (output)
	 * 
	 * @param string the string to write to file
	 */
	public void write(String string) throws IOException {
		file.writeUTF(string);
	}

	/**
	 * Reads database events written to file (input)
	 */
	public void readLog() {
		try {
			file.seek(0);
			
			while (true) {
				String output = file.readUTF();
				displayLog(output);
			}
		} catch (EOFException e) {
			System.out.println("No more data in file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Displays the log file to the console
	 * 
	 * @param output the output to display
	 */
	private void displayLog(String output) {
		System.out.println(output);
	}
	
	
}
