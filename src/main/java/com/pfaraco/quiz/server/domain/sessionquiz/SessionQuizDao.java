package com.pfaraco.quiz.server.domain.sessionquiz;

import java.util.List;

import com.pfaraco.quiz.server.domain.cours.Cours;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObject;

public interface SessionQuizDao extends AbstractDataAccessObject <SessionQuiz,Long> {
	List<SessionQuiz>findAll();
	List<SessionQuiz>findByCours(Cours cours);
	List<SessionQuiz>findByProfessor(User user);
	List<SessionQuiz>findByAuditor(User user);
	List<SessionQuiz>findByAuditorAndCours(User user,Cours cours);
}
