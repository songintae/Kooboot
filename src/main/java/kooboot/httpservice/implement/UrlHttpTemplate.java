package kooboot.httpservice.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import kooboot.httpservice.domain.UrlCallback;
import kooboot.httpservice.exception.HttpServiceException;

public class UrlHttpTemplate {
	
	public String httpServiceTemplate(String requestUrl, UrlCallback callback) {
		URL url = null;
		HttpURLConnection con = null;
		BufferedReader br = null;
		try{
			
			url = new URL(requestUrl);
			con = (HttpURLConnection)url.openConnection();
			con = callback.doSomtingWithHttpService(con);
			int responseCode = con.getResponseCode();
			if(responseCode == 200)
				br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			else
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			String inputLine = null;
			StringBuilder response = new StringBuilder();
			while((inputLine=br.readLine()) != null){
				response.append(inputLine);
			}
			return response.toString();
		}catch(IOException e){
			throw new HttpServiceException(e);
		}finally{
			if(con != null)
				con.disconnect();
		}
	}
}
