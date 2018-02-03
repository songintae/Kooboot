package kooboot.user.dao.definition;

import org.springframework.transaction.annotation.Transactional;

import kooboot.user.domain.User;

public interface UserDao {

	public void insertUser(User user);
	public User selectUser(String userKey);
	
	//Test Support Function
	public int getCount();
	public void deleteAll();
}
