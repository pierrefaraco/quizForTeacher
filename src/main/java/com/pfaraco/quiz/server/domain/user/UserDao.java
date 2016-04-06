package com.pfaraco.quiz.server.domain.user;

import java.util.List;

import com.pfaraco.quiz.server.domain.topic.Topic;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObject;

public interface UserDao extends AbstractDataAccessObject <User,Long>  {
		List<User>findAll();
		User findUserByMailAndPassword(String email, String password );
			
}
