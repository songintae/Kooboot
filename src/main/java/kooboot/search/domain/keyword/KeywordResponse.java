package kooboot.search.domain.keyword;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonParser;

import kooboot.search.domain.Document;
import kooboot.search.domain.Response;

public class KeywordResponse extends Response {
	
	List<KeywordDocument> documents = new ArrayList<KeywordDocument>();

	
	public void addDocument(KeywordDocument document){
		documents.add(document);
	}
	
	public void remove(int index){
		this.documents.remove(index);
	}
	
	public List<KeywordDocument> getDocuments(){
		return Collections.unmodifiableList(this.documents);
	}
	
	public void initializeDocuments(List<KeywordDocument> documents){
		this.documents.clear();
		for(KeywordDocument document : documents){
			this.addDocument(document);
		}
	}

	@Override
	protected Document createDocument() {
		// TODO Auto-generated method stub
		return new KeywordDocument();
	}

	@Override
	protected void addDocument(Document arg) {
		// TODO Auto-generated method stub
		this.addDocument((KeywordDocument)arg);
		
	}
	
	
	
	
	
}
