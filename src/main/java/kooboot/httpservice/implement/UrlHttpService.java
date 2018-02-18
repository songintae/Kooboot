package kooboot.httpservice.implement;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;

import kooboot.httpservice.domain.HttpService;
import kooboot.httpservice.domain.UrlCallback;

public class UrlHttpService implements HttpService {
	
	private UrlHttpTemplate urlHttpTemplate;
	
	public void setUrlHttpTemplate(UrlHttpTemplate urlHttpTemplate){
		this.urlHttpTemplate = urlHttpTemplate;
	}
	
	@Override
	public String doHttpGet(String url, final Map<String, String> header) {
		// TODO Auto-generated method stub
		return urlHttpTemplate.httpServiceTemplate(url, new UrlCallback(){

			@Override
			//익명클래스 구현.
			public HttpURLConnection doSomtingWithHttpService(HttpURLConnection con) throws IOException {
				// TODO Auto-generated method stub
				//connection 객체 설정.
				con.setRequestProperty("content-type", "application/json;charset=UTF-8");
				con.setRequestMethod("GET");
				// 익명클래스에서는 자신을 포함한 메서드의 매개변수중 final로 선언된 변수를 가져다 쓸 수 있는 강점이 있다.
				Iterator<String> keyIterator = header.keySet().iterator();
				String key = null;
				while(keyIterator.hasNext()){
					key = keyIterator.next();
					con.setRequestProperty(key, header.get(key));
				}
				//connection 객체 반환.
				return con;
			}});
	}

	@Override
	public String doHttpPostByUrlencoded(String url, final Map<String, String> param) {
		// TODO Auto-generated method stub
		return urlHttpTemplate.httpServiceTemplate(url, new UrlCallback(){

			@Override
			public HttpURLConnection doSomtingWithHttpService(HttpURLConnection con) throws IOException {
				// TODO Auto-generated method stub
				return doPostRequest(con,makeParamByUrlEncoder(param));
			}});
	}

	@Override
	public String doHttpPostByUrlencoded(String url, final Map<String, String> header, final Map<String, String> param) {
		// TODO Auto-generated method stub
		return urlHttpTemplate.httpServiceTemplate(url, new UrlCallback(){

			@Override
			public HttpURLConnection doSomtingWithHttpService(HttpURLConnection con) throws IOException {
				// TODO Auto-generated method stub
				 Iterator<String> keyIterator = header.keySet().iterator();
				 String key = null;
				 while(keyIterator.hasNext()){
					 key = keyIterator.next();
					 con.setRequestProperty(key, header.get(key));
				 }
				 return doPostRequest(con,makeParamByUrlEncoder(param));
			}});
	}

	@Override
	public String doHttpPostByJson(String url, final Map<String, String> param) {
		return urlHttpTemplate.httpServiceTemplate(url, new UrlCallback(){

			@Override
			public HttpURLConnection doSomtingWithHttpService(HttpURLConnection con) throws IOException {
				// TODO Auto-generated method stub
				con.setRequestProperty("Content-Type", "application/json");
				return doPostRequest(con,makeParamByJson(param));
			}});
	}

	@Override
	public String doHttpPostByJson(String url, final Map<String, String> header, final Map<String, String> param) {
		// TODO Auto-generated method stub
		return urlHttpTemplate.httpServiceTemplate(url, new UrlCallback(){

			@Override
			//익명클래스 구현.
			public HttpURLConnection doSomtingWithHttpService(HttpURLConnection con) throws IOException {
				// TODO Auto-generated method stub
				//Connection 객체 설정.
				con.setRequestProperty("Content-Type", "application/json");
				Iterator<String> keyIterator = header.keySet().iterator();
				 String key = null;
				 while(keyIterator.hasNext()){
					 key = keyIterator.next();
					 con.setRequestProperty(key, header.get(key));
				 }
				//Connection 객체 반환.
				return doPostRequest(con,makeParamByJson(param));
			}});
	}
	
	private HttpURLConnection doPostRequest(HttpURLConnection con , String param) throws IOException{
		//Connection 객체 Post 설정.
		con.setRequestMethod("POST");
		//Parameter를 설정 및 요청.
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(param);
        wr.flush();
        wr.close();
		return con;
	}
	
	public static String makeParamByUrlEncoder(Map<String,String> param){
		try{
			String resultParam = null;
			Iterator<String> keyIterator = param.keySet().iterator();
			String key = keyIterator.next();
			resultParam = key + "=" + URLEncoder.encode(param.get(key),"UTF-8");
			
			while(keyIterator.hasNext()){
				key = keyIterator.next();
				resultParam+= "&" + key + "=" + URLEncoder.encode(param.get(key),"UTF-8");
			}
			return resultParam;
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		
	}
	
	public static String makeParamByJson(Map<String,String> param){
		try{
			String resultParam = null;
			Iterator<String> keyIterator = param.keySet().iterator();
			String key = null;
			JSONObject obj = new JSONObject();
			while(keyIterator.hasNext()){
				key = keyIterator.next();
				obj.put(key, URLEncoder.encode(param.get(key),"UTF-8"));
			}
			return obj.toJSONString();
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		
	}

}
