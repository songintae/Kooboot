package kooboot.prepost.implement;

import java.util.Date;

import org.springframework.dao.EmptyResultDataAccessException;

import kooboot.initialstate.domain.InitialstateCode;
import kooboot.request.domain.RequestMessage;
import kooboot.user.domain.User;
import kooboot.user.domain.UserData;
import kooboot.user.domain.status.Status;
import kooboot.user.domain.status.StatusCode;
import kooboot.user.service.domain.UserService;
import kooboot.util.Constant;
import kooboot.util.DateUtil;

public class PreProcessService {
	
	private UserService userService;
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	public User preProcess(RequestMessage message){
		
		User user = initializeUser(message);
		user.setUserStatus();
		return user;
	}
	
	//요청 메시지와 사용자 데이터를 이용해 User 객체 생
	public User initializeUser(RequestMessage message){
		User user = null;
		//UserData객체 생성.
		UserData reqUserData = new UserData(message.getUser_key(),
				message.getType(),
				message.getContent());
		try{
			
			//User 데이터 조회.
			user = userService.getUser(message.getUser_key());
			user.setReqUserData(reqUserData);
			
			//지연상태 확인.
			if(delayRequestCheck(user.getLastReqTime())){
				//1Depth 초기화 상태 설정.
				user.setStatus(StatusCode.INIT);
				//2Depth 초기화, 지연상태 설정.
				if(keyboardByPassCheck(user))	
					//Keyboard 요청으로 넘어온 경우.
					user.setSubStatus(InitialstateCode.INIT.getValue());
				else			
					user.setSubStatus(InitialstateCode.DELAY.getValue());

			}
			
		}catch(EmptyResultDataAccessException e){
			//사용자가 데이터가 DB에 없을 경우 초기화 상태의 객체 생성.
			user = new User(message.getUser_key(),
					new Status(StatusCode.INIT),
					reqUserData);
		}
		
		return user;
	}
	
	//지연 요청 판단.
	public boolean delayRequestCheck(String reqTime){
		Date checkTime = new Date();
		checkTime.setMinutes(checkTime.getMinutes() -5);
		if(DateUtil.getDateYyyymmddhhmmss(checkTime).compareTo(reqTime) >=0)
			return true;
		else
			return false;
	}
	
	//Keyboard 요청 판단.
	public boolean keyboardByPassCheck(User user){
		switch(user.getReqUserData().getContents()){
			case Constant.INIT_KEYBOARD_BUTTON_ONE:
			case Constant.INIT_KEYBOARD_BUTTON_TWO:
				return true;
			default : 
				return false;
		}
	}
}
