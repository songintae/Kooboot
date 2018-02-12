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
import kooboot.search.domain.Response;
import kooboot.search.domain.book.BookResponse;
import kooboot.search.domain.keyword.KeywordResponse;
import kooboot.util.Constant;

public class BookSearchService extends KakaoSearchService {
	
	@Value("${search.bookurl}") String bookdUrl;
	private HttpService httpService;
	
	public void setHttpService(HttpService httpService){
		super.setHttpService(httpService);
	}
	
	@Override
	protected String getUrl() {
		// TODO Auto-generated method stub
		return bookdUrl;
	}
	
	@Override
	protected ResponseMessage resultMessage(Response arg) {
		// TODO Auto-generated method stub
		String message = "정확도 순으로 최대 5개까지 보여집니다.\n";
		BookResponse response = (BookResponse)arg;
		for(int i = 0; i < response.getTotal_count() && i < 5; i++){
			String entry = "\n(" + (i+1) + ")";
			entry+= "\n제목 : " + response.getDocument().get(i).getTitle();
			entry+= "\n분류 : " + response.getDocument().get(i).getCategory();
			entry+= "\n저자 : " ;
			for(int j = 0; j <  response.getDocument().get(i).getAuthors().size() ; j++){
				entry+= response.getDocument().get(i).getAuthors().get(j);
				if(j != response.getDocument().get(i).getAuthors().size() -1)
					entry+=", ";
			}
			entry+= "\n가격 : " + response.getDocument().get(i).getPrice();
			entry+= "\n할인가격 : " + response.getDocument().get(i).getSale_price();
			entry+= "\n링크 : " + response.getDocument().get(i).getUrl();
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
		return new BookResponse();
	}


	
}
