package cnam.glg204.quiz.server.domain.topic;

import java.util.List;

import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObject;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObjectImpl;

public interface TopicDao extends AbstractDataAccessObject <Topic,Long> {
	List<Topic>findByUser(User user);
	List<Topic>findAll();
}
