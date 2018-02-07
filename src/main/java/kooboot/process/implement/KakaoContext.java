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
	
	public ResponseMessage KakaoProcessTemplate(RequestMessage requestMessage){
		User user = null;
		StrategyResult result = null;
		try{
			preProcess.preProcess(requestMessage);
			result = doStretegyProcess(user);
			return result.getResponseMessage();
		}catch(NotSupportedServiceException e){
			user.setStatus(new Status(StatusCode.INIT));
			return notSupportedServiceResponse();
		}finally{
			postProcess.postProcess(result.getUser());
		}
	}
	
	private StrategyResult doStretegyProcess(User user){
		initializeStrategy(user);
		return strategy.doProcessSerivce(user);
	}
	private void initializeStrategy(User user){
		String beanName = "";
		if(StatusCode.INIT == user.getStatus().getStatusCode())
			beanName = "initialstateStategy";
		else if(StatusCode.TRANSLATE == user.getStatus().getStatusCode())
			beanName = "translateStrategy";
		else if(StatusCode.WEATHER == user.getStatus().getStatusCode())
			beanName = "weatherStrategy";
		else
			throw new AssertionError("Unknown value: " + user.getStatus().getStatusCode().getValue());
		
		strategy =appContext.getBean(beanName,KakaoStrategy.class);
	}
	
	private ResponseMessage notSupportedServiceResponse(){
		Message message = new Message();
		message.setText("이 기능은 지원하지 않는 기능입니다. 처음부터 다시 시작해 주십시오.");
		Keyboard keyboard = new Keyboard();
		keyboard.setType("buttons");
		keyboard.addButton(Constant.INIT_KEYBOARD_BUTTON_ONE);
		keyboard.addButton(Constant.INIT_KEYBOARD_BUTTON_TWO);
		return new ResponseMessage(message, keyboard);
	}
}


