package com.kooboot.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kooboot.response.domain.Keyboard;

/**
 * 사용자가 처음 방에 입장하면 표시되는 화면
 */
@Controller
public class KeyboardController {
	@RequestMapping(value = "/keyboard", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
	public @ResponseBody Keyboard keyboard() throws Exception { 
		Keyboard keyboard = new Keyboard();
		keyboard.setType("text");
		return keyboard;
	}
}
