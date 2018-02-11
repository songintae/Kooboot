package kooboot.search.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kooboot.appcontext.AppContext;
import kooboot.search.domain.KakaoSearchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class searchTest {
	
	@Autowired
	KakaoSearchService keywordSerarchService;
	
	@Test
	public void keywordDoSearchRequest(){
		keywordSerarchService.doSearchRequest("드람브르");
	}

}
