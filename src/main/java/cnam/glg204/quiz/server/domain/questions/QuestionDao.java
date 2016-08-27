package cnam.glg204.quiz.server.domain.questions;

import java.util.List;

import cnam.glg204.quiz.server.domain.topic.Topic;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObject;

public interface QuestionDao  extends AbstractDataAccessObject <Question,Long> {
	List<Question>findQuestionsByTopic(Topic topic);
	List<Question>findAll();
}
