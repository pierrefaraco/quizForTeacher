package cnam.glg204.quiz.server.domain.cours;

import java.util.List;

import cnam.glg204.quiz.server.domain.topic.Topic;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObject;

public interface CoursDao  extends AbstractDataAccessObject <Cours,Long> {
	List<Cours>findAll();
	List<Cours>getActiveCours();
	List<Cours>getCoursProfessor(User user);	
	List<Cours>getCoursAuditor(User user);
}

