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
import kooboot.translate.domain.PapagoResponse;
import kooboot.translate.domain.TranslateCode;
import kooboot.translate.domain.TranslateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContext.class)
public class TranslateTest {

	@Autowired
	TranslateService papagoService;

	@Test
	public void translateSentence() {
		assertThat(papagoService.translateSentence(TranslateCode.EN_TO_KO.getSource(),
				TranslateCode.EN_TO_KO.getTarget(), "Hello?"), is("여보세요?"));
	}
}
