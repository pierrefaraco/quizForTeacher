package com.pfaraco.quiz.server.domain.result;

import java.util.List;

import com.pfaraco.quiz.server.domain.cours.Cours;
import com.pfaraco.quiz.server.domain.sequence.Sequence;
import com.pfaraco.quiz.server.domain.sessionquiz.SessionQuiz;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObject;

public interface ResultDao extends AbstractDataAccessObject <Result,Long> {
	List<Result>findResultForASessionQuiz(SessionQuiz sessionQuiz );
	List<Result>findResultForACours(Cours cours);
	List<Result>findResultByUserForASessionQuiz(SessionQuiz session, User user);
	List<Result>findResultByUserForACours(Cours cours,User user);
}
