package kooboot.user.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import kooboot.sqlservice.definition.SqlService;
import kooboot.user.dao.domain.UserDataDao;
import kooboot.user.domain.UserData;

public class JdbcUserDataDao implements UserDataDao {

	
	private JdbcTemplate jdbcTemplate;
	private SqlService sqlService;
	
	public void setDataSource(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setSqlService(SqlService sqlService){
		this.sqlService = sqlService;
	}
	
	@Override
	public void insertUserData(UserData userData) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(sqlService.getSql("insertUserData"),
				userData.getUserKey(),
				userData.getType(),
				userData.getContents());
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(sqlService.getSql("getUserDataCount"), new ResultSetExtractor<Integer>(){

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				rs.next();
				return rs.getInt(1);
			}});
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		jdbcTemplate.update(sqlService.getSql("deleteAllUserData"));
	}
	
}
