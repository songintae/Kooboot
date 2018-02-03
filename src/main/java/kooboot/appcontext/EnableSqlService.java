package kooboot.appcontext;

import org.springframework.context.annotation.Import;

import kooboot.sqlservice.definition.SqlService;

@Import(value=SqlServiceContext.class)
public @interface EnableSqlService {

}
