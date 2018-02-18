package kooboot.prepost.implement;

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
			//User 객체 저장.
			userService.addUserAndData(user);
		}catch(DuplicateKeyException e){
			//이미 저장되어 있는경우 User객체 갱신.
			userService.updateUserAndData(user);
		}
	}
}
