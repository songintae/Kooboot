package kooboot.user.service.implement;

import kooboot.user.dao.definition.UserDao;
import kooboot.user.dao.definition.UserDataDao;
import kooboot.user.domain.User;
import kooboot.user.domain.UserData;
import kooboot.user.service.domain.UserService;

public class BasicUserService implements UserService {

	private UserDao userDao;
	private UserDataDao userDataDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setUserDataDao(UserDataDao userDataDao) {
		this.userDataDao = userDataDao;
	}

	@Override
	public User getUser(String userKey) {
		// TODO Auto-generated method stub
		return userDao.selectUser(userKey);
	}

	@Override
	public void addUserAndData(User user) {
		// TODO Auto-generated method stub
		userDao.insertUser(user);
		userDataDao.insertUserData(user.getReqUserData());
	}

	@Override
	public void updateUserAndData(User user) {
		// TODO Auto-generated method stub
		userDao.updatdUser(user);
		userDataDao.insertUserData(user.getReqUserData());
		
	}



}
