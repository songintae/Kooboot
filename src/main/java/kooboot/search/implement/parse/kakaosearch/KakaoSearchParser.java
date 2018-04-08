package kooboot.search.implement.parse.kakaosearch;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.Bean;

import kooboot.process.exception.NotSupportedServiceException;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.search.domain.SearchCode;
import kooboot.search.domain.kakaosearch.Response;
import kooboot.search.domain.parse.SearchParser;
import kooboot.search.implement.SearchStrategy;
import kooboot.util.Constant;

public abstract class KakaoSearchParser implements SearchParser {

	@Override
	public ResponseMessage parseSearchResult(Object arg) {
		// TODO Auto-generated method stub
		return createResponseMessage((Response)arg);
	}
	
	
	private ResponseMessage createResponseMessage(Response response){
		if(!response.isIs_Err()){
			if(response.getTotal_count() == 0)
				return nonResultMessage();
			else
				return resultMessage(response);
		}else{
			return errResultMessage();
		}
		
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
				return "webSearchParser";
			else if(SearchCode.BOOK == code)
				return "bookSearchParser";
			else if(SearchCode.KEYWORD == code)
				return "keywordSearchParser";
			else
				throw new NotSupportedServiceException();
		}catch(NoSuchBeanDefinitionException e){
			throw new NotSupportedServiceException();
		}
	}
	
	abstract protected ResponseMessage resultMessage(Response arg);

}
