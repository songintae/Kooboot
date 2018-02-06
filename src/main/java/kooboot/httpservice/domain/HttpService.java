package kooboot.httpservice.domain;

import java.util.Map;

public interface HttpService {
	
	
	public String doHttpGet(String url, final Map<String, String> header);
	public String doHttpPostByUrlencoded(String url , final Map<String, String> param);
	public String doHttpPostByUrlencoded(String url , final Map<String, String> Header , final Map<String, String> param);
	public String doHttpPostByJson(String url , final Map<String, String> param);
	public String doHttpPostByJson(String url , final Map<String, String> Header,final Map<String, String> param);
	
}