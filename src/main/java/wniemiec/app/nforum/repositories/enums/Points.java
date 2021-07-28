package wniemiec.app.nforum.repositories.enums;

public enum Points {
	
	NEW_TOPIC(10),
	NEW_COMMENT(3);

	private int value;
	
	Points(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
