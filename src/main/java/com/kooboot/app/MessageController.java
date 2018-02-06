package com.kooboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kooboot.prepost.implement.PostProcessService;
import kooboot.prepost.implement.PreProcessService;
import kooboot.request.domain.RequestMessage;
import kooboot.response.domain.Message;
import kooboot.response.domain.ResponseMessage;
import kooboot.user.domain.User;



@Controller
public class MessageController {
	
	@Autowired
	PreProcessService preProcessService;
	
	@Autowired
	PostProcessService postProcessService;
	
	
	@RequestMapping(value = "/message", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
	public @ResponseBody ResponseMessage message(@RequestBody RequestMessage requestMessage) throws Exception { 
		User user = preProcessService.preProcess(requestMessage);
		postProcessService.postProcess(user);
		Message message = new Message();
		message.setText(user.getStatus().getStatusCode().getValue());
		ResponseMessage responseMessage = new ResponseMessage(message,null);
		return responseMessage;
	}
}