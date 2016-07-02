package com.cnam.quiz.server.domain.result;

import java.util.List;

import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.sequence.Sequence;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuiz;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.util.persistence.AbstractDataAccessObject;

public interface ResultDao extends AbstractDataAccessObject <Result,Long> {
	
	List <Result> findResultsByUserQuestionAndSession(long userId ,long questionId, long sessionId );
	List <Result> findResultsByUserAndSession(long userId , long sessionId );
	List <Result> findResultsBySession(long sessionId);
	List <Result> findResultsByQuestionAndSession(long questionId , long sessionId );	
	
}
