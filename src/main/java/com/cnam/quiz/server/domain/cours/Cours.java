package com.cnam.quiz.server.domain.cours;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


















import org.hibernate.annotations.Cascade;

import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.domain.DomainObject;
import com.cnam.quiz.server.domain.user.User;

@Entity
@NamedQueries({
	@NamedQuery(name = "findAllCours", query="select o from Cours o"),
	@NamedQuery(name = "findCoursByProfessor", query="select o from Cours o where o.user.id = :userid "),
	@NamedQuery(name = "findCoursByAuditor", query="select   o from Cours o where :user in o.subscribers"),
	@NamedQuery(name = "findAllActivesCours", query="select o from Cours o where o.active = 1"),
	
})


@Table(name = "cours")
public class Cours extends DomainObject  implements  Serializable{
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="name", nullable = false, length = 40)
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="active", nullable = false)
	boolean active;
	@ManyToOne(fetch =FetchType.LAZY )   
	@JoinColumn(name ="user_id", nullable = false)
	private User user;
	@ElementCollection
	@MapKeyJoinColumn(name="user_id")
	@Column(name="suscriber_status" , nullable =   false)
	private Map <User,SubscriberStatus> subscribers;
	
	public Cours(long id, String name, String description,boolean active, User user,
			Map<User, SubscriberStatus> subscribers) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
		this.subscribers = subscribers;
		this.active = active;
	}	
	
	public Cours(String name, String description,boolean active, User user,
			Map<User, SubscriberStatus> subscribers) {
		super();
		this.name = name;
		this.description = description;
		this.user = user;
		this.subscribers = subscribers;
		this.active = active;
	}	

	public Cours() {
		super();
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
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Map<User, SubscriberStatus> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(Map<User, SubscriberStatus> suscribers) {
		this.subscribers = suscribers;
	}

	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id) {
		this.id=id;	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((subscribers == null) ? 0 : subscribers.hashCode());
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
		Cours other = (Cours) obj;
		if (active != other.active)
			return false;
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
		if (subscribers == null) {
			if (other.subscribers != null)
				return false;
		} else if (!subscribers.equals(other.subscribers))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	
}

