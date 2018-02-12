package kooboot.search.domain.book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kooboot.search.Exception.ResponsParseException;
import kooboot.search.domain.Document;
import kooboot.search.domain.Response;
import kooboot.search.domain.keyword.KeywordDocument;


public class BookResponse extends Response{
	private Long pageable_count;
	private Long total_count;
	private boolean is_end;
	List<BookDocument> documents = new ArrayList<BookDocument>();
	public Long getPageable_count() {
		return pageable_count;
	}
	public void setPageable_count(Long pageable_count) {
		this.pageable_count = pageable_count;
	}
	public Long getTotal_count() {
		return total_count;
	}
	public void setTotal_count(Long total_count) {
		this.total_count = total_count;
	}
	public boolean isIs_end() {
		return is_end;
	}
	public void setIs_end(boolean is_end) {
		this.is_end = is_end;
	}
	
	public void addDocument(BookDocument document){
		documents.add(document);
	}
	
	public void remove(int index){
		this.documents.remove(index);
	}
	
	public List<BookDocument> getDocument(){
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
