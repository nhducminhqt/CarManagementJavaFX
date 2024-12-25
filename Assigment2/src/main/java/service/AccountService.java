package service;

import java.util.List;

import pojo.Account;
import repo.AccountRepo;
import repo.IAccountRepo;

public class AccountService implements IAccountService{
	private IAccountRepo accountRepo;
	
	public AccountService(String name) {
		accountRepo = new AccountRepo(name);
	}
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountRepo.findAll();
	}

	@Override
	public void save(Account car) {
		// TODO Auto-generated method stub
		accountRepo.save(car);
	}

	@Override
	public void delete(Long carId) {
		// TODO Auto-generated method stub
		accountRepo.delete(carId);
	}

	@Override
	public Account findById(Long carId) {
		// TODO Auto-generated method stub
		return accountRepo.findById(carId);
	}

	@Override
	public void update(Account car) {
		// TODO Auto-generated method stub
		accountRepo.update(car);
	}

}
