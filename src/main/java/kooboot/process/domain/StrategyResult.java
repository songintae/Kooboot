package kooboot.process.domain;

import kooboot.response.domain.ResponseMessage;
import kooboot.user.domain.User;

public class StrategyResult {
	private User user;
	private ResponseMessage responseMessage;
	
	public StrategyResult(){
		
	}
	
	public StrategyResult(User user, ResponseMessage responseMessage) {
		super();
		this.user = user;
		this.responseMessage = responseMessage;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}
}
