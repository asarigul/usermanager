package net.sarigul.usermanager.core;

public class State {
	private ApplicationException cause;
	
	public State(ApplicationException initException) {
		if(initException != null) {
			this.cause = initException;
		}
	}

	public ApplicationException cause() {
		return cause;
	}
	
	public boolean isUp() {
		return cause == null;
	}
	
	public boolean isDown() {
		return ! isUp();
	}	
}
