package kooboot.process.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import kooboot.prepost.implement.PostProcessService;
import kooboot.prepost.implement.PreProcessService;
import kooboot.process.domain.KakaoStrategy;
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

public class KakaoContext {
	
	
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	private PreProcessService preProcess;
	@Autowired 
	private PostProcessService postProcess;
	
	private KakaoStrategy strategy;
	private final String NOT_SUPPROTED_SERVICE_TEXT = "이 기능은 지원하지 않는 기능입니다.\n 처음부터 다시 시작해 주십시오.";
	
	public ResponseMessage KakaoProcessTemplate(RequestMessage requestMessage){
		User user = null;
		StrategyResult result = null;
		try{
			user = preProcess.preProcess(requestMessage);
			result = doStretegyProcess(user);
			user = result.getUser();
			return result.getResponseMessage();
		}catch(NotSupportedServiceException e){
			user.setStatus(StatusCode.INIT);
			return notSupportedServiceResponse();
		}finally{
			postProcess.postProcess(user);
		}
	}
	
	private StrategyResult doStretegyProcess(User user){
		initializeStrategy(user.getStatusCode());
		return strategy.doProcessSerivce(user);
	}
	private void initializeStrategy(StatusCode code){
		String beanName = "";
		if(StatusCode.INIT == code)
			beanName = "initialstateStrategy";
		else if(StatusCode.TRANSLATE == code)
			beanName = "translateStrategy";
		else if(StatusCode.SEARCH == code)
			beanName = "searchStrategy";
		else
			throw new NotSupportedServiceException();
		
		strategy =appContext.getBean(beanName,KakaoStrategy.class);
	}
	
	private ResponseMessage notSupportedServiceResponse(){
		Message message = new Message();
		message.setText(NOT_SUPPROTED_SERVICE_TEXT);
		Keyboard keyboard = new Keyboard();
		keyboard.setType("buttons");
		keyboard.addButton(Constant.INIT_KEYBOARD_BUTTON_ONE);
		keyboard.addButton(Constant.INIT_KEYBOARD_BUTTON_TWO);
		return new ResponseMessage(message, keyboard);
	}
}


