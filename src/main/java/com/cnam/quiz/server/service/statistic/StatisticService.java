package com.cnam.quiz.server.service.statistic;

import java.util.List;

import com.cnam.quiz.common.dto.ResultDto;
import com.cnam.quiz.common.dto.ResultDtoForStatistic;
import com.cnam.quiz.common.exceptions.NoRunningSessionQuizForThisCoursException;

public interface StatisticService {
	void saveResult(ResultDto resultDto) throws NoRunningSessionQuizForThisCoursException;

	List<ResultDtoForStatistic> findResultsByUserQuestionAndSession(long userId, long questionId, long sessionId);

	List<ResultDtoForStatistic> findResultsByUserAndSession(long userId, long sessionId);

	List<ResultDtoForStatistic> findResultsByUserAndCours(long userId, long coursId);

	List<ResultDtoForStatistic> findResultsByCours(long coursId);

	List<ResultDtoForStatistic> findResultsBySession(long sessionId);

	List<ResultDtoForStatistic> findResultsByQuestionAndSession(long questionId, long sessionId);
}
