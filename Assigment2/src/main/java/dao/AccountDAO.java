package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pojo.Account;

public class AccountDAO implements IAccountDAO{
	private SessionFactory sessionFactory;
	private Configuration configuration;
	
	public AccountDAO(String name) {
		configuration = new Configuration();
		configuration = configuration.configure(name);
		sessionFactory = configuration.buildSessionFactory();
	}
	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		List<Account> students = null;
		Session session = sessionFactory.openSession();
		try {
			students = session.createQuery("from Account", Account.class).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error" + e.getMessage());
		} finally {
			//sessionFactory.close();
			session.close();
		}
		return students;
	}

	@Override
	public void save(Account student) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(student);
			t.commit();
			System.out.println("Successfully saved");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			System.out.println("Error" + e.getMessage());
		} finally {
		//	sessionFactory.close();
			session.close();
		}
	}

	@Override
	public void delete(Long studentID) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Account student = (Account) session.get(Account.class,studentID);
			session.delete(student);
			t.commit();
			System.out.println("Delete saved");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			throw e;
		} finally {
//			sessionFactory.close();
			session.close();
		}
	}

	@Override
	public Account findById(Long studentID) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			return (Account) session.get(Account.class,studentID);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Account student) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(student);
			t.commit();
			System.out.println("Update successfully");
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			System.out.println("Error "+e.getMessage());
		} finally {
			//sessionFactory.close();
			session.close();
		}
	}

}
