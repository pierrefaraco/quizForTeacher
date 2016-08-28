package cnam.glg204.quiz.server.service.statistic;

import java.util.List;

import cnam.glg204.quiz.common.dto.ResultDto;
import cnam.glg204.quiz.common.dto.ResultWithUserDto;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.NoRunningSessionQuizForThisCoursException;

public interface StatisticService {
	void saveResult(ResultDto resultDto) throws NoRunningSessionQuizForThisCoursException , CheckException ;

	List<ResultWithUserDto> findResultsByUserQuestionAndSession(long userId, long questionId, long sessionId);

	List<ResultWithUserDto> findResultsByUserAndSession(long userId, long sessionId);

	List<ResultWithUserDto> findResultsByUserAndCours(long userId, long coursId);

	List<ResultWithUserDto> findResultsByCours(long coursId);

	List<ResultWithUserDto> findResultsBySession(long sessionId);

	List<ResultWithUserDto> findResultsByQuestionAndSession(long questionId, long sessionId);
}
