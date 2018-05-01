package kooboot.search.implement.parse.kakaosearch;

import org.springframework.stereotype.Service;

import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.search.domain.kakaosearch.Response;
import kooboot.search.domain.kakaosearch.web.WebResponse;
import kooboot.search.implement.SearchHandler;
import kooboot.util.DateUtil;

@Service
public class WebSearchParser extends KakaoSearchParser {
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
		+SearchHandler.SEARCH_REQ_MESSAGE;
		return new ResponseMessage(new Message(message),null);
	}
	
	
	private String replaceHtmlTagWithBlank(String value){
		return value.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");
	}

}
