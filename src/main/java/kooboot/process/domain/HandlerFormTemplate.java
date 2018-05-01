package kooboot.process.domain;

import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.translate.domain.TranslateCode;
import kooboot.user.domain.User;

public abstract class HandlerFormTemplate implements KakaoHandler {

	
	public final static String PREVIOUS_KEYWORD = "이전";
	
	@Override
	abstract public StrategyResult execute(User user);
	
	abstract protected ResponseMessage initMessage();
	abstract protected ResponseMessage reqMessage();
	abstract protected ResponseMessage errMessage();
	abstract protected String reqSubStatusValue();
	abstract protected ResponseMessage doResProcess(User user);
	
	protected boolean reqPreviousStep(String contents) {
		return PREVIOUS_KEYWORD.equals(contents);
	}
	
	protected ResponseMessage initProcess(User user){
		user.setSubStatus(reqSubStatusValue());
		return initMessage();
	}
	
	protected ResponseMessage reqProcess(User user) {
		try {
			user.setSubStatus(user.getReqUserData().getContents());
			return reqMessage();
		} catch (AssertionError e) {
			return errMessage();
		}

	}
	
	protected ResponseMessage resProcess(User user){
		if(reqPreviousStep(user.getReqUserData().getContents()))
			return initProcess(user);
		return doResProcess(user);
	}
	
	
}
