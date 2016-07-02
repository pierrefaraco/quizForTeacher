package com.cnam.quiz.server.domain.sequence;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.util.persistence.AbstractDataAccessObjectImpl;

@Repository("sequenceDao")
public class SequenceDaoImpl  extends AbstractDataAccessObjectImpl <Sequence,Long>  implements	SequenceDao {

	@PostConstruct
	public void setEntityClass() {
		super.setEntityClass(Sequence.class);	
	}

	@Override
	public List<Sequence> findByUser(User user) {
		Query query = em.createNamedQuery("findSequencesByUser");;
		query.setParameter("userid", user.getId());
		return query .getResultList();
	}

	@Override
	public List<Sequence> findAll() {	
		return em.createNamedQuery("findAllSequences").getResultList();
	}
	
	

}
