package cnam.glg204.quiz.server.domain.topic;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObjectImpl;

@Repository("topicDao")
public class TopicDaoImpl extends AbstractDataAccessObjectImpl <Topic,Long>  implements	TopicDao {
	

	@PostConstruct
	public void setEntityClass()
	{
		super.setEntityClass(Topic.class);	
	}
	
	@Override
	public List<Topic> findAll() {
		return em.createNamedQuery("findAllTopics").getResultList();
	}

	@Override
	public List<Topic> findByUser(User user) {
		Query query = em.createNamedQuery("findTopicsByUser");;
		query.setParameter("userid", user.getId());
		return query .getResultList();
	}

}
