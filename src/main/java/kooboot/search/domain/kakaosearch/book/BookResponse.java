package kooboot.search.domain.kakaosearch.book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kooboot.search.domain.kakaosearch.Document;
import kooboot.search.domain.kakaosearch.Response;
import kooboot.search.domain.kakaosearch.keyword.KeywordDocument;
import kooboot.search.exception.ResponseParseException;


public class BookResponse extends Response{

	
	public List<BookDocument> getDocuments(){
		return Collections.unmodifiableList((List)documents);
	}
	
	@Override
	protected Document createDocument() {
		// TODO Auto-generated method stub
		return new BookDocument();
	}

}
