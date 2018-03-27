package kooboot.search.domain.kakaosearch.web;

import kooboot.search.domain.kakaosearch.Document;

public class WebDocument extends Document {
	private String datetime;
	private String contents;
	private String title;
	private String url;
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
