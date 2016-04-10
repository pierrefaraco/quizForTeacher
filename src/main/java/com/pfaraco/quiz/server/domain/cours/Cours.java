package com.pfaraco.quiz.server.domain.cours;

import java.io.Serializable;
import java.util.Map;

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











import com.pfaraco.quiz.server.domain.DomainObject;
import com.pfaraco.quiz.server.domain.user.User;
import com.pfaraco.quiz.server.enums.SuscriberStatus;

@Entity
@NamedQueries({
	@NamedQuery(name = "findAllCours", query="select o from Cours o"),
	@NamedQuery(name = "findCoursByUser", query="select o from Cours o WHERE o.user.id = :userid "),
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
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name ="user_id", nullable = false)
	private User user;
	@ElementCollection
	@MapKeyJoinColumn(name="user_id")
	@Column(name="suscriber_status")
	private Map <User,SuscriberStatus> suscribers;
	
	
	
	public Cours(long id, String name, String description, User user,
			Map<User, SuscriberStatus> suscribers) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
		this.suscribers = suscribers;
	}
	
	
	
	public Cours(String name, String description, User user,
			Map<User, SuscriberStatus> suscribers) {
		super();
		this.name = name;
		this.description = description;
		this.user = user;
		this.suscribers = suscribers;
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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Map<User, SuscriberStatus> getSuscribers() {
		return suscribers;
	}



	public void setSuscribers(Map<User, SuscriberStatus> suscribers) {
		this.suscribers = suscribers;
	}



	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setId(long id) {
		this.id=id;
		
	}
}
