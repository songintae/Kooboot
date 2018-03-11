package kooboot.search.test;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.helpers.ISO8601DateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kooboot.appcontext.AppContext;
import kooboot.search.domain.KakaoSearchService;
import kooboot.search.domain.Response;
import kooboot.search.domain.book.BookDocument;
import kooboot.search.domain.book.BookResponse;
import kooboot.util.DateUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class searchTest {
	
	@Autowired
	KakaoSearchService keywordSearchService;
	
	@Test
	public void keywordDoSearchRequest() throws ParseException{
		String text = "말자루 글자루 59-<b>스케이트</b> 타는 고양이 모리(외래어 알기) - Daum 책";
		System.out.println(replaceHtmlTagWithBlank(text));
	}
	
	private String replaceHtmlTagWithBlank(String value){
		 return value.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>","");

	}
	
}
