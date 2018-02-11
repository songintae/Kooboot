package kooboot.search.domain;

import kooboot.response.domain.ResponseMessage;

public interface KakaoSearchService {
	public ResponseMessage doSearchRequest(String keyword);
}
