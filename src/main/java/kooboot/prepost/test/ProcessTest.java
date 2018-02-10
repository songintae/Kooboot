package kooboot.prepost.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.UnexpectedRollbackException;

import kooboot.appcontext.AppContext;
import kooboot.prepost.implement.PostProcessService;
import kooboot.prepost.implement.PreProcessService;
import kooboot.request.domain.RequestMessage;
import kooboot.user.dao.definition.UserDao;
import kooboot.user.dao.definition.UserDataDao;
import kooboot.user.domain.User;
import kooboot.user.domain.UserData;
import kooboot.user.domain.status.Status;
import kooboot.user.domain.status.StatusCode;
import kooboot.user.service.implement.BasicUserService;
import kooboot.util.Constant;
import kooboot.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class ProcessTest{
	
	PreProcessService preProcess;
	
	@Autowired
	PostProcessService postProcess;
	
	static User user1;
	static User user2;
	UserData userData1;
	UserData userData2;
	
	@Before
	public void setUp(){
		
		preProcess = new PreProcessService();
		preProcess.setUserService(new TestUserService());
		user1 = new User();
		user1.setUserKey("key1");
		user1.setStatus(StatusCode.INIT);
		Date reqTime1 = new Date();
		reqTime1.setMinutes(reqTime1.getMinutes()-5);
		user1.setLastReqTime(DateUtil.getDateYyyymmddhhmmss(reqTime1));
		
		user2 = new User();
		user2.setUserKey("key2");
		user2.setStatus(StatusCode.TRANSLATE);
		user2.setSubStatus("1");
		Date reqTime2 = new Date();
		reqTime2.setMinutes(reqTime2.getMinutes()-4);
		user2.setLastReqTime(DateUtil.getDateYyyymmddhhmmss(reqTime2));
		
		
		userData1 = new UserData();
		userData1.setUserKey(user1.getUserKey());
		userData1.setType("text");
		userData1.setContents("key1Test");
		
		userData2 = new UserData();
		userData2.setUserKey(user2.getUserKey());
		userData2.setType("button");
		userData2.setContents("key2Test");
		
		user1.setReqUserData(userData1);
		user2.setReqUserData(userData2);
	}
	
	public static class TestUserService extends BasicUserService{
		public User getUser(String userKey){
			if(user1.getUserKey().equals(userKey))
				return user1;
			else if(user2.getUserKey().equals(userKey))
				return user2;
			else
				throw new EmptyResultDataAccessException(1);
		}
	}
	
	@Test
	public void delayRequestCheck(){
		
		assertThat(preProcess.delayRequestCheck(user1.getLastReqTime()),is(true));
		assertThat(preProcess.delayRequestCheck(user2.getLastReqTime()),is(false));
	}
	
	@Test
	public void preProcess(){
		User getUser1 = preProcess.preProcess(new RequestMessage(user1.getUserKey(),"text","test1"));
		checkPreProcess(getUser1,StatusCode.INIT);
		User getUser2 = preProcess.preProcess(new RequestMessage(user2.getUserKey(),"text","test2"));
		checkPreProcess(getUser2,StatusCode.TRANSLATE);
		User getUser3 = preProcess.preProcess(new RequestMessage("key3","text","test3"));
		checkPreProcess(getUser3,StatusCode.INIT);
	}
	
	@Test
	public void trasactionRollbackTest(){
		postProcess.postProcess(user1);
		postProcess.postProcess(user1);
	}
	
	@Test
	public void keyboardByPassCheck(){
		 UserData testData = new UserData();
		 testData.setType("tpye");
		 testData.setContents(Constant.INIT_KEYBOARD_BUTTON_ONE);
		 user1.setReqUserData(testData);
		 assertThat(true, is(preProcess.keyboardByPassCheck(user1)));
		 testData.setContents("취소");
		 assertThat(false, is(preProcess.keyboardByPassCheck(user1)));
	}
	
	
	private void checkPreProcess(User checkUser, StatusCode expectedStatusCode){
		assertThat(checkUser.getStatusCode(),is(expectedStatusCode));
	}
}
