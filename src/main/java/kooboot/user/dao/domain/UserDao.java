package kooboot.user.dao.domain;

import org.springframework.transaction.annotation.Transactional;

import kooboot.user.domain.User;

public interface UserDao {

	public void insertUser(User user);
	public User selectUser(String userKey);
	public void updatdUser(User user);
	//Test Support Function
	public int getCount();
	public void deleteAll();
}
