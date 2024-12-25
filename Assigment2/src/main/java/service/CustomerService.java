package service;

import java.util.List;

import pojo.Customer;
import repo.CustomerRepo;
import repo.ICustomerRepo;

public class CustomerService implements ICustomerService{
	private ICustomerRepo customerRepo;
	
	public CustomerService(String name) {
		
		this.customerRepo = new CustomerRepo(name);
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerRepo.findAll();
	}

	@Override
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.save(customer);
	}

	@Override
	public void delete(Long customerId) {
		// TODO Auto-generated method stub
		customerRepo.delete(customerId);
	}

	@Override
	public Customer findById(Long customerId) {
		// TODO Auto-generated method stub
		return customerRepo.findById(customerId);
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.update(customer);
	}

	@Override
	public List<Customer> searchByName(String name) {
		// TODO Auto-generated method stub
		return customerRepo.searchByName(name);
	}

	@Override
	public Customer searchByEmail(String name) {
		// TODO Auto-generated method stub
		return customerRepo.searchByEmail(name);
	}
	
}
