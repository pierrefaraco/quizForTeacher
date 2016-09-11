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
import cnam.glg204.quiz.common.dto.ResultDto;
import cnam.glg204.quiz.common.dto.ResultWithUserDto;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.NoRunningSessionQuizForThisCoursException;
import cnam.glg204.quiz.server.service.statistic.StatisticService;

/**
 * 
 * Controlleur rest,  donne un accés aux methodes qui permmettent d'enregistrer les résultats aux questions et de générer des statistiques
 * @author Pierre Faraco
 *
 */

@RestController
public class StatistsicsRestWebService {

	@Autowired
	StatisticService statisticService;	
	
	/**
	 *
	 * Enregistre un résultat en base <br/>
	 * @param resultDto Objet qui represente un résultat à une question,
	 * @return Instance du résultat créé avec en plus le paramètre Id auto-généré avec status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/all/result/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultDto> saveResult(@RequestBody ResultDto resultDto) throws NoRunningSessionQuizForThisCoursException, CheckException{
		statisticService.saveResult(resultDto);
		if(resultDto.getId()==0)
			return new ResponseEntity<ResultDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<ResultDto>(resultDto, HttpStatus.OK);
	}
	
	/**
	 *
	 * Permet à un auditeur de récupérer son résultats pour une question posé pendant une session
	 * @param userId id de l'auditeur
	 * @param sessionId id de la session
	 * @param questionId id de la question
	 * @return Résultat de l'utilisateur pour la question,  status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/all/user/{userid}/question/{questionid}/session/{sessionid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResultWithUserDto>>findResultsByUserQuestionAndSession(@PathVariable("userid")long userId 
			,@PathVariable("questionid")long questionId, @PathVariable("sessionid")long sessionId ){
		List<ResultWithUserDto> results = statisticService.findResultsByUserQuestionAndSession(userId, questionId, sessionId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultWithUserDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultWithUserDto>>(results, HttpStatus.OK);
	}
	
	
	/**
	 *
	 * Permet à un auditeur de récupérer ses résultats  pour une session
	 * @param userId id de l'auditeur
	 * @param sessionId id de la session
	 * @return Résultat de l'utilisateur pour la session,   status HTTP
	 * 
	 */
	
	
	@RequestMapping(value = "/all/user/{userid}/session/{sessionid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResultWithUserDto>> findResultsByUserAndSession(@PathVariable("userid")long userId ,@PathVariable("sessionid") long sessionId ){
		List<ResultWithUserDto> results = statisticService.findResultsByUserAndSession(userId, sessionId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultWithUserDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultWithUserDto>>(results, HttpStatus.OK);
	}
	
	
	/**
	 *
	 * Permet à un auditeur de récupérer ses résultats  pour un cours 
	 * @param userId id de l'auditeur
	 * @param coursid du cours
	 * @return Résultat de l'utilisateur pour le cours,  status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/all/user/{userid}/cours/{coursid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResultWithUserDto>>findResultsByUserAndCours(@PathVariable("userid")long userId,
			@PathVariable("coursid")long coursId){
		List<ResultWithUserDto> results = statisticService.findResultsByUserAndCours(userId, coursId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultWithUserDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultWithUserDto>>(results, HttpStatus.OK);
	}	
	
	/**
	 *
	 * Permet à un professeur de récupérer les résultats des abonnés pour un cours
	 * @param coursId id du cours 
	 * @return Résultat des utilisateur pour le cours,  status HTTP
	 * 
	 */

	@RequestMapping(value = "/professor/cours/{coursid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResultWithUserDto>> findResultsByCours(@PathVariable("coursid")long coursId){
		List<ResultWithUserDto> results = statisticService. findResultsByCours(coursId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultWithUserDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultWithUserDto>>(results, HttpStatus.OK);
	}
	
	/**
	 *
	 * Permet à un professeur de récupérer les résultats des abonnés pour une session
	 * @param sessionId id de la session
	 * @return Résultat des utilisateur pour la session,  status HTTP
	 * 
	 */
	
	
	@RequestMapping(value = "/professor/session/{sessionid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResultWithUserDto>> findResultsBySession(@PathVariable("sessionid")long sessionId){
		List<ResultWithUserDto> results = statisticService.findResultsBySession(sessionId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultWithUserDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultWithUserDto>>(results, HttpStatus.OK);
	}
		
	/**
	 *
	 * Permet à un professeur de récupérer les résultats des abonnés pour une question posé pendant une session
	 * @param sessionId id de la session
	 * @param questionId id de la question
	 * @return Résultat des utilisateur pour la question,  status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/professor/question/{questionid}/session/{sessionid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ResultWithUserDto>> findResultsByQuestionAndSession(@PathVariable("questionid")long questionId ,@PathVariable("sessionid") long sessionId ){
		List<ResultWithUserDto> results = statisticService.findResultsByQuestionAndSession(questionId, sessionId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultWithUserDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultWithUserDto>>(results, HttpStatus.OK);
	}	
	
}
