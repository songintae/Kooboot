package kooboot.translate.implement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import kooboot.httpservice.domain.HttpService;
import kooboot.httpservice.exception.HttpServiceException;
import kooboot.translate.domain.PapagoResponse;
import kooboot.translate.domain.TranslateCode;
import kooboot.translate.domain.TranslateService;
import kooboot.translate.exception.TranslateException;

public class PapagoService implements TranslateService {

	//property 파일에서 필요한 정보를 받아온다.
	@Value("${translate.url}")
	String translateUrl;
	@Value("${translate.clientid}")
	String translateClientId;
	@Value("${translate.clientsecret}")
	String translateSecretId;

	private HttpService httpService;
	private Map<String, String> header;
	private Map<String, String> param;

	//미리 만든 HttpService를 사용.
	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}

	//객체 생성후 바로 실행되는 함수.
	@PostConstruct
	private void setInitialize() {
		//네이버에 RestAPI를 요청하기 위해 해더 생성.
		header = new HashMap<String, String>();
		param = new HashMap<String, String>();
		header.put("X-Naver-Client-Id", translateClientId);
		header.put("X-Naver-Client-Secret", translateSecretId);
	}

	@Override
	public String translateSentence(String source, String target, String sentence) {
		// TODO Auto-generated method stub
		try {
			param.put("source", source);
			param.put("target", target);
			param.put("text", sentence);
			//기존에 만든 HttpService를 통해 Post 방식으로 요청을 보낸다.
			return parseResponse(httpService.doHttpPostByUrlencoded(translateUrl, header, param)).getTranslatedText();
		} catch (HttpServiceException | TranslateException e) {
			throw new TranslateException(e);
		}

	}
	
	//네이버 Papago API 요청을 받아와 객체로 변환해준다.
	private PapagoResponse parseResponse(String response) throws TranslateException {
		PapagoResponse papagoResponse = null;
		try {
			papagoResponse = new PapagoResponse();
			JSONObject obj = (JSONObject) new JSONParser().parse(response);
			Iterator itr = obj.keySet().iterator();
			String key = null;
			while (itr.hasNext()) {
				key = itr.next().toString();
				if (key.equalsIgnoreCase("message")) {

					JSONObject message = (JSONObject) obj.get(key);
					papagoResponse.setType((String) message.get("@type"));
					papagoResponse.setService((String) message.get("@service"));
					papagoResponse.setVersion((String) message.get("@version"));

					JSONObject result = (JSONObject) message.get("result");
					papagoResponse.setTranslatedText((String) result.get("translatedText"));
					papagoResponse.setSrcLangType((String) result.get("srcLangType"));
				}

			}
			return papagoResponse;
		} catch (Exception e) {
			throw new TranslateException(e);
		}
	}

}
