package com.pfaraco.quiz.server.domain.topic;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.domain.user.UserDao;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObjectImpl;

@Repository("topicDao")
public class TopicDaoImpl extends AbstractDataAccessObjectImpl <Topic,Long>  implements	TopicDao {
	

	public List<Topic> findAll() {
		return em.createNamedQuery("findAllTopics").getResultList();
	}



	@Override
	public List<Topic> findByUser(User user) {
		// Query query = em.createNamedQuery("findTopicsByUser");;
		// query.setParameter("user", user);
		return null;// query .getResultList();

	}

}
