package cnam.glg204.quiz.server.domain.sessionquiz;

import java.util.List;

import cnam.glg204.quiz.server.domain.cours.Cours;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObject;

public interface SessionQuizDao extends AbstractDataAccessObject <SessionQuiz,Long> {
	List<SessionQuiz>findAll();
	List<SessionQuiz>findByCours(Cours cours);
	SessionQuiz findRunningByCours(Cours cours);
	List<SessionQuiz>findByProfessor(User user);
	List<SessionQuiz>findByAuditor(User user);
	List<SessionQuiz>findByAuditorAndCours(User user,Cours cours);
}
