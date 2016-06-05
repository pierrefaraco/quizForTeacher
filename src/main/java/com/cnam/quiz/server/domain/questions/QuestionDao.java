package com.cnam.quiz.server.domain.questions;

import java.util.List;

import com.cnam.quiz.server.domain.topic.Topic;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.util.persistence.AbstractDataAccessObject;

public interface QuestionDao  extends AbstractDataAccessObject <Question,Long> {
	List<Question>findByTopic(Topic topic);
	List<Question>findAll();
}
