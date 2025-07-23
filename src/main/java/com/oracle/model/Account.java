package com.oracle.model;import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component("account1")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "acctable")
public class Account {
	@Column(name = "accId")
	@Id
	private int accountId;
	@Column(name = "balance")
	private double balance;
	
	public Account(int accountId, double balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
	}
	public Account() {
		super();

//		System.out.print("This is account class");
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + "]";
	}
	
	
	
	
}
