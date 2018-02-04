package kooboot.prepostservice.implement;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import kooboot.user.domain.User;
import kooboot.user.service.domain.UserService;

public class PostProcessService {
	
	
	private UserService userService;
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	public void postProcess(User user){
		try{
			userService.addUser(user);
			userService.addUserData(user.getReqUserData());
		}catch(DuplicateKeyException e){
			userService.addUserData(user.getReqUserData());
		}
	}
}
