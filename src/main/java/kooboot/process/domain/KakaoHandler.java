package kooboot.process.domain;

import kooboot.user.domain.User;

public interface KakaoHandler {
	public StrategyResult execute(User user); 
}
