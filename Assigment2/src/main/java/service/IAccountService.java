package service;

import java.util.List;

import pojo.Account;

public interface IAccountService {
	public List<Account> findAll();

	public void save(Account car);

	public void delete(Long carId);

	public Account findById(Long carId);

	public void update(Account car);
}
