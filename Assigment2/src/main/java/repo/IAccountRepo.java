package repo;

import java.util.List;

import pojo.Account;

public interface IAccountRepo {
	public List<Account> findAll();

	public void save(Account account);

	public void delete(Long accountId);

	public Account findById(Long accountId);

	public void update(Account account);
}
