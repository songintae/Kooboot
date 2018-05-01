package kooboot.search.implement;

import java.util.Map;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;

import kooboot.process.domain.HandlerFormTemplate;
import kooboot.process.domain.StrategyResult;
import kooboot.process.exception.NotSupportedServiceException;
import kooboot.response.domain.Keyboard;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.search.domain.SearchCode;
import kooboot.search.domain.kakaosearch.Response;
import kooboot.search.domain.parse.SearchParser;
import kooboot.search.implement.parse.kakaosearch.KakaoSearchParser;
import kooboot.user.domain.User;
import kooboot.util.Constant;

public class SearchHandler extends HandlerFormTemplate {
	
	
	@Autowired
	private KakaoSearchService searchService;
	@Autowired
	private Map<String,SearchParser> searchParserMap;
	
	
	public static String SEARCH_REQ_MESSAGE = "검색할 문장을 입력해 주세요.\n\n"
			+ "이전단계로 돌아가려면" +"\""+ PREVIOUS_KEYWORD +"\"" +"을 입력해주세요.\n"
			+ "처음단계로 돌아가려면" +"\""+Constant.INIT_KEYWORD+"\"" +"을 입력해주세요.";
	
	
	@Override
	public StrategyResult execute(User user) {
		// TODO Auto-generated method stub
		try{
			ResponseMessage responsMessage = null;
			if(SearchCode.INIT == SearchCode.valueOfCode(user.getSubStatusValue()))
				responsMessage = initProcess(user);
			else if(SearchCode.REQ == SearchCode.valueOfCode(user.getSubStatusValue()))
				responsMessage = reqProcess(user);
			else
				responsMessage = resProcess(user);
				
			return new StrategyResult(user,responsMessage);
		}catch(AssertionError | NotSupportedServiceException e){
			return new StrategyResult(user,errMessage());
		}
		
	}
	
	private Keyboard getSearchKeboard(){
		Keyboard keyboard = new Keyboard();
		keyboard.setType("buttons");
		keyboard.addButton(SearchCode.SEARCH_CATAGORY_WEB);
		keyboard.addButton(SearchCode.SEARCH_CATAGORY_BOOK);
		keyboard.addButton(SearchCode.SEARCH_CATAGORY_KEYWORD);
		return keyboard;
	}
	
	private SearchParser lookupParserBy(SearchCode code){
		return searchParserMap.get(KakaoSearchParser.getServicenameForCode(code));
	}

	@Override
	protected ResponseMessage initMessage() {
		// TODO Auto-generated method stub
		return new ResponseMessage(new Message("검색 분류를 선택해주세요."),getSearchKeboard());
	}

	@Override
	protected String reqSubStatusValue() {
		// TODO Auto-generated method stub
		return SearchCode.REQ.getValue();
	}

	@Override
	protected ResponseMessage reqMessage() {
		// TODO Auto-generated method stub
		return new ResponseMessage(new Message(SEARCH_REQ_MESSAGE),null);
	}

	@Override
	protected ResponseMessage errMessage() {
		// TODO Auto-generated method stub
		return new ResponseMessage(new Message("처리중 오류가 발생했습니다. 검색 분류를 다시 선택해 주세요."),getSearchKeboard());
	}


	@Override
	protected ResponseMessage doResProcess(User user) {
		// TODO Auto-generated method stub
		Response kakaoResponse = searchService.doSearchRequest(
				SearchCode.valueOfCode(user.getSubStatusValue())
				,user.getReqUserData().getContents());
		return lookupParserBy(SearchCode.valueOfCode(user.getSubStatusValue()))
				.parseSearchResult(kakaoResponse);
	}


}
