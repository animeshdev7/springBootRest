package com.oracle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oracle.except.CustomerAlreadyPresentException;
import com.oracle.model.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public void createCustomer(Customer c) {		
			entityManager.persist(c);
	}

	@Override
	public Customer readCustomerByEmail(String email) {
		System.out.println(entityManager.find(Customer.class, email));
		return entityManager.find(Customer.class, email);
	}

	@Override
	public List<Customer> readAllCustomers() {
		// Case-sensitive
		String jpql = "FROM Customer"; // OR "SELECT c FROM Customer c"
		return entityManager.createQuery(jpql,Customer.class).getResultList();
	}

	@Override
	@Transactional
	public void updateCustomerEmail(String email, Customer c) {
		 Customer customer = entityManager.find(Customer.class, email);
		    if (customer == null) {
		       System.out.print("Customer with email '" + email + "' not found.");
		    }
		
		    entityManager.remove(customer);
		    c.setEmail(email);
		    entityManager.persist(c);
	}

	@Override
	@Transactional
	public Customer deleteCustomer(String email) {
	    Customer customer = entityManager.find(Customer.class, email);
	    if (customer != null) {
	        entityManager.remove(customer);
	    }
	    return customer; 
	}

}
