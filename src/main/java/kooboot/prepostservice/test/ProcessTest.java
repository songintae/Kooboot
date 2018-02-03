package kooboot.prepostservice.test;

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

import kooboot.appcontext.AppContext;
import kooboot.prepostservice.implement.PreProcessService;
import kooboot.request.domain.RequestMessage;
import kooboot.user.dao.definition.UserDao;
import kooboot.user.dao.definition.UserDataDao;
import kooboot.user.domain.User;
import kooboot.user.domain.UserData;
import kooboot.user.domain.status.Status;
import kooboot.user.domain.status.StatusCode;
import kooboot.user.service.implement.BasicUserService;
import kooboot.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class ProcessTest{
	
	PreProcessService preProcess;
	
	static User user1;
	static User user2;
	
	@Before
	public void setUp(){
		
		preProcess = new PreProcessService();
		preProcess.setUserService(new TestUserService());
		user1 = new User();
		user1.setUserKey("key1");
		user1.setStatus(new Status(StatusCode.INIT));
		Date reqTime1 = new Date();
		reqTime1.setMinutes(reqTime1.getMinutes()-5);
		user1.setLastReqTime(DateUtil.getDateYyyymmddhhmmss(reqTime1));
		
		user2 = new User();
		user2.setUserKey("key2");
		user2.setStatus(new Status(StatusCode.TRANSLATE,"1"));
		Date reqTime2 = new Date();
		reqTime2.setMinutes(reqTime2.getMinutes()-4);
		user2.setLastReqTime(DateUtil.getDateYyyymmddhhmmss(reqTime2));
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
	
	private void checkPreProcess(User checkUser, StatusCode expectedStatusCode){
		assertThat(checkUser.getStatus().getStatusCode(),is(expectedStatusCode));
	}
}
