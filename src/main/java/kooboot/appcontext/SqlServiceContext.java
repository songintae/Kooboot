package kooboot.appcontext;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import kooboot.sqlservice.definition.SqlReader;
import kooboot.sqlservice.definition.SqlRegistry;
import kooboot.sqlservice.definition.SqlService;
import kooboot.sqlservice.implement.BaseSqlService;
import kooboot.sqlservice.implement.EmbeddedDBSqlRegistry;
import kooboot.sqlservice.implement.OxmSqlReader;

@Configuration
public class SqlServiceContext {
	
	@Bean
	public SqlService sqlService(){
		BaseSqlService sqlService = new BaseSqlService();
		sqlService.setSqlReader(sqlReader());
		sqlService.setSqlRegistry(sqlRegistry());
		return sqlService;
	}
	
	@Bean
	public SqlReader sqlReader(){
		OxmSqlReader sqlReader = new OxmSqlReader();
		sqlReader.setUnmarshaller(unmarshaller());
		return sqlReader;
	}
	
	@Bean
	public SqlRegistry sqlRegistry(){
		EmbeddedDBSqlRegistry sqlRegistry = new EmbeddedDBSqlRegistry();
		sqlRegistry.setDataSource(embeddedDatabase());
		return sqlRegistry;
	}
	
	@Bean
	public Unmarshaller unmarshaller(){
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("kooboot.sqlservice.implement.jaxb");
		return marshaller;
	}
	
	@Bean
	public DataSource embeddedDatabase(){
		return new EmbeddedDatabaseBuilder()
				.setName("embeddedDatabase")
				.setType(HSQL)
				.addScript("classpath:kooboot/sqlservice/definition/schema.sql")
				.build();
	}
}
