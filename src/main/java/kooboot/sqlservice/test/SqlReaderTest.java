package kooboot.sqlservice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kooboot.appcontext.SqlServiceContext;
import kooboot.sqlservice.definition.SqlRegistry;
import kooboot.sqlservice.exception.SqlNotFoundException;
import kooboot.sqlservice.implement.OxmSqlReader;
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SqlServiceContext.class)
public class SqlReaderTest {
	
	@Autowired
	Unmarshaller unmarshaller;
	OxmSqlReader sqlReader;
	SqlRegistry sqlRegistry;
	
	static class TestSqlRegistry implements SqlRegistry{
		
		private Map<String,String> sqlmap = new HashMap<String,String>();
		@Override
		public void registerSql(String key, String sql) {
			// TODO Auto-generated method stub
			sqlmap.put(key, sql);
		}

		@Override
		public String findSql(String key) throws SqlNotFoundException {
			// TODO Auto-generated method stub
			return sqlmap.get(key);
		}
		
	}
	
	@Before
	public void setUp(){
		sqlRegistry = new TestSqlRegistry();
		sqlReader = new OxmSqlReader();
		sqlReader.setUnmarshaller(unmarshaller);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void readException(){
		sqlReader.setSqlmapFile("test.xml");
		sqlReader.read(sqlRegistry);
		
	}
	
	@Test
	public void readTest(){
		sqlReader.setSqlmapFile("sqlmap.xml");
		sqlReader.setSqlmapPath("kooboot/sqlservice/test/");
		sqlReader.read(sqlRegistry);
		assertThat(sqlRegistry.findSql("test"),is("test"));
		assertThat(sqlRegistry.findSql("test1"),is("test1"));

	}
	

}
