package com.pfaraco.quiz.server.domain.topic;

import java.util.List;

import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.domain.user.UserDao;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObject;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObjectImpl;

public interface TopicDao extends AbstractDataAccessObject <Topic,Long> {
	List<Topic>findByUser(User user);
	List<Topic>findAll();
}
