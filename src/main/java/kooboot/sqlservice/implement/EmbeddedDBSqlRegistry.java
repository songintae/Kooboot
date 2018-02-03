package kooboot.sqlservice.implement;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import kooboot.sqlservice.definition.SqlRegistry;
import kooboot.sqlservice.exception.SqlNotFoundException;

public class EmbeddedDBSqlRegistry implements SqlRegistry{
	
	
	private JdbcTemplate jdbc;
	
	public void setDataSource(DataSource datasource){
		this.jdbc = new JdbcTemplate(datasource);
	}
	@Override
	public void registerSql(String key, String sql) {
		// TODO Auto-generated method stub
		jdbc.update("INSERT INTO SQLMAP(KEY_ , SQL_) VALUES(?,?)",key,sql);
	}

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		// TODO Auto-generated method stub
		try{
			return jdbc.queryForObject("SELECT SQL_ FROM SQLMAP WHERE KEY_ = ?", String.class,key);
		}catch(EmptyResultDataAccessException e){
			throw new SqlNotFoundException(key + "에 해당하는 sql을 찾을 수 없습니다.");
		}
		
	}

}
