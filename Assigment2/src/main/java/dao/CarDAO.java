package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pojo.Car;

public class CarDAO implements ICarDAO{
	private SessionFactory sessionFactory;
	private Configuration configuration;
	
	public CarDAO(String name) {
		configuration = new Configuration();
		configuration = configuration.configure(name);
		sessionFactory = configuration.buildSessionFactory();
	}
	
	@Override
	public void save(Car student) {
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
			Car student = (Car) session.get(Car.class,studentID);
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
	public Car findById(Long studentID) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			return (Car) session.get(Car.class,studentID);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void update(Car student) {
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
	public List<Car> searchByName(String name) {
		// TODO Auto-generated method stub
		List<Car> list = new ArrayList<>();
	    Session session = sessionFactory.openSession();
	    try {
	    	  // Native SQL query to fetch movies based on movie name or director name
	        String sql = "SELECT m.* FROM Car m "+ // Confirm column names match your DB schema
	                     "WHERE m.CarName LIKE :Mname ";
	        
	        // Create the native query, specifying the result class
	        Query<Car> query = session.createNativeQuery(sql, Car.class);
	        
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
	public List<Car> getAll() {
		// TODO Auto-generated method stub
		List<Car> students = null;
		Session session = sessionFactory.openSession();
		try {
			students = session.createQuery("from Car", Car.class).list();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error" + e.getMessage());
		} finally {
			//sessionFactory.close();
			session.close();
		}
		return students;
	}
	
}
