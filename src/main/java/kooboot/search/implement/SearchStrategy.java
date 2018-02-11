package kooboot.search.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import kooboot.process.domain.KakaoStrategy;
import kooboot.process.domain.StrategyResult;
import kooboot.process.exception.NotSupportedServiceException;
import kooboot.response.domain.Keyboard;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.search.domain.KakaoSearchService;
import kooboot.search.domain.SearchCode;
import kooboot.translate.domain.TranslateCode;
import kooboot.user.domain.User;
import kooboot.user.domain.status.StatusCode;
import kooboot.util.Constant;

public class SearchStrategy implements KakaoStrategy {
	
	@Autowired
	ApplicationContext appContext;
	
	private KakaoSearchService searchService;
	
	public static final String SEARCH_PREVIOUS_KEYWORD = "이전";
	
	
	public static String SEARCH_REQ_MESSAGE = "검색할 문장을 입력해 주세요.\n\n"
			+ "이전단계로 돌아가려면" +"\""+ SEARCH_PREVIOUS_KEYWORD +"\"" +"을 입력해주세요.\n"
			+ "처음단계로 돌아가려면" +"\""+Constant.INIT_KEYWORD+"\"" +"을 입력해주세요.";
	
	
	@Override
	public StrategyResult doProcessSerivce(User user) {
		// TODO Auto-generated method stub
		ResponseMessage responsMessage = null;
		if(SearchCode.INIT == SearchCode.valueOfCode(user.getSubStatusValue()))
			responsMessage = initProcess(user);
		else if(SearchCode.REQ == SearchCode.valueOfCode(user.getSubStatusValue()))
			responsMessage = reqProcess(user);
		else
			responsMessage = resProcess(user);
			
		return new StrategyResult(user,responsMessage);
	}
	
	private void initializeSearchService(SearchCode code){
		String beanName = "";
		if(SearchCode.WEB == code)
			beanName = "webSearchService";
		else if(SearchCode.BOOK == code)
			beanName = "bookSearchService";
		else if(SearchCode.KEYWORD == code)
			beanName = "keywordSearchService";
		else
			throw new NotSupportedServiceException();
		
		searchService =appContext.getBean(beanName,KakaoSearchService.class);
		
	}
	
	private ResponseMessage initProcess(User user){
		user.setSubStatus(SearchCode.REQ.getValue());
		return getInitMessage();
	}
	
	private ResponseMessage reqProcess(User user){
			user.setSubStatus(user.getReqUserData().getContents());
			return new ResponseMessage(new Message(SEARCH_REQ_MESSAGE),null);	
	}
	
	
	private ResponseMessage resProcess(User user){
		if(reqPreviousStep(user.getReqUserData().getContents())){
			user.setSubStatus(SearchCode.REQ.getValue());
			return initProcess(user);
		} else{
			initializeSearchService(SearchCode.valueOfCode(user.getSubStatusValue()));
			return searchService.doSearchRequest(user.getReqUserData().getContents());
		}
	}
	
	private boolean reqPreviousStep(String contents){
		return SEARCH_PREVIOUS_KEYWORD.equals(contents);
	}
	
	
	
	private ResponseMessage getInitMessage(){
		Keyboard keyboard = new Keyboard();
		keyboard.setType("buttons");
		keyboard.addButton(SearchCode.SEARCH_CATAGORY_WEB);
		keyboard.addButton(SearchCode.SEARCH_CATAGORY_BOOK);
		keyboard.addButton(SearchCode.SEARCH_CATAGORY_KEYWORD);
		return new ResponseMessage(new Message("검색 분류를 선택해주세요."),keyboard);
	}
}
