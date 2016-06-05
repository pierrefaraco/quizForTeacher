package com.cnam.quiz.server.domain.result;

import java.util.List;

import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.sequence.Sequence;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuiz;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.util.persistence.AbstractDataAccessObject;

public interface ResultDao extends AbstractDataAccessObject <Result,Long> {
	List<Result>findResultForASessionQuiz(SessionQuiz sessionQuiz );
	List<Result>findResultForACours(Cours cours);
	List<Result>findResultByUserForASessionQuiz(SessionQuiz session, User user);
	List<Result>findResultByUserForACours(Cours cours,User user);
}
