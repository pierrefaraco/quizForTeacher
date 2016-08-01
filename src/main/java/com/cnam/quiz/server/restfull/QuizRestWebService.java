package com.cnam.quiz.server.restfull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cnam.quiz.common.dto.QuestionDto;
import com.cnam.quiz.common.dto.SequenceDto;
import com.cnam.quiz.common.dto.SequenceWithQuestionsDto;
import com.cnam.quiz.common.dto.SessionQuizDto;
import com.cnam.quiz.common.dto.TopicDto;
import com.cnam.quiz.common.exceptions.CoursNotActiveException;
import com.cnam.quiz.common.exceptions.SessionQuizAlreadyRunningException;
import com.cnam.quiz.server.service.quiz.QuizService;

@RestController
public class QuizRestWebService {
	
	@Autowired
	QuizService quizService;	 

	@RequestMapping(value = "/professor/topic/{topicid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TopicDto> findTopic(@PathVariable("topicid") long id) {
		TopicDto topicDto =  quizService.findTopic(id);
		if (topicDto == null)
			return new ResponseEntity<TopicDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<TopicDto>(topicDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/topic/", method = RequestMethod.POST)
	public ResponseEntity<TopicDto>  createTopic(@RequestBody TopicDto topicDto) {
		quizService.createTopic(topicDto);
		if (topicDto.getId() == 0)
			return new ResponseEntity<TopicDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<TopicDto>(topicDto,HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/professor/topic/", method = RequestMethod.PUT)
	public ResponseEntity updateTopic(@RequestBody TopicDto topicDto) {
		quizService.updateTopic(topicDto);
		TopicDto topic = quizService.findTopic(topicDto.getId());
		if (!topicDto.equals(topic))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity( HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/professor/topic/{topicid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteTopic(@PathVariable("topicid")long id) {
		quizService.deleteTopic(id);
		return new ResponseEntity( HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/professor/topic/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<TopicDto>> findAllTopics() {
		List<TopicDto> topics = quizService.findAllTopics();
		if (topics == null)
			return new ResponseEntity<List<TopicDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TopicDto>>(topics, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/user/{userid}/topic/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TopicDto>>  findTopicsByProfessor(@PathVariable("userid")long userId) {
		List<TopicDto> topics = quizService.findTopicsByProfessor(userId);
		if (topics == null)
			return new ResponseEntity<List<TopicDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TopicDto>>(topics, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/question/{questionid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< QuestionDto> findQuestion(@PathVariable("questionid")long id) {
		QuestionDto questionDto = quizService.findQuestion(id);
		if (questionDto  == null)
			return new ResponseEntity<QuestionDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<QuestionDto>(questionDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/question/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionDto>createQuestion(@RequestBody QuestionDto questionDto) {
		quizService.createQuestion(questionDto);
		if (questionDto.getId() == 0)
			return new ResponseEntity<QuestionDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<QuestionDto>(questionDto,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/professor/question/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionDto>  updateQuestion(@RequestBody QuestionDto questionDto) {
		quizService.updateQuestion(questionDto);
		QuestionDto question = quizService.findQuestion(questionDto.getId());
		if (!questionDto.equals(question))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity <QuestionDto> (questionDto,  HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/professor/question/{questionid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteQuestion(@PathVariable("questionid")long id) {
		quizService.deleteQuestion(id);
		return new ResponseEntity( HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/professor/topic/{topicid}/question/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<QuestionDto>> findQuestionsByTopic(@PathVariable("topicid")long topicId) {
		List<QuestionDto> questions = quizService.findQuestionsByTopic(topicId);
		if (questions == null)
			return new ResponseEntity <List<QuestionDto>> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <List<QuestionDto>> (questions, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SequenceWithQuestionsDto>  findSequence(@PathVariable("sequenceid")long id) {
		SequenceWithQuestionsDto sequence= quizService.findSequence(id);
		if (sequence== null)
			return new ResponseEntity <SequenceWithQuestionsDto> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <SequenceWithQuestionsDto> (sequence, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/sequence/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SequenceWithQuestionsDto>  createSequence(@RequestBody SequenceWithQuestionsDto sequenceDto) {
		System.out.println(sequenceDto.getId()+" "+ sequenceDto.getName()+" "+ sequenceDto.getDescription());
		quizService.createSequence(sequenceDto);
		if (sequenceDto.getId() == 0)
			return new ResponseEntity<SequenceWithQuestionsDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<SequenceWithQuestionsDto>(sequenceDto,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/professor/sequence/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <SequenceWithQuestionsDto> updateSequence(@RequestBody SequenceWithQuestionsDto sequenceDto) {
		quizService.updateSequence(sequenceDto);
		SequenceWithQuestionsDto sequence = quizService.findSequence(sequenceDto.getId());
		if(!sequence.equals(sequenceDto))
			return new ResponseEntity<SequenceWithQuestionsDto>( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity<SequenceWithQuestionsDto>(sequenceDto, HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteSequence(@PathVariable("sequenceid")long id) {
		quizService.deleteSequence(id);
		return new ResponseEntity( HttpStatus.OK);	
	}
	
	
	@RequestMapping(value = "/professor/user/{userid}/sequence/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<SequenceDto>> findSequenceByProfessor(@PathVariable("userid")long userId) {
		List<SequenceDto> sequences = quizService.findSequenceByProfessor(userId);
		if (sequences == null)
			return new ResponseEntity <List<SequenceDto>> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <List<SequenceDto>> (sequences, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}/question/{questionid}/position/{pos}/add/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity  <Integer> addQuestionToSequence(@PathVariable("sequenceid")long sequenceId,
			@PathVariable("questionid") long questionId,@PathVariable("pos")int pos) {

		int positionInSequence = quizService.addQuestionToSequence(sequenceId, questionId, pos);	
		System.out.println("positionInSequence"  + positionInSequence );
		return new ResponseEntity  <Integer >(  positionInSequence, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}/position/{pos}/remove/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  removeQuestionFromSequence(@PathVariable("sequenceid")long sequenceId,@PathVariable("pos") int pos) {
		quizService.removeQuestionFromSequence(sequenceId, pos);		
		return new ResponseEntity  ( HttpStatus.OK);	
	}
	
	
	@RequestMapping(value = "/professor/sessionquiz/{sessionquizid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SessionQuizDto>  findSessionQuiz(@PathVariable("sessionquizid")long id) {
		SessionQuizDto sessionQuizDto = quizService.findSessionQuiz(id);
		if (sessionQuizDto == null)
			return new ResponseEntity<SessionQuizDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SessionQuizDto>(sessionQuizDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/sessionquiz/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <SessionQuizDto> startSessionQuiz(@RequestBody SessionQuizDto sessionQuizDto) {
		
		try {
			quizService.startSessionQuiz(sessionQuizDto);
		} catch (SessionQuizAlreadyRunningException e) {
			e.printStackTrace();
			return new ResponseEntity( HttpStatus.INTERNAL_SERVER_ERROR);	
		} catch (CoursNotActiveException e) {
			e.printStackTrace();
			return new ResponseEntity( HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		if(sessionQuizDto.getId() == 0 )
			return new ResponseEntity <SessionQuizDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SessionQuizDto>(sessionQuizDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/sessionquiz/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity stopSessionQuiz(@RequestBody  SessionQuizDto sessionQuizDto) {
		quizService.stopSessionQuiz(sessionQuizDto);
		SessionQuizDto sessionQuiz = quizService.findSessionQuiz(sessionQuizDto.getId());
		if(!sessionQuiz.equals(sessionQuizDto))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity( HttpStatus.OK);		
	}
		
	@RequestMapping(value = "/professor/sessionquiz/{sessionquizid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteSessionQuiz(@PathVariable("sessionquizid") long id) {
		quizService.deleteSessionQuiz(id);
		return new ResponseEntity( HttpStatus.OK );	
	}
	
	@RequestMapping(value = "/all/cours/{coursid}/sessionquiz/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<SessionQuizDto>> findSessionQuizByCours(@PathVariable("coursid")long coursId) {
		List<SessionQuizDto> sessions = quizService.findSessionQuizByCours(coursId) ;
		if (sessions == null)
			return new ResponseEntity <List<SessionQuizDto>> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <List<SessionQuizDto>> (sessions, HttpStatus.OK);	
	} 
}
