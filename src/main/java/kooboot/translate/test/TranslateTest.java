package kooboot.translate.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

import kooboot.appcontext.AppContext;
import kooboot.translate.domain.TranslateCode;
import kooboot.translate.domain.TranslateService;
import kooboot.translate.implement.PapagoResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppContext.class)
public class TranslateTest {
	
	@Autowired
	TranslateService papagoService;
	
	@Test
	public void translateSentence(){
		assertThat(papagoService.translateSentence(TranslateCode.KO_TO_EN.getSource(),
				TranslateCode.KO_TO_EN.getTarget(),"안녕하세요?"),is("Hello?"));
	}
}
