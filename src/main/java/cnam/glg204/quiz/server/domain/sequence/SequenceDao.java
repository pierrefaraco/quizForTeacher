package cnam.glg204.quiz.server.domain.sequence;

import java.util.List;

import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.util.persistence.AbstractDataAccessObject;

public interface SequenceDao extends AbstractDataAccessObject <Sequence,Long> {
	List<Sequence>findByUser(User user);
	List<Sequence>findAll();
}

