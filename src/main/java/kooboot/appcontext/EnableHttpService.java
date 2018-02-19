package kooboot.appcontext;

import org.springframework.context.annotation.Import;

@Import(value=HttpServiceContext.class)
public @interface EnableHttpService {

}
