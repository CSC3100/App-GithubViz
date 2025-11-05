/**
 * This class represents a square with a file path and the number of lines of code.
 *
 * @author javiergs
 * @version 1.0
 */
public class Square {
	
	private String path;
	private int lines;
	
	public Square(String path, int lines) {
		this.path = path;
		this.lines = lines;
	}
	
	public int getLinesOfCode() {
		return lines;
	}
	
	public String getName() {
		return path.substring(path.lastIndexOf("/") + 1);
	}
	
}
