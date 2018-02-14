package kooboot.search.implement;

import org.springframework.beans.factory.annotation.Value;

import kooboot.httpservice.domain.HttpService;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.search.domain.KakaoSearchService;
import kooboot.search.domain.Response;
import kooboot.search.domain.keyword.KeywordResponse;
import kooboot.search.domain.web.WebResponse;
import kooboot.util.DateUtil;

public class WebSearchService extends KakaoSearchService{
	

	@Value("${search.weburl}") String webUrl;
	private HttpService httpService;
	
	public void setHttpService(HttpService httpService){
		super.setHttpService(httpService);
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return this.webUrl;
	}
	
	
	@Override
	protected ResponseMessage resultMessage(Response arg) {
		// TODO Auto-generated method stub
		String message = "정확도 순으로 최대 5개까지 보여집니다.\n";
		WebResponse response = (WebResponse)arg;
		for(int i = 0; i < response.getTotal_count() && i < 5; i++){
			String entry = "\n(" + (i+1) + ")";
			entry+= "\n제목 : " + replaceHtmlTagWithBlank(response.getDocuments().get(i).getTitle());
			entry+= "\n내용 : " + replaceHtmlTagWithBlank(response.getDocuments().get(i).getContents());
			entry+= "\n일시 : " + DateUtil.getDateYyyymmddhhmmssWithDelimiterWithISO8601(response.getDocuments().get(i).getDatetime());
			entry+= "\n링크 : " + response.getDocuments().get(i).getUrl();
			entry+= "\n";	
			message += entry;
		}
		message += "\n\n"
		+SearchStrategy.SEARCH_REQ_MESSAGE;
		return new ResponseMessage(new Message(message),null);
	}

	@Override
	protected Response createResponse() {
		// TODO Auto-generated method stub
		return new WebResponse();
	}
	
	private String replaceHtmlTagWithBlank(String value){
		return value.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
	}

	

}
