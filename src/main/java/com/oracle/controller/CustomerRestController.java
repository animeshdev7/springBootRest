package com.oracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.oracle.except.CustomerAlreadyPresentException;
import com.oracle.except.NoSuchCustomerException;
import com.oracle.model.Customer;
import com.oracle.service.CustomerService;

@RestController
@RequestMapping(path="/customer-api/")
public class CustomerRestController {
	
	@Autowired
	private CustomerService custServ;
	
	//http://localhost:9090/customer-api/
	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody Customer c) {
		custServ.addCustomer(c);
		ResponseEntity<String> Resp = new ResponseEntity<String>("Customer created successfully!", HttpStatus.CREATED);
        return Resp;
	}
	
	@GetMapping("/getCustomer")
	public Customer getCustomerByEmail(@RequestParam String email) {
		return custServ.findCustomerByEmail(email);
	}
	
	@GetMapping("/all-customers")
    public List<Customer> getAllCustomers() {
        return custServ.getAllCustomers();
    }
	
	// RequestParam : http://localhost:9090/customer-api/update?email=test3@gmail.com 
	// Use : @RequestParam String email
    @PutMapping("/update/{email}")
    public ResponseEntity<String> updateCustomerEmail(@PathVariable String email, @RequestBody Customer c) {
        custServ.changeCustomerEmail(email, c);
         ResponseEntity<String> Resp = new ResponseEntity<String>("Customer updated successfully!", HttpStatus.ACCEPTED);
         return Resp;
    } 
    
    @DeleteMapping("/delete-user/{email}")
    public Customer delCustomer(@PathVariable String email) {
    	return custServ.deleteCustomer(email);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNoSuchCustomerException(NoSuchCustomerException ex){
    ResponseEntity<String> str	= new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    return str;
    } 
    	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleCustomerAlreadyPresentException(CustomerAlreadyPresentException ex){
    ResponseEntity<String> str	= new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    return str;
    } 
    
    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<?> handleBadInput(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}
