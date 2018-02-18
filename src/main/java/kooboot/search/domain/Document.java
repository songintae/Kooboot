package kooboot.search.domain;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mysql.fabric.xmlrpc.base.ResponseParser;

import kooboot.search.domain.book.BookDocument;
import kooboot.search.exception.ResponseParseException;
import kooboot.util.StringUtil;

public class Document {
	
	
	public void pareseDocumentResponse(JSONObject response) throws ResponseParseException{
		Iterator itr = response.keySet().iterator();
		String key = null;
		while(itr.hasNext()){
			key = itr.next().toString();
			if(response.get(key) instanceof JSONArray){
				JSONArray arr = (JSONArray)response.get(key);
				for(Object obj : arr){	
					methodInvoke("add"+StringUtil.StringFirstUpper(key),obj.getClass(),obj);
				}
			}
			else
				methodInvoke("set"+StringUtil.StringFirstUpper(key),response.get(key).getClass(),response.get(key));	
		}
	}
	
	private void methodInvoke(String methodName,Class<?> classes , Object argument){
		try{
			getClass().getMethod(methodName, classes).invoke(this, argument);
		}catch(Exception e){
			throw new ResponseParseException(e);
		}
		
	}
}
