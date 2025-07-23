package com.oracle.service;

import java.util.List;

import com.oracle.model.Customer;

public interface CustomerService {
	public void addCustomer(Customer c);
	public Customer findCustomerByEmail(String email);
	public List<Customer> getAllCustomers();
	public void changeCustomerEmail(String email, Customer c);
	public Customer deleteCustomer(String email);
}
