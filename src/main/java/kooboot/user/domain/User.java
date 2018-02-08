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
		return this.getStatus().getStatusCode();
	}
	public String getSubStatusValue(){
		return this.getStatus().getSubStatus().getStatusValue();
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
				setStatus(new Status(StatusCode.INIT));
				break;
			case Constant.INIT_KEYBOARD_BUTTON_ONE :
				setStatus(new Status(StatusCode.TRANSLATE));
				break;
			case Constant.INIT_KEYBOARD_BUTTON_TWO : 
				setStatus(new Status(StatusCode.WEATHER));
				break;
		}
	}
	


}
