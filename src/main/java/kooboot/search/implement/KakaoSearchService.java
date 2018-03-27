package kooboot.search.implement;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import kooboot.httpservice.domain.HttpService;
import kooboot.httpservice.exception.HttpServiceException;
import kooboot.httpservice.implement.UrlHttpService;
import kooboot.process.exception.NotSupportedServiceException;
import kooboot.search.domain.SearchCode;
import kooboot.search.domain.kakaosearch.Response;
import kooboot.search.domain.kakaosearch.book.BookResponse;
import kooboot.search.domain.kakaosearch.keyword.KeywordResponse;
import kooboot.search.domain.kakaosearch.web.WebResponse;
import kooboot.search.exception.ResponseParseException;
import kooboot.util.Constant;

public class KakaoSearchService {
	
	@Value("${search.appkey}") private String searchAppKey;
	@Value("${search.weburl}") private String webUrl;
	@Value("${search.bookurl}") private String bookUrl;
	@Value("${search.keywordurl}") private String keywordUrl;
	private Map<String, String> header;
	private HttpService httpService;
	
	public void setHttpService(HttpService httpService){
		this.httpService = httpService;
	}
	
	@PostConstruct
	public void setUp(){
		header = new HashMap<String,String>();
		header.put("Authorization", searchAppKey);
	}
	

	public Response doSearchRequest(SearchCode searchCode, String keyword) {
		// TODO Auto-generated method stub
		Response response = null;
		try{
			response = createResponse(searchCode);
			Map<String,String> param = new HashMap<String,String>();
			param.put("query", keyword);
			String httpResponse = httpService.doHttpGet(
					getUrl(searchCode)+UrlHttpService.makeParamByUrlEncoder(param)
					, header);
			response.pareseResponse(httpResponse);
		}catch(ResponseParseException | HttpServiceException | IllegalArgumentException e){
			response.setIs_Err(true);
		}
		return response;
		
	}
	
	private Response createResponse(SearchCode code){
		if(SearchCode.WEB == code)
			return new WebResponse();
		else if(SearchCode.BOOK == code)
			return new BookResponse();
		else if(SearchCode.KEYWORD == code)
			return new KeywordResponse();
		else
			throw new IllegalArgumentException("Invalid code : " + code.getValue());
	}
	
	private String getUrl(SearchCode code){
		if(SearchCode.WEB == code)
			return webUrl;
		else if(SearchCode.BOOK == code)
			return bookUrl;
		else if(SearchCode.KEYWORD == code)
			return keywordUrl;
		else
			throw new IllegalArgumentException("Invalid code : " + code.getValue());
	}
	
}
