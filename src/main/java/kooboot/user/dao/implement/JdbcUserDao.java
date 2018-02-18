package kooboot.user.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import kooboot.sqlservice.definition.SqlService;
import kooboot.user.dao.domain.UserDao;
import kooboot.user.domain.User;
import kooboot.user.domain.status.Status;
import kooboot.user.domain.status.StatusCode;

public class JdbcUserDao implements UserDao {
	
	private RowMapper<User> userMapper = new RowMapper<User>(){

		@Override
		public User mapRow(ResultSet rs, int row) throws SQLException {
			// TODO Auto-generated method stub
			User user = new User();
			user.setUserKey(rs.getString("USER_KEY"));
			user.setStatus(StatusCode.valueOfCode(rs.getString("STATUS")));
			user.setSubStatus(rs.getString("SUB_STATUS"));
			user.setLastReqTime(rs.getString("LAST_REQ_TIME"));
			return user;
		}};
	
	private JdbcTemplate jdbcTemplate;
	private SqlService sqlService;
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setSqlService(SqlService sqlService){
		this.sqlService = sqlService;
	}
	
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(sqlService.getSql("insertUser"),
				user.getUserKey(),
				user.getStatusCode().getValue(),
				user.getSubStatusValue());
		
	}

	@Override
	public User selectUser(String userKey) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(sqlService.getSql("selectUser"), userMapper, userKey,userKey);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(sqlService.getSql("getUserCount"), new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				rs.next();
				return rs.getInt(1);
			}
		});
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		jdbcTemplate.update(sqlService.getSql("deleteAllUser"));
		
	}

	@Override
	public void updatdUser(User user) {
		// TODO Auto-generated method stub
		jdbcTemplate.update(sqlService.getSql("updateUser"),
				user.getStatusCode().getValue(),
				user.getSubStatusValue(),
				user.getUserKey());
	}
	
	

}
