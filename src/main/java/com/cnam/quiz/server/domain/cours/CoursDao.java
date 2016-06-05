package com.cnam.quiz.server.domain.cours;

import java.util.List;

import com.cnam.quiz.server.domain.topic.Topic;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.util.persistence.AbstractDataAccessObject;

public interface CoursDao  extends AbstractDataAccessObject <Cours,Long> {
	List<Cours>findAll();
	List<Cours>findByProfessorr(User user);
	List<Cours>findByAuditor(User user);
}

