package dao;

import java.util.List;

import pojo.Customer;



public interface ICustomerDAO {
	public List<Customer> getAll();

	public void save(Customer student);

	public void delete(Long studentID);

	public Customer findById(Long studentID);

	public void update(Customer student);
	
	public List<Customer> searchByName(String name);
	
	public Customer searchByEmail(String name);
}
