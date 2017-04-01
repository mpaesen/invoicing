/**
 * 
 */
package persistency;

/**
 * @author Mathy
 *
 */
public enum ArgIO {
	IN("Input", 0), OUT("Output", 1);
	private String argTitle;
	private int argValue;
	private ArgIO(String argTitle, int argValue) {
		this.argTitle = argTitle;
		this.argValue = argValue;
	}
	public String getArgTitle() {
		return argTitle;
	}
	public int getArgValue() {
		return argValue;
	}
	
}
