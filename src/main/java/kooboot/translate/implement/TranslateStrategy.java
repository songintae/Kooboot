package kooboot.translate.implement;

import kooboot.process.domain.KakaoStrategy;
import kooboot.process.domain.StrategyResult;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.translate.domain.TranslateCode;
import kooboot.translate.domain.TranslateService;
import kooboot.user.domain.User;
import kooboot.util.Constant;

public class TranslateStrategy implements KakaoStrategy {

	private TranslateService translateService;

	public void setTranslateService(TranslateService translateService) {
		this.translateService = translateService;
	}
	
	//이전 단계로 이동 요청을 하는 메시지.
	private final String TRANSLATE_PREVIOUS_KEYWORD = "이전";
    //어떤 번역을 요청할지 선택하는 메시지.
	private final String TRANSLATE_INIT_MESSAGE = " 1.한국어 -> 영어로 번역.\n"
												 + "2.한국어 -> 일어로 번역.\n" 
												 + "3.한국어 -> 중국어로 번역.\n"
												 + "4.영문  -> 한국어로 번역.\n" 
												 + "5.일어  -> 한국어로 번역.\n" 
												 + "6.중국어 -> 한국어로 번역.\n\n" 
												 + "원하시는 번역을 숫자로 입력해주세요.\n"
												 + "처음단계로 돌아가려면" + "\"" + Constant.INIT_KEYWORD + "\"" + "을 입력해주세요.";
	//번역을 할 문장을 입력 요청하는 메시지.
	private final String TRANSLATE_REQ_MESSAGE = "번역할 문장을 입력해 주세요.\n\n"
												+ "이전단계로 돌아가려면" + "\""
												+ TRANSLATE_PREVIOUS_KEYWORD 
												+ "\"" + "을 입력해주세요.\n"
												+ "처음단계로 돌아가려면" + "\"" + Constant.INIT_KEYWORD + "\""+ "을 입력해주세요.";

	private final String TRANSLATE_ERR_MESSAGE = "잘못 입력했습니다. \n\n" + TRANSLATE_INIT_MESSAGE;

	@Override
	public StrategyResult doProcessSerivce(User user) {
		// TODO Auto-generated method stub
		ResponseMessage responsMessage = null;
		//유저의 상태에 따라 로직 처리.
		if (TranslateCode.INIT == TranslateCode.valueOfCode(user.getSubStatusValue()))
			responsMessage = initProcess(user);
		else if (TranslateCode.REQ == TranslateCode.valueOfCode(user.getSubStatusValue()))
			responsMessage = reqProcess(user);
		else
			responsMessage = resProcess(user);

		return new StrategyResult(user, responsMessage);
	}

	
	private boolean reqPreviousStep(String contents) {
		return TRANSLATE_PREVIOUS_KEYWORD.equals(contents);
	}
	
	//초기 상태일떄 Req 상태로 변경 후 TRANSLATE_INIT_MESSAGE를 담은 Response 반환. 
	private ResponseMessage initProcess(User user) {
		user.setSubStatus(TranslateCode.REQ.getValue());
		return new ResponseMessage(new Message(TRANSLATE_INIT_MESSAGE), null);
	}
	
	//요청 상태일때 사용자 상태를 요청한 상태로 변경 후 TRANSLATE_REQ_MESSAGE를 담은 Respons 반환.
	private ResponseMessage reqProcess(User user) {
		try {
			user.setSubStatus(user.getReqUserData().getContents());
			return new ResponseMessage(new Message(TRANSLATE_REQ_MESSAGE), null);
		} catch (AssertionError e) {
			return new ResponseMessage(new Message(TRANSLATE_ERR_MESSAGE), null);
		}

	}
	
	//응답 상태일때 번역 할 문장에 대한 번역을 담아 Response.
	private ResponseMessage resProcess(User user) {
		if (reqPreviousStep(user.getReqUserData().getContents())) {
			user.setSubStatus(TranslateCode.REQ.getValue());
			return initProcess(user);
		} else {
			TranslateCode code = TranslateCode.valueOfCode(user.getSubStatusValue());
			String translateSentence = translateService.translateSentence(code.getSource(), code.getTarget(),
					user.getReqUserData().getContents());
			return new ResponseMessage(new Message(translateSentence + "\n\n" + TRANSLATE_REQ_MESSAGE), null);
		}

	}

}
