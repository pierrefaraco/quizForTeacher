package com.pfaraco.quiz.server.domain.topic;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObjectImpl;

@Repository("topicDao")
public class TopicDaoImpl extends AbstractDataAccessObjectImpl implements TopicDao {
	
	public List<Topic> findAll()
	{	
		return em.createNamedQuery("findAllTopics").getResultList();
	}
	
	@Override
	public void save(Topic topic) {
	
	em.persist(topic);

	}
	@Override
	public void update(Topic topic) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Topic topic) {
		// TODO Auto-generated method stub   
		
	}
	@Override
	public Topic find(long id) {
		Query query = em.createNamedQuery("findTopic");;
		query.setParameter("id", id);
		return ( Topic)query .getResultList().get(0);
	}

	@Override
	public List<Topic> findByUser(User user) {
		//Query query = em.createNamedQuery("findTopicsByUser");;
		//query.setParameter("user", user);
		return null;//query .getResultList();

	}



}
