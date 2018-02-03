package kooboot.sqlservice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kooboot.appcontext.SqlServiceContext;
import kooboot.sqlservice.exception.SqlNotFoundException;
import kooboot.sqlservice.implement.EmbeddedDBSqlRegistry;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SqlServiceContext.class)
public class SqlRegistryTest {
	EmbeddedDBSqlRegistry sqlRegistry;
	
	EmbeddedDatabase db;
	
	@Before
	public void setUp(){
		sqlRegistry = new EmbeddedDBSqlRegistry();
		db = new EmbeddedDatabaseBuilder()
				.setType(HSQL)
				.addScript("classpath:kooboot/sqlservice/definition/schema.sql")
				.build();
		sqlRegistry.setDataSource(db);
		sqlRegistry.registerSql("test", "test");
		sqlRegistry.registerSql("test1", "test1");
	}
	
	@Test
	public void findSqlTest(){
		assertThat(sqlRegistry.findSql("test"),is("test"));
		assertThat(sqlRegistry.findSql("test1"),is("test1"));
	}
	
	@Test(expected=SqlNotFoundException.class)
	public void findSqlException(){
		assertThat(sqlRegistry.findSql("test2"),is("test2"));
	}
	
	@After
	public void tearDown(){
		db.shutdown();
	}
}
