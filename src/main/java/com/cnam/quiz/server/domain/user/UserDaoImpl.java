package com.cnam.quiz.server.domain.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cnam.quiz.server.domain.topic.Topic;
import com.cnam.quiz.server.util.persistence.AbstractDataAccessObjectImpl;
@Repository("userDao")
public class UserDaoImpl extends AbstractDataAccessObjectImpl <User,Long>  implements UserDao {
	
	@PostConstruct
	public void setEntityClass()
	{
		super.setEntityClass(User.class);	
	}
	
	@Override
	public List<User> findAll() {
		return em.createNamedQuery("findAllUsers").getResultList();
	}

	@Override
	public User findUserByMailAndPassword(String email, String password) {
		Query query = em.createNamedQuery("findUsersByEmailAndPassword");;
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<User> users = query.getResultList();
		if (users!=null && users.size()>0)
			return users.get(0);
		return null;
	}

	



	
}
