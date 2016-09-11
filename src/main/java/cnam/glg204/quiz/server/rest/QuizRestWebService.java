package cnam.glg204.quiz.server.rest;
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
import cnam.glg204.quiz.common.dto.QuestionDto;
import cnam.glg204.quiz.common.dto.SequenceDto;
import cnam.glg204.quiz.common.dto.SequenceWithQuestionsDto;
import cnam.glg204.quiz.common.dto.SessionQuizDto;
import cnam.glg204.quiz.common.dto.TopicDto;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.CoursNotActiveException;
import cnam.glg204.quiz.common.exceptions.NoCoursSelectedException;
import cnam.glg204.quiz.common.exceptions.SessionQuizAlreadyRunningException;
import cnam.glg204.quiz.server.service.quiz.QuizService;

/**
 * 
 * Controlleur rest,  donne un accés aux methodes qui permmettent la gestion des quiz 
 * @author Pierre Faraco
 *
 */


@RestController
public class QuizRestWebService {
	
	@Autowired
	QuizService quizService;	 
	
	/**
	 *
	 * Renvois un topic à partir de son Id<br/>
	 * @param topicsId id du topic
	 * @return Instance du topic trouvé, status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/professor/topic/{topicid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TopicDto> findTopic(@PathVariable("topicid") long id) {
		TopicDto topicDto =  quizService.findTopic(id);
		if (topicDto == null)
			return new ResponseEntity<TopicDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<TopicDto>(topicDto, HttpStatus.OK);
	}
		
	/**
	 *
	 * Créé un topic  en base <br/>
	 * @param topicDto Objet qui represente le  topic à créer.
	 * @return Instance du topic créé avec en plus le paramètre Id auto-généré, status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/professor/topic/", method = RequestMethod.POST)
	public ResponseEntity<TopicDto>  createTopic(@RequestBody TopicDto topicDto)throws CheckException  {
		quizService.createTopic(topicDto);
		if (topicDto.getId() == 0)
			return new ResponseEntity<TopicDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<TopicDto>(topicDto,HttpStatus.OK);	
	}
	
	/**
	 *
	 * modifie un topic  en base <br/>
	 * @param topicDto Objet qui represente le  topic à créer.
	 * @return Instance du topic modifié, status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/professor/topic/", method = RequestMethod.PUT)
	public ResponseEntity updateTopic(@RequestBody TopicDto topicDto) throws CheckException {
		quizService.updateTopic(topicDto);
		TopicDto topic = quizService.findTopic(topicDto.getId());
		if (!topicDto.equals(topic))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity( HttpStatus.OK);	
	}
	
	/**
	 *
	 * Supprime un topic/>
	 * @param topicId id du topic
	 * @return status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/professor/topic/{topicid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteTopic(@PathVariable("topicid")long topicId) {
		quizService.deleteTopic(topicId);
		return new ResponseEntity( HttpStatus.OK);	
	}
	
	/**
	 *
	 * Renvois la liste de tous les  topics <br/>
	 * @return liste des topics, status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/professor/topic/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<TopicDto>> findAllTopics() {
		List<TopicDto> topics = quizService.findAllTopics();
		if (topics == null)
			return new ResponseEntity<List<TopicDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TopicDto>>(topics, HttpStatus.OK);
	}
	
	/**
	 *
	 * Renvois la liste des topics pour un professuer<br/>
	 * @param userId id du professeur
	 * @return liste des topics, status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/professor/user/{userid}/topic/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TopicDto>>  findTopicsByProfessor(@PathVariable("userid")long userId) {
		List<TopicDto> topics = quizService.findTopicsByProfessor(userId);
		if (topics == null)
			return new ResponseEntity<List<TopicDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TopicDto>>(topics, HttpStatus.OK);
	}
	
	/**
	 *
	 * Renvois une question à partir de son Id<br/>
	 * @param questionId id de la question
	 * @return Instance de la question trouvée, status HTTP
	 * 
	 */	
	
	@RequestMapping(value = "/professor/question/{questionid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< QuestionDto> findQuestion(@PathVariable("questionid")long questionId) {
		QuestionDto questionDto = quizService.findQuestion(questionId);
		if (questionDto  == null)
			return new ResponseEntity<QuestionDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<QuestionDto>(questionDto, HttpStatus.OK);
	}
	
