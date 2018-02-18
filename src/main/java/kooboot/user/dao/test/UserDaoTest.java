package kooboot.user.dao.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kooboot.appcontext.AppContext;
import kooboot.translate.domain.TranslateCode;
import kooboot.user.dao.domain.UserDao;
import kooboot.user.dao.domain.UserDataDao;
import kooboot.user.domain.User;
import kooboot.user.domain.UserData;
import kooboot.user.domain.status.Status;
import kooboot.user.domain.status.StatusCode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class UserDaoTest {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserDataDao userDataDao;
	
	User user1 = null;
	User user2 = null;
	
	UserData userData1 = null;
	UserData userData2 = null;
	
	@Before
	public void setUp(){
		userDataDao.deleteAll();
		userDao.deleteAll();
		user1 = new User();
		user1.setUserKey("key1");
		user1.setStatus(StatusCode.INIT);
		
		user2 = new User();
		user2.setUserKey("key2");
		user2.setStatus(StatusCode.TRANSLATE);
		user2.setSubStatus(TranslateCode.KO_TO_EN.getValue());
		userData1 = new UserData();
		userData1.setUserKey(user1.getUserKey());
		userData1.setType("text");
		userData1.setContents("key1Test");
		
		userData2 = new UserData();
		userData2.setUserKey(user2.getUserKey());
		userData2.setType("button");
		userData2.setContents("key2Test");
	}
	
	@Test
	public void insertUser(){
		assertThat(userDao.getCount(), is(0));
		userDao.insertUser(user1);
		userDao.insertUser(user2);
		assertThat(userDao.getCount(), is(2));
	}
	
	@Test
	public void selectUser(){
		assertThat(userDao.getCount(), is(0));
		userDao.insertUser(user1);
		userDao.insertUser(user2);
		assertThat(userDao.getCount(), is(2));
		
		User getUser1 = userDao.selectUser(user1.getUserKey());	
		checkSameUser(user1,getUser1);
		User getUser2 = userDao.selectUser(user2.getUserKey());	
		checkSameUser(user2,getUser2);
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void selectUserException(){
		
		User getUser1 = userDao.selectUser("test");
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void duplicateException(){
		userDao.insertUser(user1);
		userDao.insertUser(user1);
	}
	
	@Test
	public void insertUserData(){
		assertThat(userDao.getCount(), is(0));
		userDao.insertUser(user1);
		userDao.insertUser(user2);
		assertThat(userDao.getCount(), is(2));
		
		assertThat(userDataDao.getCount(), is(0));
		userDataDao.insertUserData(userData1);
		userDataDao.insertUserData(userData2);
		assertThat(userDataDao.getCount(), is(2));
	}
	
	
	private void checkSameUser(User expectedUser , User checkUser){
		assertThat(expectedUser.getUserKey(),is(checkUser.getUserKey()));
		assertThat(expectedUser.getStatusCode(),is(checkUser.getStatusCode()));
		assertThat(expectedUser.getSubStatusValue(),is(checkUser.getSubStatusValue()));
	}
	
}
