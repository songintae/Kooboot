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

public class KeywordResponse {
	private Long pageable_count;
	private Long total_count;
	private boolean is_end;
	List<Document> documents = new ArrayList<Document>();
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
	
	public void addDocument(Document document){
		documents.add(document);
	}
	
	public void remove(int index){
		this.documents.remove(index);
	}
	
	public List<Document> getDocument(){
		return Collections.unmodifiableList(this.documents);
	}
	
	public void initializeDocuments(List<Document> documents){
		this.documents.clear();
		for(Document document : documents){
			this.addDocument(document);
		}
	}
	
	public void pareseKeywordResponse(String response){
		try{
			JSONObject responseObj = (JSONObject)new JSONParser().parse(response);
			JSONObject meta = (JSONObject)responseObj.get("meta");
			setPageable_count((Long)meta.get("pageable_count"));
			setTotal_count((Long)meta.get("total_count"));
			setIs_end((Boolean)meta.get("is_end"));
			JSONArray documents = (JSONArray)responseObj.get("documents");
			for(int i = 0; i < documents.size(); i++){
				Document document = new Document();
				document.pareseDocumentResponse((JSONObject)documents.get(i));
				addDocument(document);
			}
		}catch(ParseException e){
			System.out.println(e.toString());
		}
		
	}
	
	
	
	
}
