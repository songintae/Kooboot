package kooboot.appcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kooboot.httpservice.domain.HttpService;
import kooboot.httpservice.implement.UrlHttpService;
import kooboot.httpservice.implement.UrlHttpTemplate;

@Configuration
public class HttpServiceContext {
	
	@Bean
	public HttpService httpService(){
		UrlHttpService httpService = new UrlHttpService();
		httpService.setUrlHttpTemplate(new UrlHttpTemplate());
		return httpService;
	}
}
