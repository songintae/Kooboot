package kooboot.search.domain.book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kooboot.search.domain.Document;
import kooboot.search.domain.Response;
import kooboot.search.domain.keyword.KeywordDocument;
import kooboot.search.exception.ResponseParseException;


public class BookResponse extends Response{

	List<BookDocument> documents = new ArrayList<BookDocument>();
	
	public void addDocument(BookDocument document){
		documents.add(document);
	}
	
	public void remove(int index){
		this.documents.remove(index);
	}
	
	public List<BookDocument> getDocuments(){
		return Collections.unmodifiableList(this.documents);
	}
	
	public void initializeDocuments(List<BookDocument> documents){
		this.documents.clear();
		for(BookDocument document : documents){
			this.addDocument(document);
		}
	}
	
	@Override
	protected Document createDocument() {
		// TODO Auto-generated method stub
		return new BookDocument();
	}

	@Override
	protected void addDocument(Document arg) {
		// TODO Auto-generated method stub
		this.addDocument((BookDocument)arg);
		
	}
}
