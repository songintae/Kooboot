package kooboot.sqlservice.definition;

import kooboot.sqlservice.exception.SqlNotFoundException;

public interface SqlRegistry {
	void registerSql(String key , String sql);
	String findSql(String key) throws SqlNotFoundException;
}
