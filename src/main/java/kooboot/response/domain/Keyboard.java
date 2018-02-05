package kooboot.response.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Keyboard {
	
	private String type;
	private List<String> buttons;
	
	public Keyboard(){
		type = "text";	//kakao api 에서 default 
	}
	
	public Keyboard(String type , List<String> buttons){
		this.type = type;
		this.buttons = new ArrayList<String>();
		this.initailizeButtons(buttons);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if("buttons".equals(type))
			this.buttons = new ArrayList<String>();
	}

	public List<String> getButtons() {
		return Collections.unmodifiableList(this.buttons);
	}

	public void initailizeButtons(List<String> buttons) {
		this.buttons.clear();
		for(String button : buttons){
			this.buttons.add(button);
		}
	}
	
	public void addButton(String button){
		this.buttons.add(button);
	}
	
	public void removeButton(int index){
		this.buttons.remove(index);
	}
	
	public void removeButtons(String arg){
		Iterator<String> itr = this.buttons.iterator();
		int idx = 0;
		while(itr.hasNext()){
			if(itr.next().equals(arg)){
				this.buttons.remove(idx);
			}
			idx++;
		}
	}
	
	
}
