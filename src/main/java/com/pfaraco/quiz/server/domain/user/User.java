package com.pfaraco.quiz.server.domain.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pfaraco.quiz.server.domain.topic.Topic;
@Entity
@NamedQueries({
	@NamedQuery(name = "findAllUsers", query="select o from User o")
})

@Table(name = "users")
public class User implements  Serializable{
	@Id
	@Column(name="id")
	private long id;
	@Column(name="firstName", nullable = false, length = 40)
	private String firstName;
	@Column(name="lastName", nullable = false, length = 40)
	private String lastName;
	@Column(name="birthday")
	private Date birthDay;
	@Column(name="email", nullable = false, length = 40)
	private String email;
	@Column(name="presentation")
	private String presentation;
	@Column(name="login", nullable = false, length = 40)
	private String login;
	@Column(name="password", nullable = false, length = 40)
	private String password;
	@Column(name="account_type", nullable = false, length = 1)
	private int accountType;	

	
	public User(){
	}
	

	public User(long id, String firstName, String lastName, Date birthDay,
			String email, String presentation, String login, String password,
			int accountType) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDay = birthDay;
		this.email = email;
		this.presentation = presentation;
		this.login = login;
		this.password = password;
		this.accountType = accountType;
	
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPresentation() {
		return presentation;
	}
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int account_type) {
		this.accountType = account_type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountType;
		result = prime * result
				+ ((birthDay == null) ? 0 : birthDay.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((presentation == null) ? 0 : presentation.hashCode());
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
		User other = (User) obj;
		if (accountType != other.accountType)
			return false;
		if (birthDay == null) {
			if (other.birthDay != null)
				return false;
		} else if (!birthDay.equals(other.birthDay))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (presentation == null) {
			if (other.presentation != null)
				return false;
		} else if (!presentation.equals(other.presentation))
			return false;
		return true;
	}

	
}
