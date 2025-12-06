package enumeration;

public enum WebDriverType {
	LOCAL("local"),
	REMOTE("remote");
	
	private String description;
	
	WebDriverType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
