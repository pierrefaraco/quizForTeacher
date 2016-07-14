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
import com.cnam.quiz.common.dto.ResultDto;
import com.cnam.quiz.server.service.statistic.StatisticService;


@RestController
public class StatistsicsRestWebService {

	@Autowired
	StatisticService statisticService;
	
	@RequestMapping(value = "/all/result/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ResultDto> saveResult(@RequestBody ResultDto resultDto){
		statisticService.saveResult(resultDto);
		if(resultDto.getId()==0)
			return new ResponseEntity<ResultDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<ResultDto>(resultDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/user/{userid}/question/{questionid}/session/{sessionid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ResultDto>>findResultsByUserQuestionAndSession(@PathVariable("userid")long userId 
			,@PathVariable("questionid")long questionId, @PathVariable("sessionid")long sessionId ){
		List<ResultDto> results = statisticService.findResultsByUserQuestionAndSession(userId, questionId, sessionId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultDto>>(results, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/user/{userid}/session/{sessionid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ResultDto>> findResultsByUserAndSession(@PathVariable("userid")long userId ,@PathVariable("sessionid") long sessionId ){
		List<ResultDto> results = statisticService.findResultsByUserAndSession(userId, sessionId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultDto>>(results, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/user/{userid}/cours/{coursid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ResultDto>>findResultsByUserAndCours(@PathVariable("userid")long userId,
			@PathVariable("coursid")long coursId){
		List<ResultDto> results = statisticService.findResultsByUserAndCours(userId, coursId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultDto>>(results, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/professor/cours/{coursid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ResultDto>> findResultsByCours(@PathVariable("coursid")long coursId){
		List<ResultDto> results = statisticService. findResultsByCours(coursId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultDto>>(results, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/session/{sessionid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ResultDto>> findResultsBySession(@PathVariable("sessionid")long sessionId){
		List<ResultDto> results = statisticService.findResultsBySession(sessionId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultDto>>(results, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/question/{questionid}/session/{sessionid}/result", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<ResultDto>> findResultsByQuestionAndSession(@PathVariable("questionid")long questionId ,@PathVariable("sessionid") long sessionId ){
		List<ResultDto> results = statisticService.findResultsByQuestionAndSession(questionId, sessionId);
		if (results.isEmpty()) 
			return new ResponseEntity<List<ResultDto>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<ResultDto>>(results, HttpStatus.OK);
	}	
}
