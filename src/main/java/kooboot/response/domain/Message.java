package kooboot.response.domain;

public class Message {
	private String text;
	private MessageButton messageButton;
	private Photo photo;
	
	public Message(){
		
	}
	
	public Message(String text){
		this.text = text;
	}

	public Message(String text, MessageButton messageButton, Photo photo) {
		this.text = text;
		this.messageButton = messageButton;
		this.photo = photo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public MessageButton getMessageButton() {
		return messageButton;
	}

	public void setMessageButton(MessageButton messageButton) {
		this.messageButton = messageButton;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	
	
	
}
