 package kooboot.search.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kooboot.search.exception.ResponsParseException;


public abstract class Response {
	
	
	private Long pageable_count;
	private Long total_count;
	private boolean is_end;
	
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
	
	public void pareseKeywordResponse(String response){
		try{
			JSONObject responseObj = (JSONObject)new JSONParser().parse(response);
			JSONObject meta = (JSONObject)responseObj.get("meta");
			setPageable_count((Long)meta.get("pageable_count"));
			setTotal_count((Long)meta.get("total_count"));
			setIs_end((Boolean)meta.get("is_end"));
			JSONArray documents = (JSONArray)responseObj.get("documents");
			for(int i = 0; i < documents.size(); i++){
				Document document = createDocument();
				document.pareseDocumentResponse((JSONObject)documents.get(i));
				addDocument(document);
			}
		}catch(ParseException e){
			throw new ResponsParseException(e);
		}
		
	}
	
	abstract protected Document createDocument();
	abstract protected void addDocument(Document arg);
}
