package kooboot.user.service.domain;

import org.springframework.transaction.annotation.Transactional;

import kooboot.user.domain.User;
import kooboot.user.domain.UserData;

public interface UserService {
	
	@Transactional(readOnly=true)
	public User getUser(String userKey);
	@Transactional
	public void addUser(User user);
	@Transactional
	public void addUserData(UserData userData);
}
