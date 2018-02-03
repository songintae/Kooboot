package kooboot.sqlservice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.oxm.Unmarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kooboot.appcontext.SqlServiceContext;
import kooboot.sqlservice.definition.SqlReader;
import kooboot.sqlservice.definition.SqlRegistry;
import kooboot.sqlservice.exception.SqlRetrievalFailureException;
import kooboot.sqlservice.implement.BaseSqlService;
import kooboot.sqlservice.implement.EmbeddedDBSqlRegistry;
import kooboot.sqlservice.implement.OxmSqlReader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SqlServiceContext.class)
public class SqlServiceTest {
	
	
	@Autowired
	Unmarshaller unmarshaller;
	
	TestSqlService sqlService;
	EmbeddedDatabase db;
	
	static class TestSqlService extends BaseSqlService{
		
		public TestSqlService(SqlReader sqlReader, SqlRegistry sqlRegistry){
			super();
			setSqlReader(sqlReader);
			setSqlRegistry(sqlRegistry);
		}
	}
	
	@Before
	public void setUp(){
		OxmSqlReader sqlReader = new OxmSqlReader();
		sqlReader.setUnmarshaller(unmarshaller);
		sqlReader.setSqlmapFile("sqlmap.xml");
		sqlReader.setSqlmapPath("kooboot/sqlservice/test/");
		EmbeddedDBSqlRegistry sqlRegistry = new EmbeddedDBSqlRegistry();
		db = new EmbeddedDatabaseBuilder()
				.setType(HSQL)
				.addScript("classpath:kooboot/sqlservice/definition/schema.sql")
				.build();
		sqlRegistry.setDataSource(db);
		sqlService = new TestSqlService(sqlReader,sqlRegistry);
		sqlService.loadSql();
	}
	
	@Test
	public void getSql(){
		assertThat(sqlService.getSql("test"),is("test"));
		assertThat(sqlService.getSql("test1"),is("test1"));
	}
	
	@Test(expected=SqlRetrievalFailureException.class)
	public void getSqlException(){
		assertThat(sqlService.getSql("test2"),is("test2"));
	}
	@After
	public void tearDown(){
		db.shutdown();
	}
	
	
}
