package kooboot.sqlservice.definition;

import kooboot.sqlservice.exception.SqlRetrievalFailureException;

public interface SqlService {
	String getSql(String key) throws SqlRetrievalFailureException;
}
