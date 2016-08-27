package cnam.glg204.quiz.server.service.statistic;

import java.util.List;

import cnam.glg204.quiz.common.dto.ResultDto;
import cnam.glg204.quiz.common.dto.ResultDtoForStatistic;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.NoRunningSessionQuizForThisCoursException;

public interface StatisticService {
	void saveResult(ResultDto resultDto) throws NoRunningSessionQuizForThisCoursException , CheckException ;

	List<ResultDtoForStatistic> findResultsByUserQuestionAndSession(long userId, long questionId, long sessionId);

	List<ResultDtoForStatistic> findResultsByUserAndSession(long userId, long sessionId);

	List<ResultDtoForStatistic> findResultsByUserAndCours(long userId, long coursId);

	List<ResultDtoForStatistic> findResultsByCours(long coursId);

	List<ResultDtoForStatistic> findResultsBySession(long sessionId);

	List<ResultDtoForStatistic> findResultsByQuestionAndSession(long questionId, long sessionId);
}
