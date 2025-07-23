package com.oracle.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Component
@Scope(scopeName = "prototype")
@Entity
@Table(name = "custtable")
public class Customer {
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "email")
	@Id
	private String email;
	
	@Autowired
	@OneToOne(cascade = CascadeType.PERSIST)
	private Account account1;

	public String getFirstName() {
		return firstName;
	}

	public Customer() {
		super();
//		System.out.print("\nThis is customer class");
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

	public Account getAccount() {
		return account1;
	}

	public void setAccount(Account account) {
		this.account1 = account;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", account1="
				+ account1 + "]";
	}
	
	
	
}
