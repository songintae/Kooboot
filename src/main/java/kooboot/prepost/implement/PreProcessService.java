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
	
	public User initializeUser(RequestMessage message){
		User user = null;
		UserData reqUserData = new UserData(message.getUser_key(),
				message.getType(),
				message.getContent());
		try{
			user = userService.getUser(message.getUser_key());
			user.setReqUserData(reqUserData);
			
			if(delayRequestCheck(user.getLastReqTime())){
				user.setStatus(StatusCode.INIT);
				if(keyboardByPassCheck(user))	
					user.setSubStatus(InitialstateCode.INIT.getValue());
				else			
					user.setSubStatus(InitialstateCode.DELAY.getValue());

			}
			
		}catch(EmptyResultDataAccessException e){
			user = new User(message.getUser_key(),
					new Status(StatusCode.INIT),
					reqUserData);
		}
		
		return user;
	}
	
	
	public boolean delayRequestCheck(String reqTime){
		Date checkTime = new Date();
		checkTime.setMinutes(checkTime.getMinutes() -5);
		if(DateUtil.getDateYyyymmddhhmmss(checkTime).compareTo(reqTime) >=0)
			return true;
		else
			return false;
	}
	
	
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
