package kooboot.user.domain;

public class UserData {
	
	private String userKey;
	private String type;
	private String contents;
	
	public UserData(){};
	public UserData(String userKey,String type, String contents){
		this.userKey = userKey;
		this.type = type;
		this.contents = contents;
	}
	
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

}
