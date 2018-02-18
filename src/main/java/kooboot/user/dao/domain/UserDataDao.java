package kooboot.user.dao.domain;

import org.springframework.transaction.annotation.Transactional;

import kooboot.user.domain.UserData;

public interface UserDataDao {
	
	public void insertUserData(UserData userData);
	
	//Test Support function
	public int getCount();
	public void deleteAll();
}