	/**
	 *
	 * Créé une question  en base <br/>
	 * @param questionDto Objet qui represente la question à créer.
	 * @return Instance de la question créée avec en plus le paramètre Id auto-généré, status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/professor/question/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionDto>createQuestion(@RequestBody QuestionDto questionDto) throws CheckException {
		quizService.createQuestion(questionDto);
		if (questionDto.getId() == 0)
			return new ResponseEntity<QuestionDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<QuestionDto>(questionDto,HttpStatus.OK);
		
	}
	
	/**
	 *
	 * Modifie une question  en base <br/>
	 * @param questionDto Objet qui represente la question à créer.
	 * @return Instance de la question modifiée, status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/professor/question/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionDto>  updateQuestion(@RequestBody QuestionDto questionDto) throws CheckException {
		quizService.updateQuestion(questionDto);
		QuestionDto question = quizService.findQuestion(questionDto.getId());
		if (!questionDto.equals(question))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity <QuestionDto> (questionDto,  HttpStatus.OK);		
	}
	
	/**
	 *
	 * Supprime une question<br/>
	 * @param questionId id de la question
	 * @return status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/professor/question/{questionid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteQuestion(@PathVariable("questionid")long questionId) {
		quizService.deleteQuestion( questionId);
		return new ResponseEntity( HttpStatus.OK);		
	}
	
	/**
	 *
	 * Renvois la liste des question d'un topic<br/>
	 * @param topicId id du topic.
	 * @return Iliste des questions, status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/professor/topic/{topicid}/question/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<QuestionDto>> findQuestionsByTopic(@PathVariable("topicid")long topicId) {
		List<QuestionDto> questions = quizService.findQuestionsByTopic(topicId);
		if (questions == null)
			return new ResponseEntity <List<QuestionDto>> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <List<QuestionDto>> (questions, HttpStatus.OK);
	}
	
	/**
	 *
	 * Renvois une séquence à partir de son Id<br/>
	 * @param sequenceId id de la séquence
	 * @return Instance de la sequence trouvée, status HTTP
	 * 
	 */	
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SequenceWithQuestionsDto>  findSequence(@PathVariable("sequenceid")long sequenceId) {
		SequenceWithQuestionsDto sequence= quizService.findSequence(sequenceId);
		if (sequence== null)
			return new ResponseEntity <SequenceWithQuestionsDto> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <SequenceWithQuestionsDto> (sequence, HttpStatus.OK);
	}
	
	/**
	 *
	 * Créé une sequence  en base <br/>
	 * @param sequenceDto Objet qui represente la sequence à créer.
	 * @return Instance de la question créée avec en plus le paramètre Id auto-généré, status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/professor/sequence/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SequenceWithQuestionsDto>  createSequence(@RequestBody SequenceWithQuestionsDto sequenceDto)throws CheckException  {
		System.out.println(sequenceDto.getId()+" "+ sequenceDto.getName()+" "+ sequenceDto.getDescription());
		quizService.createSequence(sequenceDto);
		if (sequenceDto.getId() == 0)
			return new ResponseEntity<SequenceWithQuestionsDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<SequenceWithQuestionsDto>(sequenceDto,HttpStatus.OK);
		
	}
	
	/**
	 *
	 * Modifie une sequence  en base <br/>
	 * @param sequenceDto Objet qui represente la sequence à créer.
	 * @return Instance de la question modifiée, status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/professor/sequence/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <SequenceWithQuestionsDto> updateSequence(@RequestBody SequenceWithQuestionsDto sequenceDto) throws CheckException {
		quizService.updateSequence(sequenceDto);
		SequenceWithQuestionsDto sequence = quizService.findSequence(sequenceDto.getId());
		if(!sequence.equals(sequenceDto))
			return new ResponseEntity<SequenceWithQuestionsDto>( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity<SequenceWithQuestionsDto>(sequenceDto, HttpStatus.OK);		
	}
	
	/**
	 *
	 * Supprime une sequence<br/>
	 * @param sequenceId id de la sequence
	 * @return status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteSequence(@PathVariable("sequenceid")long id) {
		quizService.deleteSequence(id);
		return new ResponseEntity( HttpStatus.OK);	
	}
	
	/**
	 *
	 * Renvois la liste des séquences pour un professeur<br/>
	 * @param userId id du professeur.
	 * @return liste de séquences, status HTTP
	 * 
	 */	
	
	@RequestMapping(value = "/professor/user/{userid}/sequence/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<SequenceDto>> findSequenceByProfessor(@PathVariable("userid")long userId) {
		List<SequenceDto> sequences = quizService.findSequenceByProfessor(userId);
		if (sequences == null)
			return new ResponseEntity <List<SequenceDto>> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <List<SequenceDto>> (sequences, HttpStatus.OK);
	}
		
