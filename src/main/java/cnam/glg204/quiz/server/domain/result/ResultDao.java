package cnam.glg204.quiz.server.domain.result;

import java.util.List;

import cnam.glg204.quiz.server.domain.cours.Cours;
import cnam.glg204.quiz.server.domain.sequence.Sequence;
import cnam.glg204.quiz.server.domain.sessionquiz.SessionQuiz;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObject;

public interface ResultDao extends AbstractDataAccessObject <Result,Long> {
	
	List <Result> findResultsByUserQuestionAndSession(long userId ,long questionId, long sessionId );
	List <Result> findResultsByUserAndSession(long userId , long sessionId );
	List <Result> findResultsBySession(long sessionId);
	List <Result> findResultsByQuestionAndSession(long questionId , long sessionId );	
	
}
