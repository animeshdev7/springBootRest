package com.oracle.dao;

import java.util.List;

import com.oracle.model.Customer;

public interface CustomerDao {
	public void createCustomer(Customer c);
	public Customer readCustomerByEmail(String email);
	public List<Customer> readAllCustomers();
	public void updateCustomerEmail(String email, Customer c);
	public Customer deleteCustomer(String email);
}
