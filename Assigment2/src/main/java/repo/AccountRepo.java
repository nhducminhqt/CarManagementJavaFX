package repo;

import java.util.List;

import dao.AccountDAO;
import dao.IAccountDAO;
import pojo.Account;

public class AccountRepo implements IAccountRepo{
	private IAccountDAO accountDAO;
	public AccountRepo(String name) {
		accountDAO = new AccountDAO(name);
	}
	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountDAO.getAll();
	}
	@Override
	public void save(Account account) {
		// TODO Auto-generated method stub
		accountDAO.save(account);
	}
	@Override
	public void delete(Long accountId) {
		// TODO Auto-generated method stub
		accountDAO.delete(accountId);
	}
	@Override
	public Account findById(Long accountId) {
		// TODO Auto-generated method stub
		return accountDAO.findById(accountId);
	}
	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		accountDAO.update(account);
	}
	
	
}
