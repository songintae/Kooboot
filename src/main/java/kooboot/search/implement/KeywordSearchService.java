package kooboot.search.implement;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import kooboot.httpservice.domain.HttpService;
import kooboot.httpservice.implement.UrlHttpService;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.search.domain.KakaoSearchService;
import kooboot.search.domain.keyword.KeywordResponse;
import kooboot.util.Constant;

public class KeywordSearchService implements KakaoSearchService{
	
	
	@Value("${search.keywordurl}") String keywordUrl;
	@Value("${search.appkey}") String searchAppKey;
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
	
	@Override
	public ResponseMessage doSearchRequest(String keyword) {
		// TODO Auto-generated method stub
		Map<String,String> param = new HashMap<String,String>();
		param.put("query", keyword);
		KeywordResponse response = new KeywordResponse();
		response.pareseKeywordResponse(httpService
				.doHttpGet(keywordUrl+UrlHttpService.makeParamByUrlEncoder(param), header));
		return createResponseMessage(response);
	}
	
	private ResponseMessage createResponseMessage(KeywordResponse response){
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
	
	private ResponseMessage resultMessage(KeywordResponse response){
		String message = "정확도 순으로 최대 5개까지 보여집니다.\n";
		
		for(int i = 0; i < response.getTotal_count() && i < 5; i++){
			String entry = "\n(" + (i+1) + ")";
			entry+= "\n장소 : " + response.getDocument().get(i).getPlace_name();
			entry+= "\n분류 : " + response.getDocument().get(i).getCategory_group_name();
			entry+= "\n주소 : " + response.getDocument().get(i).getAddress_name();
			entry+= "\n도로명 : " + response.getDocument().get(i).getRoad_address_name();
			entry+= "\n번호 : " + response.getDocument().get(i).getPhone();
			entry+= "\n링크 : " + response.getDocument().get(i).getPlace_url();
			entry+= "\n";	
			message += entry;
		}
		message += SearchStrategy.SEARCH_REQ_MESSAGE;
		return new ResponseMessage(new Message(message),null);
	}

}
