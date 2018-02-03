package kooboot.prepostservice.implement;

import java.util.Date;

import org.springframework.dao.EmptyResultDataAccessException;

import kooboot.request.domain.RequestMessage;
import kooboot.user.domain.User;
import kooboot.user.domain.UserData;
import kooboot.user.domain.status.Status;
import kooboot.user.domain.status.StatusCode;
import kooboot.user.service.domain.UserService;
import kooboot.util.DateUtil;

public class PreProcessService {
	
	private UserService userService;
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	public User preProcess(RequestMessage message){
		User user = null;
		UserData reqUserData = new UserData(message.getUser_key(),
				message.getType(),
				message.getContent());
		try{
			user = userService.getUser(message.getUser_key());
			user.setReqUserData(reqUserData);
			
			if(delayRequestCheck(user.getLastReqTime()))
				user.setStatus(new Status(StatusCode.INIT));
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
}
