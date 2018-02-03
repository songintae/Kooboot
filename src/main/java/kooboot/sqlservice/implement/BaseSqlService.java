package kooboot.sqlservice.implement;

import javax.annotation.PostConstruct;

import kooboot.sqlservice.definition.SqlReader;
import kooboot.sqlservice.definition.SqlRegistry;
import kooboot.sqlservice.definition.SqlService;
import kooboot.sqlservice.exception.SqlNotFoundException;
import kooboot.sqlservice.exception.SqlRetrievalFailureException;


public class BaseSqlService implements SqlService{
	
	private SqlReader sqlReader;
	private SqlRegistry sqlRegistry;
	
	
	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}

	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}
	
	@PostConstruct
	public void loadSql(){
		sqlReader.read(sqlRegistry);
	}
	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		// TODO Auto-generated method stub
		try{
			return sqlRegistry.findSql(key);
		}catch(SqlNotFoundException e){
			throw new SqlRetrievalFailureException(e.getMessage());
		}
	}
}
