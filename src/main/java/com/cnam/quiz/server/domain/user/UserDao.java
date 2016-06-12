package com.cnam.quiz.server.domain.user;

import java.util.List;

import com.cnam.quiz.server.domain.topic.Topic;
import com.cnam.quiz.server.util.persistence.AbstractDataAccessObject;

public interface UserDao extends AbstractDataAccessObject <User,Long>  {
		List<User>findAll();
		User findUserByMailAndPassword(String email, String password );
		User findUserByMail(String email );	
}
