package com.cnam.quiz.server.websocket;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cnam.quiz.common.dto.QuestionDto;
import com.cnam.quiz.server.service.quiz.QuizService;
@Controller
@RequestMapping("/")
public class WebSocketControler {

		
		@Autowired
		QuizService quizService;
		
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
	  
	  
	  @MessageMapping("/question")
	  @SendTo("/topic/show")
	  public QuestionDto sendQuestion(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	
}
