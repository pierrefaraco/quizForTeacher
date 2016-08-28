package cnam.glg204.quiz.server.domain.cours;

import java.util.List;

import cnam.glg204.quiz.server.domain.topic.Topic;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObject;

public interface CoursDao  extends AbstractDataAccessObject <Cours,Long> {
	List<Cours>findAll();
	List<Cours>findActiveCours();
	List<Cours>findByProfessor(User user);	
	List<Cours>findByAuditor(User user);
}

