package kooboot.process.implement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import kooboot.prepost.implement.PostProcessService;
import kooboot.prepost.implement.PreProcessService;
import kooboot.process.domain.KakaoHandler;
import kooboot.process.domain.StrategyResult;
import kooboot.process.exception.NotSupportedServiceException;
import kooboot.request.domain.RequestMessage;
import kooboot.response.domain.Keyboard;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.user.domain.User;
import kooboot.user.domain.status.Status;
import kooboot.user.domain.status.StatusCode;
import kooboot.util.Constant;

public class KakaoProcess {
	
	
	@Autowired
	private PreProcessService preProcess;
	@Autowired 
	private PostProcessService postProcess;
	
	@Autowired
	private Map<String,KakaoHandler> handlers;
	
	private final String NOT_SUPPROTED_SERVICE_TEXT = "이 기능은 지원하지 않는 기능입니다.\n 처음부터 다시 시작해 주십시오.";
	
	//Template  메서드
	public ResponseMessage kakaoProcessTemplate(RequestMessage requestMessage){
		User user = null;
		StrategyResult result = null;
		try{
			//선 처리.
			user = preProcess.preProcess(requestMessage);
			//변하는 부분.(Strategy)
			result = excuteActionAndGetResponse(user);
			//결과 return
			user = result.getUser();
			return result.getResponseMessage();
		}catch(NotSupportedServiceException e){
			//에러처리.
			user.setStatus(StatusCode.INIT);
			return notSupportedServiceResponse();
		}finally{
			//후 처리.
			postProcess.postProcess(user);
		}
	}
	
	private StrategyResult excuteActionAndGetResponse(User user){
		KakaoHandler handler = lookupHandlerBy(user.getStatusCode()); 
		return handler.execute(user);
	}
	
	private KakaoHandler lookupHandlerBy(StatusCode code){
		String handlerName = "";
		//사용자 상태에 따라 전략 선택.
		if(StatusCode.INIT == code)
			handlerName = "initialstateStrategy";
		else if(StatusCode.TRANSLATE == code)
			handlerName = "translateStrategy";
		else if(StatusCode.SEARCH == code)
			handlerName = "searchStrategy";
		else
			throw new NotSupportedServiceException();
		return handlers.get(handlerName);
	}
	
	private ResponseMessage notSupportedServiceResponse(){
		//에러 메시지 처리.
		Message message = new Message();
		message.setText(NOT_SUPPROTED_SERVICE_TEXT);
		Keyboard keyboard = new Keyboard();
		keyboard.setType("buttons");
		keyboard.addButton(Constant.INIT_KEYBOARD_BUTTON_ONE);
		keyboard.addButton(Constant.INIT_KEYBOARD_BUTTON_TWO);
		return new ResponseMessage(message, keyboard);
	}
}


