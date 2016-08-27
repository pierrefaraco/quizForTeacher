package cnam.glg204.quiz.server.domain.topic;

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

import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.server.domain.DomainObject;
import cnam.glg204.quiz.server.domain.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@NamedQueries({
	@NamedQuery(name = "findAllTopics", query="select o from Topic o"),
	@NamedQuery(name = "findTopicsByUser", query="select o from Topic o WHERE o.user.id = :userid "),
})


@Table(name = "topics")
public class Topic  extends DomainObject  implements  Serializable {	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	@Column(name="name", nullable = false)
	private String name;
	@Column(name="description", length = 2048)
	private String description;
	//@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="user_id", nullable = false)
	private User user;
	
	public Topic() {
		super();
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

	@Override
	public void checkData() throws CheckException {
		if ( name == null || name.equals(""))
			throw new CheckException("name can't be null or empty");
		
		if ( user == null)
			throw new CheckException("no professor linked to this topic");
		
		user.checkData();	
	}

 
	
}
