package kooboot.response.domain;

public class ResponseMessage {
	
	private Message message;
	private Keyboard keyboard;
	
	public ResponseMessage() {
		
	}

	public ResponseMessage(Message message, Keyboard keyboard) {
		this.message = message;
		this.keyboard = keyboard;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}
	
}
