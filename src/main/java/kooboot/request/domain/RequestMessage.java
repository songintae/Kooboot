package kooboot.request.domain;

public class RequestMessage {
	String user_key;
	String type;	
	String content; 
	
	public RequestMessage(){
		
	}
	
	
	public RequestMessage(String user_key, String type, String content) {
		super();
		this.user_key = user_key;
		this.type = type;
		this.content = content;
	}


	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
