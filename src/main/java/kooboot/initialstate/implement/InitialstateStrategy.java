package kooboot.initialstate.implement;


import kooboot.initialstate.domain.InitialstateCode;
import kooboot.process.domain.KakaoStrategy;
import kooboot.process.domain.StrategyResult;
import kooboot.response.domain.Keyboard;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.user.domain.User;
import kooboot.util.Constant;

public class InitialstateStrategy implements KakaoStrategy {
	public final String INITIAL_DELAY_TEXT = "입력시간 5분이 경과했습니다. 처음부터 다시 시작해주세요.";
	public final String INITIAL_INIT_TEXT = "시작 메뉴 입니다.";
	@Override
	public StrategyResult doProcessSerivce(User user) {
		// TODO Auto-generated method stub
		ResponseMessage responsMessage = new ResponseMessage();
		responsMessage.setKeyboard(getinitialKeyboard());
		if(InitialstateCode.valueOfCode(user.getSubStatusValue()) == InitialstateCode.DELAY)
			responsMessage.setMessage(new Message(INITIAL_DELAY_TEXT));
		else
			responsMessage.setMessage(new Message(INITIAL_INIT_TEXT));
		return new StrategyResult(user,responsMessage);
	}
	
	public static Keyboard getinitialKeyboard(){
		Keyboard keyboard = new Keyboard();
		keyboard.setType("buttons");
		keyboard.addButton(Constant.INIT_KEYBOARD_BUTTON_ONE);
		keyboard.addButton(Constant.INIT_KEYBOARD_BUTTON_TWO);
		return keyboard;
	}

}
