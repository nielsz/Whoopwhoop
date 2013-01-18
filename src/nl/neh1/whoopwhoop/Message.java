package nl.neh1.whoopwhoop;

public class Message {
	  private long id;
	  private String message;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getMessage() {
	    return message;
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