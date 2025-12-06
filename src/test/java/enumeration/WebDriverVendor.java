package enumeration;

public enum WebDriverVendor {
	CHROME("chrome"),
	FIREFOX("firefox"),
	EDGE("edge");
	
	private String description;
	
	WebDriverVendor(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
