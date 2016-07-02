package com.cnam.quiz.server.restfull;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cnam.quiz.common.dto.QuestionDto;
import com.cnam.quiz.common.dto.SequenceDto;
import com.cnam.quiz.common.dto.SessionQuizDto;
import com.cnam.quiz.common.dto.TopicDto;
import com.cnam.quiz.server.service.quiz.QuizService;

@RestController
public class quizRestWebService {
	
	 @Autowired
	 QuizService quizService;	 

	@RequestMapping(value = "/topic/{topicid}", method = RequestMethod.GET)
	public ResponseEntity<TopicDto> findTopic(@PathVariable("topicid") long id) {
		TopicDto topicDto =  quizService.findTopic(id);
		if (topicDto == null)
			return new ResponseEntity<TopicDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<TopicDto>(topicDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/topic/", method = RequestMethod.POST)
	public ResponseEntity<TopicDto>  createTopic(TopicDto topicDto) {
		topicDto.setId(-1);
		quizService.createTopic(topicDto);
		if (topicDto.getId() == -1)
			return new ResponseEntity<TopicDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<TopicDto>(topicDto,HttpStatus.valueOf("TOPIC NOT RECORDED"));
		
	}
	
	@RequestMapping(value = "/topic/", method = RequestMethod.PUT)
	public ResponseEntity updateTopic(TopicDto topicDto) {
		quizService.updateTopic(topicDto);
		TopicDto topic = quizService.findTopic(topicDto.getId());
		if (!topicDto.equals(topic))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity( HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/topic/{topicid}", method = RequestMethod.DELETE)
	public ResponseEntity deleteTopic(@PathVariable("topicid")long id) {
		quizService.deleteTopic(id);
		TopicDto topic = quizService.findTopic(id);
		if (topic == null)
			return new ResponseEntity( HttpStatus.OK);
		return new ResponseEntity( HttpStatus.NOT_MODIFIED );		
	}
	
	@RequestMapping(value = "/topic/", method = RequestMethod.GET)
	public  ResponseEntity<List<TopicDto>> findAllTopics() {
		List<TopicDto> topics = quizService.findAllTopics();
		if (topics == null)
			return new ResponseEntity<List<TopicDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TopicDto>>(topics, HttpStatus.OK);
	}
	
	@RequestMapping(value = "user/{userid}/topic/", method = RequestMethod.GET)
	public ResponseEntity<List<TopicDto>>  findTopicsByProfessor(@PathVariable("userid")long userId) {
		List<TopicDto> topics = quizService.findTopicsByProfessor(userId);
		if (topics == null)
			return new ResponseEntity<List<TopicDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TopicDto>>(topics, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/question/{questionid}", method = RequestMethod.GET)
	public ResponseEntity< QuestionDto> findQuestion(@PathVariable("questionid")long id) {
		QuestionDto questionDto = quizService.findQuestion(id);
		if (questionDto  == null)
			return new ResponseEntity<QuestionDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<QuestionDto>(questionDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/question/", method = RequestMethod.POST)
	public ResponseEntity<QuestionDto>createQuestion(QuestionDto questionDto) {
		questionDto.setId(-1);
		quizService.createQuestion(questionDto);
		if (questionDto.getId() == -1)
			return new ResponseEntity<QuestionDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<QuestionDto>(questionDto,HttpStatus.valueOf("TOPIC NOT RECORDED"));
		
	}
	
	@RequestMapping(value = "/question/", method = RequestMethod.PUT)
	public ResponseEntity<QuestionDto>  updateQuestion(QuestionDto questionDto) {
		quizService.updateQuestion(questionDto);
		QuestionDto question = quizService.findQuestion(questionDto.getId());
		if (!questionDto.equals(question))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity( HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/question/{questionid}", method = RequestMethod.DELETE)
	public ResponseEntity deleteQuestion(@PathVariable("questionid")long id) {
		quizService.deleteQuestion(id);
		QuestionDto question = quizService.findQuestion(id);
		if (question == null)
			return new ResponseEntity( HttpStatus.OK);
		return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		
	}
	
	public List<QuestionDto> findQuestionsByTopic(long topicId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public SequenceDto findSequence(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void createSequence(SequenceDto sequenceDto) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateSequence(SequenceDto sequenceDto) {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteSequence(long id) {
		// TODO Auto-generated method stub
		
	}
	
	public List<SequenceDto> findSequenceByProfessor(long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addQuestionToSequence(long sequenceId, long questionId, int pos) {
		// TODO Auto-generated method stub
		
	}
	
	public void removeQuestionFromSequence(long sequenceId, int pos) {
		// TODO Auto-generated method stub
		
	}
	
	public SessionQuizDto findSessionQuiz(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void createSessionQuiz(SessionQuizDto sessionDto) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateSessionQuiz(SessionQuizDto sessionDto) {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteSessionQuiz(long id) {
		// TODO Auto-generated method stub
		
	}
	
	public List<SessionQuizDto> findSessionQuizByCours(long coursId) {
		// TODO Auto-generated method stub
		return null;
	}
 
  
}
