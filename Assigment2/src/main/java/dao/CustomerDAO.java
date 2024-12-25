package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pojo.Customer;

public class CustomerDAO implements ICustomerDAO{
	private SessionFactory sessionFactory;
	private Configuration configuration;
	
	public CustomerDAO(String name) {
		configuration = new Configuration();
		configuration = configuration.configure(name);
		sessionFactory = configuration.buildSessionFactory();
	}
	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		List<Customer> students = null;
		Session session = sessionFactory.openSession();
		try {
			students = session.createQuery("from Customer", Customer.class).list();
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
	public void save(Customer student) {
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
			Customer student = (Customer) session.get(Customer.class,studentID);
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
	public Customer findById(Long studentID) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			return (Customer) session.get(Customer.class,studentID);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Customer student) {
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

	@Override
	public List<Customer> searchByName(String name) {
		// TODO Auto-generated method stub
		List<Customer> list = new ArrayList<>();
	    Session session = sessionFactory.openSession();
	    try {
	    	  // Native SQL query to fetch movies based on movie name or director name
	        String sql = "SELECT m.* FROM Customer m "+ // Confirm column names match your DB schema
	                     "WHERE m.customerName LIKE :Mname ";
	        
	        // Create the native query, specifying the result class
	        Query<Customer> query = session.createNativeQuery(sql, Customer.class);
	        
	        // Set query parameters for movie name and director name
	        query.setParameter("Mname", "%" + name + "%");
	        
	        // Execute query and get results
	        list = query.getResultList();
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    } finally {
	        session.close();
	    }
	    return list;
	}
	@Override
	public Customer searchByEmail(String name) {
		// TODO Auto-generated method stub
		Customer customer = new Customer(); 
	    Session session = sessionFactory.openSession();
	    try {
	    	  // Native SQL query to fetch movies based on movie name or director name
	        String sql = "SELECT m.* FROM Customer m "+ // Confirm column names match your DB schema
	                     "WHERE m.Email = :Mname ";
	        
	        // Create the native query, specifying the result class
	        Query<Customer> query = session.createNativeQuery(sql, Customer.class);
	        
	        // Set query parameters for movie name and director name
	        query.setParameter("Mname",  name );
	        
	        // Execute query and get results
	        customer = query.getSingleResult();
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	    } finally {
	        session.close();
	    }
	    return customer;
	}

}
