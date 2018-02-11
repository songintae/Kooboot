package kooboot.user.domain;

import kooboot.user.domain.status.Status;
import kooboot.user.domain.status.StatusCode;
import kooboot.util.Constant;

public class User {
	
	private String userKey;
	private Status status;
	private UserData reqUserData;
	private String lastReqTime;
	
	public User(){
		
	}
	
	public User(String userKey, Status status, UserData reqUserData) {
		this.userKey = userKey;
		this.status = status;
		this.reqUserData = reqUserData;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
	public StatusCode getStatusCode(){
		return this.status.getStatusCode();
	}
	public String getSubStatusValue(){
		return this.status.getSubStatus().getStatusValue();
	}
	
	public void setStatus(StatusCode statusCode){
		this.status = new Status(statusCode);
	}
	
	public void setSubStatus(String subStatusValue) throws AssertionError{
		this.status.setSubStatus(subStatusValue);
	}
	
	public UserData getReqUserData() {
		return reqUserData;
	}
	public void setReqUserData(UserData reqUserData) {
		this.reqUserData = reqUserData;
	}
	public String getLastReqTime() {
		return lastReqTime;
	}
	public void setLastReqTime(String lastReqTime) {
		this.lastReqTime = lastReqTime;
	}
	
	public void setUserStatus(){
		switch(getReqUserData().getContents()){
			case Constant.INIT_KEYWORD :
				setStatus(StatusCode.INIT);
				break;
			case Constant.INIT_KEYBOARD_BUTTON_ONE :
				setStatus(StatusCode.TRANSLATE);
				break;
			case Constant.INIT_KEYBOARD_BUTTON_TWO : 
				setStatus(StatusCode.SEARCH);
				break;
		}
	}
	


}