	/**
	 *
	 * Rajoute une question à une séquence<br/>
	 * @param sequenceId id de la sequence
	 * @param questionId id de la question
	 * @param pos position souhaitée dans la séquence
	 * @return la position dans la sequence, status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/professor/sequence/{sequenceid}/question/{questionid}/position/{pos}/add/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity  <Integer> addQuestionToSequence(@PathVariable("sequenceid")long sequenceId,
			@PathVariable("questionid") long questionId,@PathVariable("pos")int pos) throws CheckException {

		int positionInSequence = quizService.addQuestionToSequence(sequenceId, questionId, pos);	
		System.out.println("positionInSequence"  + positionInSequence );
		return new ResponseEntity  <Integer >(  positionInSequence, HttpStatus.OK);
	}
	
	/**
	 *
	 * Retire une question d' une séquence<br/>
	 * @param sequenceId id de la sequence
	 * @param pos position de la question à retirer
	 * @return status HTTP
	 * 
	 */
	@RequestMapping(value = "/professor/sequence/{sequenceid}/position/{pos}/remove/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  removeQuestionFromSequence(@PathVariable("sequenceid")long sequenceId,@PathVariable("pos") int pos)throws CheckException  {
		quizService.removeQuestionFromSequence(sequenceId, pos);		
		return new ResponseEntity  ( HttpStatus.OK);	
	}
	
	/**
	 *
	 * Renvois une sessionQuiz à partir de son Id<br/>
	 * @param sessionQuizId id de la sessionQuiz
	 * @return Instance de la sessionQuiz trouvée, status HTTP
	 * 
	 */	
	
	
	@RequestMapping(value = "/professor/sessionquiz/{sessionquizid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SessionQuizDto>  findSessionQuiz(@PathVariable("sessionquizid")long sessionQuizId) {
		SessionQuizDto sessionQuizDto = quizService.findSessionQuiz(sessionQuizId);
		if (sessionQuizDto == null)
			return new ResponseEntity<SessionQuizDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SessionQuizDto>(sessionQuizDto, HttpStatus.OK);
	}
	
	/**
	 *
	 * Créé une sessionQuiz  en base <br/>
	 * @param sessionQuizDto Objet qui represente la sessionQuiz  à créer.
	 * @return Instance de la sessionQuiz créée avec en plus le paramètre Id auto-généré, status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/professor/sessionquiz/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <SessionQuizDto> startSessionQuiz(@RequestBody SessionQuizDto sessionQuizDto) throws CheckException, NoCoursSelectedException, SessionQuizAlreadyRunningException, CoursNotActiveException {
		quizService.startSessionQuiz(sessionQuizDto);	
		if(sessionQuizDto.getId() == 0 )
			return new ResponseEntity <SessionQuizDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<SessionQuizDto>(sessionQuizDto, HttpStatus.OK);
	}
	
	/**
	 *
	 * Modifie une sessionQuiz  en base <br/>
	 * @param sessionQuizDto Objet qui represente la sessionQuiz  à créer.
	 * @return Instance de la sessionQuiz modifiée, status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/professor/sessionquiz/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity stopSessionQuiz(@RequestBody  SessionQuizDto sessionQuizDto)throws CheckException {
		quizService.stopSessionQuiz(sessionQuizDto);
		SessionQuizDto sessionQuiz = quizService.findSessionQuiz(sessionQuizDto.getId());
		if(!sessionQuiz.equals(sessionQuizDto))
			return new ResponseEntity( HttpStatus.NOT_MODIFIED );	
		return new ResponseEntity( HttpStatus.OK);		
	}
	
	/**
	 *
	 * Supprime une session quiz<br/>
	 * @param  sessionQuizId id de la session Quiz
	 * @return status HTTP
	 * 
	 */
	
		
	@RequestMapping(value = "/professor/sessionquiz/{sessionquizid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteSessionQuiz(@PathVariable("sessionquizid") long sessionQuizId) {
		quizService.deleteSessionQuiz( sessionQuizId);
		return new ResponseEntity( HttpStatus.OK );	
	}
	
	/**
	 *
	 * Renvois la liste des sessionQuiz qui se sont déroulés pour un cours<br/>
	 * @param coursId id du cours.
	 * @return liste des sessionQuiz, status HTTP
	 * 
	 */	
	
	@RequestMapping(value = "/all/cours/{coursid}/sessionquiz/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<SessionQuizDto>> findSessionQuizByCours(@PathVariable("coursid")long coursId) {
		List<SessionQuizDto> sessions = quizService.findSessionQuizByCours(coursId) ;
		if (sessions == null)
			return new ResponseEntity <List<SessionQuizDto>> (HttpStatus.NO_CONTENT);
		return new ResponseEntity <List<SessionQuizDto>> (sessions, HttpStatus.OK);	
	} 
}
