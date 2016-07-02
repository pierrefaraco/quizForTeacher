package com.cnam.quiz.server.service.statistic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import com.cnam.quiz.common.dto.ResultDto;
import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.cours.CoursDao;
import com.cnam.quiz.server.domain.result.Result;
import com.cnam.quiz.server.domain.result.ResultDao;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuiz;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuizDao;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;

@Service("statisticService")
@Transactional
@Rollback(true)
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	ResultDao resultDao;
	
	@Autowired
	SessionQuizDao sessionQuizDao;
	
	@Autowired
	CoursDao coursDao;
	
	@Autowired
	UserDao userDao;
	

	
	
	@Override
	public void saveResult(ResultDto resultDto) {
		Result result = this.resultDtoToResult(resultDto);
		result.setDate(new java.util.Date());
		resultDao.save( result );
	}
	
	@Override
	public List<ResultDto>  findResultsByUserQuestionAndSession(long userId, long questionId, long sessionId) {
		List <Result> listResult =  resultDao.findResultsByUserQuestionAndSession(userId, questionId, sessionId);
		return 	this.listOfresultToListOfResultDto(listResult);
	}

	@Override
	public List<ResultDto>  findResultsByUserAndSession(long userId, long sessionId) {
		List <Result> listResult =  resultDao. findResultsByUserAndSession(userId,sessionId);
		return 	this.listOfresultToListOfResultDto(listResult);
	}

	@Override
	public List<ResultDto> findResultsByUserAndCours(long userId, long coursId) {	
		Cours cours =coursDao.find(coursId);
		List <SessionQuiz> sessions = sessionQuizDao.findByCours( cours );
		List <ResultDto> results = new ArrayList <ResultDto> ();
		for(SessionQuiz sessionQuiz :  sessions )
			 results.addAll(findResultsByUserAndSession(userId, sessionQuiz.getId()));
		return results;
	}
		
	@Override
	public List<ResultDto> findResultsByQuestionAndSession(long questionId, long sessionId) {
		List <Result> listResult =  resultDao. findResultsByQuestionAndSession(questionId,sessionId);
		return 	this.listOfresultToListOfResultDto(listResult);
	}	
	
	@Override
	public List<ResultDto>  findResultsBySession(long sessionId) {
		List <Result> listResult =  resultDao. findResultsBySession(sessionId);
		return 	this.listOfresultToListOfResultDto(listResult);
	}
	
	@Override
	public List<ResultDto>  findResultsByCours(long coursId) {
		Cours cours =coursDao.find(coursId);
		List <SessionQuiz> sessions = sessionQuizDao.findByCours( cours );
		List <ResultDto> results = new ArrayList <ResultDto> ();
		for(SessionQuiz sessionQuiz :  sessions )
			 results.addAll( findResultsBySession(sessionQuiz.getId()));
		return results;
	}
	
	public List <ResultDto> listOfresultToListOfResultDto (List <Result> listResult){
		ArrayList<ResultDto> listResultDto = new ArrayList<ResultDto>();
		for (Result result : listResult)
			 listResultDto.add( resultToResultDto(result) );
		return	listResultDto;	
	}
	
	public List <Result> listOfresultDtoToListOfResult (List <ResultDto> listResultDto){
		ArrayList<Result> listResult = new ArrayList<Result>();
		for (ResultDto result : listResultDto)
			 listResult.add( resultDtoToResult(result) );
		return	listResult;	
	}
	
	public Result resultDtoToResult ( ResultDto resultDto){
		Result result = new Result ();
		result.setId(resultDto.getId());
		User user = userDao.find(resultDto.getUserId());
		result.setUser(user);
		result.setAnswerTime(resultDto.getAnswerTime());
		result.setQuestionId(resultDto.getQuestionId());
		result.setQuestion(resultDto.getQuestion());
		result.setGivenAnswers(	resultDto.getGivenAnswers());
		result.setGoodAnswers(	resultDto.getGoodAnswers());
		result.setPropositions(resultDto.getPropositions());
		result.setDate(resultDto.getDate());	
		SessionQuiz sessionQuiz = sessionQuizDao.find(resultDto.getSessionQuizId());
		result.setSessionQuiz(sessionQuiz );
		result.setMaxPoints(resultDto.getMaxPoints());
		result.setObtainedPoint(resultDto.getObtainedPoints());
		return result;	
	}

	public ResultDto resultToResultDto ( Result result){
		ResultDto resultDto = new ResultDto ();
		resultDto.setId(result.getId());
		resultDto.setUserId(result.getUser().getId());
		resultDto.setAnswerTime(result.getAnswerTime());
		resultDto.setQuestionId(result.getQuestionId());
		resultDto.setQuestion(result.getQuestion());
		resultDto.setGivenAnswers(	result.getGivenAnswers());
		resultDto.setGoodAnswers(	result.getGoodAnswers());
		resultDto.setPropositions(result.getPropositions());
		resultDto.setDate(result.getDate());
		resultDto.setSessionQuizId(result.getSessionQuiz().getId());
		resultDto.setMaxPoints(result.getMaxPoints());
		resultDto.setObtainedPoints(result.getObtainedPoint());
		return resultDto;	
	}
	
	private UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setAccountType(user.getAccountType());
		return userDto;
	}
}
