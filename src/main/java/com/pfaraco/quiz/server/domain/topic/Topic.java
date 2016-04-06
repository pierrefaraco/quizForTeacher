package com.pfaraco.quiz.server.domain.topic;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.pfaraco.quiz.server.domain.DomainObject;
import com.pfaraco.quiz.server.domain.user.User;

@Entity
@NamedQueries({
	@NamedQuery(name = "findAllTopics", query="select o from Topic o"),
	//@NamedQuery(name = "findTopicsByUser", query="select o from Topic WHERE o.user.id = :userid "),
})


@Table(name = "topics")
public class Topic  extends DomainObject  implements  Serializable {	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="name", nullable = false, length = 40)
	private String name;
	@Column(name="description")
	private String description;
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name ="user_id", nullable = false)
	private User user;

	
	public Topic() {
		
	}
	
	public Topic(String name, String description ,User user) {
		super();
		this.name = name;
		this.description = description;
		this.user = user ;
	}
	
	public Topic(long id, String name, String description,User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user ;
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

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Topic))
            return false;
        Topic other = (Topic) obj;
        if (id != other.id)
            return false;
        return true;
    }
 
	
}
