package kooboot.search.domain.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kooboot.search.domain.Document;
import kooboot.search.domain.Response;

public class WebResponse extends Response {
	
	
	public List<WebDocument> getDocuments(){
		return Collections.unmodifiableList((List)documents);
	}

	@Override
	protected Document createDocument() {
		// TODO Auto-generated method stub
		return new WebDocument();
	}

}
