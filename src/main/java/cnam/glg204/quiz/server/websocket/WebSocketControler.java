package cnam.glg204.quiz.server.websocket;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cnam.glg204.quiz.common.dto.QuestionDto;
import cnam.glg204.quiz.server.service.quiz.QuizService;
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
	  
	  @MessageMapping("/cours0")
	  @SendTo("/auditors/cours0")
	  public QuestionDto sendQuestionForCours0(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	  
	  @MessageMapping("/cours1")
	  @SendTo("/auditors/cours1")
	  public QuestionDto sendQuestionForCours1(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	  
	  
	  @MessageMapping("/cours2")
	  @SendTo("/auditors/cours2")
	  public QuestionDto sendQuestionForCours2(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	  
	  @MessageMapping("/cours3")
	  @SendTo("/auditors/cours3")
	  public QuestionDto sendQuestionForCours3(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	  
	  @MessageMapping("/cours4")
	  @SendTo("/auditors/cours4")
	  public QuestionDto sendQuestionForCours4(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	  
	  @MessageMapping("/cours5")
	  @SendTo("/auditors/cours5")
	  public QuestionDto sendQuestionForCours5(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	  
	  @MessageMapping("/cours6")
	  @SendTo("/auditors/cours6")
	  public QuestionDto sendQuestionForCours6(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	  
	  
	  @MessageMapping("/cours7")
	  @SendTo("/auditors/cours7")
	  public QuestionDto sendQuestionForCours7(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	  
	  @MessageMapping("/cours8")
	  @SendTo("/auditors/cours8")
	  public QuestionDto sendQuestionForCours8(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	  
	  @MessageMapping("/cours9")
	  @SendTo("/auditors/cours9")
	  public QuestionDto sendQuestionForCours9(Message message) {
		System.out.println(message.getId());
	    QuestionDto question = quizService.findQuestion(message.getId());
		System.out.println(question.getQuestion());
	    return question;
	  }
	  

	  
	  
	  
	
}
