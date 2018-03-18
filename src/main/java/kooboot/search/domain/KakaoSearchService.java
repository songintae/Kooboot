package kooboot.search.domain;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Value;

import com.mysql.fabric.xmlrpc.base.ResponseParser;

import kooboot.httpservice.domain.HttpService;
import kooboot.httpservice.exception.HttpServiceException;
import kooboot.httpservice.implement.UrlHttpService;
import kooboot.process.exception.NotSupportedServiceException;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.search.domain.keyword.KeywordResponse;
import kooboot.search.exception.ResponseParseException;
import kooboot.search.implement.SearchStrategy;
import kooboot.util.Constant;

public abstract class KakaoSearchService {
	
	@Value("${search.appkey}") private String searchAppKey;
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
	

	public ResponseMessage doSearchRequest(String keyword) {
		// TODO Auto-generated method stub
		try{
			Map<String,String> param = new HashMap<String,String>();
			param.put("query", keyword);
			Response response = createResponse();
			String httpResponse = httpService
					.doHttpGet(getUrl()+UrlHttpService.makeParamByUrlEncoder(param), header);
			response.pareseResponse(httpResponse);
			return createResponseMessage(response);
		}catch(ResponseParseException | HttpServiceException e){
			return errResultMessage();
		}
		
	}
	
	private ResponseMessage createResponseMessage(Response response){
		if(response.getTotal_count() == 0)
			return nonResultMessage();
		else
			return resultMessage(response);
	}
	
	private ResponseMessage nonResultMessage(){
		String message = "검색된 결과과 없습니다.\n\n"
				+ "이전단계로 돌아가려면" +"\""+ SearchStrategy.SEARCH_PREVIOUS_KEYWORD +"\"" +"을 입력해주세요.\n"
				+ "처음단계로 돌아가려면" +"\""+Constant.INIT_KEYWORD+"\"" +"을 입력해주세요.";
		return new ResponseMessage(new Message(message),null);
	}
	private ResponseMessage errResultMessage(){
		String message = "검색중 오류가 발생했습니다. 다시 검색해 주시기 바랍니다.\n\n"
				+ "이전단계로 돌아가려면" +"\""+ SearchStrategy.SEARCH_PREVIOUS_KEYWORD +"\"" +"을 입력해주세요.\n"
				+ "처음단계로 돌아가려면" +"\""+Constant.INIT_KEYWORD+"\"" +"을 입력해주세요.";
		return new ResponseMessage(new Message(message),null);
	}
	
	public static String getServicenameForCode(SearchCode code){
		try{
			if(SearchCode.WEB == code)
				return "webSearchService";
			else if(SearchCode.BOOK == code)
				return "bookSearchService";
			else if(SearchCode.KEYWORD == code)
				return "keywordSearchService";
			else
				throw new NotSupportedServiceException();
		}catch(NoSuchBeanDefinitionException e){
			throw new NotSupportedServiceException();
		}	
	}
	
	abstract protected ResponseMessage resultMessage(Response response);
	abstract protected Response createResponse();
	abstract protected String getUrl();
}
