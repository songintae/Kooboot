package kooboot.search.implement.parse.kakaosearch;

import org.springframework.stereotype.Service;

import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.search.domain.kakaosearch.Response;
import kooboot.search.domain.kakaosearch.keyword.KeywordResponse;
import kooboot.search.implement.SearchHandler;

@Service
public class KeywordSearchParser extends KakaoSearchParser{

	@Override
	protected ResponseMessage resultMessage(Response arg){
		String message = "정확도 순으로 최대 5개까지 보여집니다.\n";
		KeywordResponse response = (KeywordResponse)arg;
		for(int i = 0; i < response.getTotal_count() && i < 5; i++){
			String entry = "\n(" + (i+1) + ")";
			entry+= "\n장소 : " + response.getDocuments().get(i).getPlace_name();
			entry+= "\n분류 : " + response.getDocuments().get(i).getCategory_group_name();
			entry+= "\n주소 : " + response.getDocuments().get(i).getAddress_name();
			entry+= "\n도로명 : " + response.getDocuments().get(i).getRoad_address_name();
			entry+= "\n번호 : " + response.getDocuments().get(i).getPhone();
			entry+= "\n링크 : " + response.getDocuments().get(i).getPlace_url();
			entry+= "\n";	
			message += entry;
		}
		message += "\n\n"
		+SearchHandler.SEARCH_REQ_MESSAGE;
		return new ResponseMessage(new Message(message),null);
	}
}
