package kooboot.search.domain.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kooboot.search.domain.Document;
import kooboot.search.domain.Response;

public class WebResponse extends Response {
	
	private List<WebDocument> documents = new ArrayList<WebDocument>();
	
	public void addDocument(WebDocument document){
		this.documents.add(document);
	}
	
	public List<WebDocument> getDocuments(){
		return Collections.unmodifiableList(this.documents);
	}

	@Override
	protected Document createDocument() {
		// TODO Auto-generated method stub
		return new WebDocument();
	}

	@Override
	protected void addDocument(Document arg) {
		// TODO Auto-generated method stub
		this.addDocument((WebDocument)arg);
		
	}
}
