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
public class QuizRestWebService {
	
	 @Autowired
	 QuizService quizService;	 

	@RequestMapping(value = "/professor/topic/{topicid}", method = RequestMethod.GET)
	public ResponseEntity<TopicDto> findTopic(@PathVariable("topicid") long id) {
		TopicDto topicDto =  quizService.findTopic(id);
		if (topicDto == null)
			return new ResponseEntity<TopicDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<TopicDto>(topicDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/topic/", method = RequestMethod.POST)
	public ResponseEntity<TopicDto>  createTopic(TopicDto topicDto) {
		topicDto.setId(-1);
		quizService.createTopic(topicDto);
		if (topicDto.getId() == -1)
			return new ResponseEntity<TopicDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<TopicDto>(topicDto,HttpStatus.valueOf("TOPIC NOT RECORDED"));
		
	}
	
	@RequestMapping(value = "/professor/topic/", method = RequestMethod.PUT)
	public ResponseEntity updateTopic(TopicDto topicDto) {
		quizService.updateTopic(topicDto);
		TopicDto topic = quizService.findTopic(topicDto.getId());
		if (!topicDto.equals(topic))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity( HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/professor/topic/{topicid}", method = RequestMethod.DELETE)
	public ResponseEntity deleteTopic(@PathVariable("topicid")long id) {
		quizService.deleteTopic(id);
		TopicDto topic = quizService.findTopic(id);
		if (topic == null)
			return new ResponseEntity( HttpStatus.OK);
		return new ResponseEntity( HttpStatus.NOT_MODIFIED );		
	}
	
	@RequestMapping(value = "/professor/topic/", method = RequestMethod.GET)
	public  ResponseEntity<List<TopicDto>> findAllTopics() {
		List<TopicDto> topics = quizService.findAllTopics();
		if (topics == null)
			return new ResponseEntity<List<TopicDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TopicDto>>(topics, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/user/{userid}/topic/", method = RequestMethod.GET)
	public ResponseEntity<List<TopicDto>>  findTopicsByProfessor(@PathVariable("userid")long userId) {
		List<TopicDto> topics = quizService.findTopicsByProfessor(userId);
		if (topics == null)
			return new ResponseEntity<List<TopicDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TopicDto>>(topics, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/question/{questionid}", method = RequestMethod.GET)
	public ResponseEntity< QuestionDto> findQuestion(@PathVariable("questionid")long id) {
		QuestionDto questionDto = quizService.findQuestion(id);
		if (questionDto  == null)
			return new ResponseEntity<QuestionDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<QuestionDto>(questionDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/question/", method = RequestMethod.POST)
	public ResponseEntity<QuestionDto>createQuestion(QuestionDto questionDto) {
		questionDto.setId(-1);
		quizService.createQuestion(questionDto);
		if (questionDto.getId() == -1)
			return new ResponseEntity<QuestionDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<QuestionDto>(questionDto,HttpStatus.valueOf("QUESTION NOT RECORDED"));
		
	}
	
	@RequestMapping(value = "/professor/question/", method = RequestMethod.PUT)
	public ResponseEntity<QuestionDto>  updateQuestion(QuestionDto questionDto) {
		quizService.updateQuestion(questionDto);
		QuestionDto question = quizService.findQuestion(questionDto.getId());
		if (!questionDto.equals(question))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity( HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/professor/question/{questionid}", method = RequestMethod.DELETE)
	public ResponseEntity deleteQuestion(@PathVariable("questionid")long id) {
		quizService.deleteQuestion(id);
		QuestionDto question = quizService.findQuestion(id);
		if (question == null)
			return new ResponseEntity( HttpStatus.OK);
		return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		
	}
	
	@RequestMapping(value = "/professor/topic/{topicid}/question/", method = RequestMethod.GET)
	public ResponseEntity<List<QuestionDto>> findQuestionsByTopic(@PathVariable("topicid")long topicId) {
		List<QuestionDto> questions = quizService.findQuestionsByTopic(topicId);
		if (questions == null)
			return new ResponseEntity <List<QuestionDto>> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <List<QuestionDto>> (questions, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}", method = RequestMethod.GET)
	public ResponseEntity<SequenceDto>  findSequence(@PathVariable("sequenceid")long id) {
		SequenceDto sequence= quizService.findSequence(id);
		if (sequence== null)
			return new ResponseEntity <SequenceDto> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <SequenceDto> (sequence, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/sequence/", method = RequestMethod.POST)
	public ResponseEntity<SequenceDto>  createSequence(SequenceDto sequenceDto) {
		sequenceDto.setId(-1);
		quizService.createSequence(sequenceDto);
		if (sequenceDto.getId() == -1)
			return new ResponseEntity<SequenceDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SequenceDto>(sequenceDto,HttpStatus.valueOf("SEQUENCE NOT RECORDED"));
		
	}
	
	@RequestMapping(value = "/professor/sequence/", method = RequestMethod.PUT)
	public ResponseEntity  updateSequence(SequenceDto sequenceDto) {
		quizService.updateSequence(sequenceDto);
		SequenceDto sequence = quizService.findSequence(sequenceDto.getId());
		if(!sequence.equals(sequenceDto))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity( HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}", method = RequestMethod.DELETE)
	public ResponseEntity deleteSequence(@PathVariable("sequenceid")long id) {
		quizService.deleteSequence(id);
		SequenceDto sequence = quizService.findSequence(id);
		if (sequence == null)
			return new ResponseEntity( HttpStatus.OK);
		return new ResponseEntity( HttpStatus.NOT_MODIFIED );
		
	}
	
	
	@RequestMapping(value = "/professor/user/{userid}/sequence/}", method = RequestMethod.GET)
	public ResponseEntity <List<SequenceDto>> findSequenceByProfessor(@PathVariable("userid")long userId) {
		List<SequenceDto> sequences = quizService.findSequenceByProfessor(userId);
		if (sequences == null)
			return new ResponseEntity <List<SequenceDto>> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <List<SequenceDto>> (sequences, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}/question/{questionid}/position/{pos}/add/}", method = RequestMethod.PUT)
	public  ResponseEntity  addQuestionToSequence(@PathVariable("sequenceid")long sequenceId,
			@PathVariable("questionid") long questionId,@PathVariable("pos")int pos) {
		quizService.addQuestionToSequence(sequenceId, questionId, pos);	
		return new ResponseEntity  ( HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}/position/{pos}/remove/}", method = RequestMethod.PUT)
	public ResponseEntity  removeQuestionFromSequence(@PathVariable("sequenceid")long sequenceId,@PathVariable("pos") int pos) {
		quizService.removeQuestionFromSequence(sequenceId, pos);		
		return new ResponseEntity  ( HttpStatus.OK);	
	}
	
	
	@RequestMapping(value = "/professor/sessionquiz/{sessionquizid}", method = RequestMethod.GET)
	public ResponseEntity<SessionQuizDto>  findSessionQuiz(@PathVariable("sessionquizid")long id) {
		SessionQuizDto sessionQuizDto = quizService.findSessionQuiz(id);
		if (sessionQuizDto == null)
			return new ResponseEntity<SessionQuizDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SessionQuizDto>(sessionQuizDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/sessionquiz/", method = RequestMethod.POST)
	public ResponseEntity <SessionQuizDto> createSessionQuiz(SessionQuizDto sessionQuizDto) {
		sessionQuizDto.setId(-1);
		quizService.createSessionQuiz(sessionQuizDto);
		if(sessionQuizDto.getId() == -1 )
			return new ResponseEntity <SessionQuizDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SessionQuizDto>(sessionQuizDto,HttpStatus.valueOf("SEQUENCE NOT RECORDED"));
	}
	
	@RequestMapping(value = "/professor/sessionquiz/", method = RequestMethod.PUT)
	public ResponseEntity updateSessionQuiz(SessionQuizDto sessionQuizDto) {
		quizService.updateSessionQuiz(sessionQuizDto);
		SessionQuizDto sessionQuiz = quizService.findSessionQuiz(sessionQuizDto.getId());
		if(!sessionQuiz.equals(sessionQuizDto))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity( HttpStatus.OK);		
	}
		
	@RequestMapping(value = "/professor/sessionquiz/{sessionquizid}", method = RequestMethod.DELETE)
	public ResponseEntity deleteSessionQuiz(@PathVariable("sessionquizid") long id) {
		quizService.deleteSessionQuiz(id);
		SessionQuizDto sessionQuiz = quizService.findSessionQuiz(id);
		if( sessionQuiz == null )	
			return new ResponseEntity( HttpStatus.OK);
		return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
	}
	
	@RequestMapping(value = "/all/cours/{coursid}/sessionquiz/", method = RequestMethod.GET)
	public ResponseEntity< List<SessionQuizDto>> findSessionQuizByCours(@PathVariable("coursid")long coursId) {
		List<SessionQuizDto> sessions = quizService.findSessionQuizByCours(coursId) ;
		if (sessions == null)
			return new ResponseEntity <List<SessionQuizDto>> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <List<SessionQuizDto>> (sessions, HttpStatus.OK);	
	} 
}
