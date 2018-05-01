package kooboot.search.implement.parse.kakaosearch;

import org.springframework.stereotype.Service;

import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.search.domain.kakaosearch.Response;
import kooboot.search.domain.kakaosearch.book.BookResponse;
import kooboot.search.implement.SearchHandler;


@Service
public class BookSearchParser extends KakaoSearchParser {
	
	@Override
	protected ResponseMessage resultMessage(Response arg) {
		// TODO Auto-generated method stub
		String message = "정확도 순으로 최대 5개까지 보여집니다.\n";
		BookResponse response = (BookResponse)arg;
		for(int i = 0; i < response.getTotal_count() && i < 5; i++){
			String entry = "\n(" + (i+1) + ")";
			entry+= "\n제목 : " + response.getDocuments().get(i).getTitle();
			entry+= "\n분류 : " + response.getDocuments().get(i).getCategory();
			entry+= "\n저자 : " ;
			for(int j = 0; j <  response.getDocuments().get(i).getAuthors().size() ; j++){
				entry+= response.getDocuments().get(i).getAuthors().get(j);
				if(j != response.getDocuments().get(i).getAuthors().size() -1)
					entry+=", ";
			}
			entry+= "\n가격 : " + response.getDocuments().get(i).getPrice();
			entry+= "\n할인가격 : " + response.getDocuments().get(i).getSale_price();
			entry+= "\n링크 : " + response.getDocuments().get(i).getUrl();
			entry+= "\n";	
			message += entry;
		}
		message += "\n\n"
		+SearchHandler.SEARCH_REQ_MESSAGE;
		return new ResponseMessage(new Message(message),null);
	}
}
