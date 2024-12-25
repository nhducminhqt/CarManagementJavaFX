package repo;

import java.util.List;

import dao.CustomerDAO;
import dao.ICustomerDAO;
import pojo.Customer;

public class CustomerRepo implements ICustomerRepo{
	private ICustomerDAO customerDAO;
	
	public CustomerRepo(String name) {
		this.customerDAO = new CustomerDAO(name);
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerDAO.getAll();
	}

	@Override
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		customerDAO.save(customer);
	}

	@Override
	public void delete(Long customerId) {
		// TODO Auto-generated method stub
		customerDAO.delete(customerId);
	}

	@Override
	public Customer findById(Long customerId) {
		// TODO Auto-generated method stub
		return customerDAO.findById(customerId);
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		customerDAO.update(customer);
	}

	@Override
	public List<Customer> searchByName(String name) {
		// TODO Auto-generated method stub
		return customerDAO.searchByName(name);
	}

	@Override
	public Customer searchByEmail(String name) {
		// TODO Auto-generated method stub
		return customerDAO.searchByEmail(name);
	}

}
