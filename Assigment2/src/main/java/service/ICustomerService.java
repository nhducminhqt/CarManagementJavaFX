package service;

import java.util.List;

import pojo.Customer;

public interface ICustomerService {
	public List<Customer> findAll();

	public void save(Customer customer);

	public void delete(Long customerId);

	public Customer findById(Long customerId);

	public void update(Customer customer);
	
	public List<Customer> searchByName(String name);
	
	public Customer searchByEmail(String name);
}
