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
	
	
	public List<KeywordDocument> getDocuments(){
		return Collections.unmodifiableList((List)documents);
	}
	

	@Override
	protected Document createDocument() {
		// TODO Auto-generated method stub
		return new KeywordDocument();
	}

	
	
	
	
	
}
