package kooboot.httpservice.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kooboot.appcontext.AppContext;
import kooboot.httpservice.domain.HttpService;
import kooboot.search.domain.keyword.KeywordResponse;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class HttpServiceTest {
	@Autowired
	HttpService httpService;
	
	@Test
	public void geturlencodeTest() throws UnsupportedEncodingException, ParseException{
		String url ="https://dapi.kakao.com/v2/local/search/keyword.json?query=" + URLEncoder.encode("드람브르","UTF-8");
		Map<String, String> header = new HashMap<String,String>();
		header.put("Authorization", "KakaoAK 61702e4b839f22f0e9cd32812ca4a748");
		String result = httpService.doHttpGet(url, header);
		KeywordResponse keywordResponse = new KeywordResponse();
		keywordResponse.pareseKeywordResponse(result);
		System.out.println(keywordResponse.getDocument().get(0).getPlace_name());
		
	}
}
