package com.kooboot.app;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kooboot.httpservice.domain.HttpService;
import kooboot.prepost.implement.PostProcessService;
import kooboot.prepost.implement.PreProcessService;
import kooboot.process.implement.KakaoContext;
import kooboot.request.domain.RequestMessage;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.user.domain.User;



@Controller
public class MessageController {
	
	
	@Autowired
	KakaoContext kakaoContext;
	
	@RequestMapping(value = "/message", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	public @ResponseBody ResponseMessage message(@RequestBody RequestMessage requestMessage) throws Exception { 
		return kakaoContext.KakaoProcessTemplate(requestMessage);
	}

}