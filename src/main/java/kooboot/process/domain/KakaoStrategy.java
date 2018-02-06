package kooboot.process.domain;

import kooboot.user.domain.User;

public interface KakaoStrategy {
	public StrategyResult doProcessSerivce(User user); 
}
