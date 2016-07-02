package com.cnam.quiz.server.service.statistic;

import java.util.List;

import com.cnam.quiz.common.dto.ResultDto;

public interface StatisticService {
	void saveResult(ResultDto resultDto);
	List<ResultDto> findResultsByUserQuestionAndSession(long userId ,long questionId, long sessionId );
	List<ResultDto>  findResultsByUserAndSession(long userId , long sessionId );
	List<ResultDto> findResultsByUserAndCours(long userId, long coursId);	
	List<ResultDto> findResultsByCours(long coursId);
	List<ResultDto> findResultsBySession(long sessionId);
	List<ResultDto> findResultsByQuestionAndSession(long questionId , long sessionId );	
}
