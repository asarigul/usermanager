package net.sarigul.usermanager.core;

public class State {
	private String info;
	private Exception cause;
	
	public State() {
		this.info = "User Manager is up";
	}
	
	public State(Exception initException) {
		if(initException != null) {
			this.cause = initException;
			this.info = initException.getMessage();
		}
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Exception cause() {
		return cause;
	}
	
	public boolean isUp() {
		return cause == null;
	}
	
	public boolean isDown() {
		return ! isUp();
	}	
}
