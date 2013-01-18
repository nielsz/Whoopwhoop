package nl.neh1.whoopwhoop;

public class Message {
	
	private long id;
	private String message;
	private int gender;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setGender(int g) {
		gender = g;
	}

	public int getGender() {
		return gender;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}
	
	
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return message;
	}
} 