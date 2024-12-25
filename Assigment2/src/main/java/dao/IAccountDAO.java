package dao;

import java.util.List;

import pojo.Account;



public interface IAccountDAO {
	public List<Account> getAll();

	public void save(Account student);

	public void delete(Long studentID);

	public Account findById(Long studentID);

	public void update(Account student);
	
	
}
