package kooboot.appcontext;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.jdbc.Driver;

import kooboot.httpservice.domain.HttpService;
import kooboot.httpservice.implement.UrlHttpService;
import kooboot.httpservice.implement.UrlHttpTemplate;
import kooboot.initialstate.implement.InitialstateStrategy;
import kooboot.prepost.implement.PostProcessService;
import kooboot.prepost.implement.PreProcessService;
import kooboot.process.domain.KakaoStrategy;
import kooboot.process.implement.KakaoContext;
import kooboot.search.domain.KakaoSearchService;
import kooboot.search.implement.BookSearchService;
import kooboot.search.implement.KeywordSearchService;
import kooboot.search.implement.SearchStrategy;
import kooboot.search.implement.WebSearchService;
import kooboot.sqlservice.definition.SqlService;
import kooboot.translate.domain.TranslateService;
import kooboot.translate.implement.PapagoService;
import kooboot.translate.implement.TranslateStrategy;
import kooboot.user.dao.domain.UserDao;
import kooboot.user.dao.domain.UserDataDao;
import kooboot.user.dao.implement.JdbcUserDao;
import kooboot.user.dao.implement.JdbcUserDataDao;
import kooboot.user.service.domain.UserService;
import kooboot.user.service.implement.BasicUserService;

@Configuration
@ComponentScan(basePackages="kooboot")
@EnableSqlService
@EnableTransactionManagement
@EnableHttpService
@PropertySources({
	@PropertySource("classpath:kooboot/appcontext/database.properties"),
	@PropertySource("classpath:kooboot/translate/domain/serviceinfo.properties"),
	@PropertySource("classpath:kooboot/search/domain/serviceinfo.properties")
})

public class AppContext {
	
	@Value("${db.driverClass}") Class<? extends Driver> driverClass;
	@Value("${db.url}") String url;
	@Value("${db.username}") String username;
	@Value("${db.password}") String password;
	
	
	@Autowired
	SqlService sqlService;
	
	@Autowired
	HttpService httpService;
	
	@Bean
	public KakaoContext kakaoContext(){
		return new KakaoContext();
	}
	
	
	@Bean
	public KakaoStrategy initialstateStrategy(){
		return new InitialstateStrategy();
	}
	
	@Bean
	public KakaoStrategy searchStrategy(){
		SearchStrategy searchStrategy = new SearchStrategy();
		return searchStrategy;
	}
	
	@Bean 
	public KakaoSearchService keywordSearchService(){
		KeywordSearchService keywordSearchService = new KeywordSearchService();
		keywordSearchService.setHttpService(httpService);
		return keywordSearchService;
	}
	
	@Bean 
	public KakaoSearchService webSearchService(){
		WebSearchService webSearchService = new WebSearchService();
		webSearchService.setHttpService(httpService);
		return webSearchService;
	}
	
	@Bean 
	public KakaoSearchService bookSearchService(){
		BookSearchService bookSearchService = new BookSearchService();
		bookSearchService.setHttpService(httpService);
		return bookSearchService;
	}
	
	@Bean
	public KakaoStrategy translateStrategy(){
		TranslateStrategy translateStrategy = new TranslateStrategy();
		translateStrategy.setTranslateService(translateService());
		return translateStrategy;
	}
	
	
	@Bean
	public TranslateService translateService(){
		PapagoService papagoService = new PapagoService();
		papagoService.setHttpService(httpService);
		return papagoService;
	}
	
	
	@Bean
	public PostProcessService postProcessService(){
		PostProcessService postProcess = new PostProcessService();
		postProcess.setUserService(userService());
		return postProcess;
	}
	
	
	@Bean
	public PreProcessService preProcessService(){
		PreProcessService preProcess = new PreProcessService();
		preProcess.setUserService(userService());
		return preProcess;
	}
	
	
	@Bean
	public UserService userService(){
		BasicUserService userService = new BasicUserService();
		userService.setUserDao(userDao());
		userService.setUserDataDao(userDataDao());
		return userService;
		
	}
	
	@Bean
	public UserDao userDao(){
		JdbcUserDao jdbcUserDao = new JdbcUserDao();
		jdbcUserDao.setDataSource(dataSource());
		jdbcUserDao.setSqlService(sqlService);
		return jdbcUserDao;
	}
	
	@Bean
	public UserDataDao userDataDao(){
		JdbcUserDataDao jdbcUserDataDao = new JdbcUserDataDao();
		jdbcUserDataDao.setDataSource(dataSource());
		jdbcUserDataDao.setSqlService(sqlService);
		return jdbcUserDataDao;
	} 
	
	
	@Bean
	public PlatformTransactionManager transactionManager(){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}
	
	@Bean
	public DataSource dataSource(){
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
}
