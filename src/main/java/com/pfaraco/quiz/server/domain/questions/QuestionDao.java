package com.pfaraco.quiz.server.domain.questions;

import java.util.List;

import com.pfaraco.quiz.server.domain.topic.Topic;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObject;

public interface QuestionDao  extends AbstractDataAccessObject <Question,Long> {
	List<Question>findByTopic(Topic topic);
	List<Question>findAll();
}
