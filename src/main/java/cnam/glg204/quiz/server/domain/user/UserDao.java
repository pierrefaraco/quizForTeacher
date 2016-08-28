package cnam.glg204.quiz.server.domain.user;

import java.util.List;

import cnam.glg204.quiz.server.domain.topic.Topic;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObject;

public interface UserDao extends AbstractDataAccessObject <User,Long>  {
		List<User>findAll();
		User findUserByMailAndPassword(String email, String password );
		User findByMail(String email );	
}
