package com.appsdeveloperblog.photoapp.api.users.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class UserEntity implements Serializable {


	private static final long serialVersionUID = 6961928337506620516L;
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable=false,length=50)
	private String firstName;
	@Column(nullable=false,length=50)
	private String lastName;
	@Column
	private String email;
	@Column(nullable=false,unique = true)
	private String userId;
	@Column(nullable=false)
	private String encryptedPassword;
	
	
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getEncryptedPassport() {
		return encryptedPassword;
	}
	public void setEncryptedPassport(String encryptedPassport) {
		this.encryptedPassword = encryptedPassport;
	}

	
	
	
	
	
	

}
