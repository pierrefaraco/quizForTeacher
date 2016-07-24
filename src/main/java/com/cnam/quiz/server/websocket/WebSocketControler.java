package com.cnam.quiz.server.websocket;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/")
public class WebSocketControler {

	  @RequestMapping(method = RequestMethod.GET)
	  public String viewApplication() {
	    return "index";
	  }
	    
	  @MessageMapping("/chat")
	  @SendTo("/topic/message")
	  public OutputMessage sendMessage(Message message) {
		    System.out.println("message to difuse : " + message.getMessage());
	    return new OutputMessage(message, new Date());
	
	  }
	
}
