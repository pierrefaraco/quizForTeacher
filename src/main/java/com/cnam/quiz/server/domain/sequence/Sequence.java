package com.cnam.quiz.server.domain.sequence;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.cnam.quiz.server.domain.DomainObject;
import com.cnam.quiz.server.domain.questions.Question;
import com.cnam.quiz.server.domain.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@NamedQueries({
	@NamedQuery(name = "findAllSequences", query="select o from Sequence o"),
	@NamedQuery(name = "findSequencesByUser", query="select o from Sequence o WHERE o.user.id = :userid "),
})


@Table(name = "sequences")
public class Sequence extends DomainObject  implements  Serializable {
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="name", nullable = false, length = 40)
	private String name;
	@Column(name="description")
	private String description;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="user_id", nullable =   false)
	private User user;
	@ManyToMany
	@JoinColumn(name ="sequence_id", nullable =   false)
	Map <Integer,Question> questions;
	
	
	public Sequence(long id, String name, String description, User user,
			Map<Integer, Question> questions) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
		this.questions = questions;
	}

	public Sequence(String name, String description, User user,
			Map<Integer, Question> questions) {
		super();
		this.name = name;
		this.description = description;
		this.user = user;
		this.questions = questions;
	}
	
	public Sequence(){
		super();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Map <Integer,Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Map <Integer,Question> questions) {
		this.questions = questions;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sequence other = (Sequence) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
