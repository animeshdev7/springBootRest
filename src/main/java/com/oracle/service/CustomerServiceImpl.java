package com.oracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.CustomerDao;
import com.oracle.except.CustomerAlreadyPresentException;
import com.oracle.except.NoSuchCustomerException;
import com.oracle.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao dao;

	@Override
	public void addCustomer(Customer c) {
		try {	
		dao.createCustomer(c);
	} catch (Exception e) {
		throw new CustomerAlreadyPresentException("Another customer with same email is present!",e);
	}
	}

	@Override
	public Customer findCustomerByEmail(String email) {
		Customer customer = dao.readCustomerByEmail(email);
		if(customer == null) {
			throw new NoSuchCustomerException("Customer with email "+email+" not found!");
		}
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return dao.readAllCustomers();
	}

	@Override
	public void changeCustomerEmail(String email, Customer c) {
		dao.updateCustomerEmail(email, c);
	}

	@Override
	public Customer deleteCustomer(String email) {
		return dao.deleteCustomer(email);
	}

	
}
