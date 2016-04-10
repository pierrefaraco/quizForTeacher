package com.pfaraco.quiz.server.domain.sequence;

import java.util.List;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.util.persistence.AbstractDataAccessObject;

public interface SequenceDao extends AbstractDataAccessObject <Sequence,Long> {
	List<Sequence>findByUser(User user);
	List<Sequence>findAll();
}

