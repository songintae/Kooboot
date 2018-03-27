package kooboot.search.domain.parse;

import kooboot.response.domain.ResponseMessage;

public interface SearchParser {
	ResponseMessage parseSearchResult(Object arg);
}
