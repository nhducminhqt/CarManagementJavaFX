package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pojo.CarRental;


public class CarRentalDAO implements ICarRentalDAO{
	private SessionFactory sessionFactory;
	private Configuration configuration;
	public CarRentalDAO(String name) {
		configuration = new Configuration();
		configuration = configuration.configure(name);
		sessionFactory = configuration.buildSessionFactory();
	}
	@Override
	public List<CarRental> getAll() {
		// TODO Auto-generated method stub
		List<CarRental> students = null;
		Session session = sessionFactory.openSession();
		try {
			students = session.createQuery("from CarRental", CarRental.class).list();
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
	public void save(CarRental student) {
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
			CarRental student = (CarRental) session.get(CarRental.class,studentID);
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
	public CarRental findById(Long studentID) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			return (CarRental) session.get(CarRental.class,studentID);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void update(CarRental student) {
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
	public List<CarRental> findByCustomerId(Long id) {
		// TODO Auto-generated method stub
		List<CarRental> customer = new ArrayList<CarRental>(); 
	    Session session = sessionFactory.openSession();
	    try {
	    	  // Native SQL query to fetch movies based on movie name or director name
	        String sql = "SELECT m.* FROM CarRental m "+ // Confirm column names match your DB schema
	                     "WHERE m.CustomerID = :Mname ";
	        
	        // Create the native query, specifying the result class
	        Query<CarRental> query = session.createNativeQuery(sql, CarRental.class);
	        
	        // Set query parameters for movie name and director name
	        query.setParameter("Mname",  id );
	        
	        // Execute query and get results
	        customer = query.getResultList();
	} catch (Exception e) {
		// TODO: handle exception
	}
		return customer;
	}
	@Override
	public List<CarRental> findByCarId(Long id) {
		// TODO Auto-generated method stub
		List<CarRental> customer = new ArrayList<CarRental>(); 
	    Session session = sessionFactory.openSession();
	    try {
	    	  // Native SQL query to fetch movies based on movie name or director name
	        String sql = "SELECT m.* FROM CarRental m "+ // Confirm column names match your DB schema
	                     "WHERE m.CarID = :Mname ";
	        
	        // Create the native query, specifying the result class
	        Query<CarRental> query = session.createNativeQuery(sql, CarRental.class);
	        
	        // Set query parameters for movie name and director name
	        query.setParameter("Mname",  id );
	        
	        // Execute query and get results
	        customer = query.getResultList();
	} catch (Exception e) {
		// TODO: handle exception
	}
		return customer;
	}
	@Override
	public int countOverlappingBookings(Long carId, LocalDate pickupDate, LocalDate returnDate) {
		// TODO Auto-generated method stub
		int count = 0;
		 Session session = sessionFactory.openSession();
		    try {
		    	  // Native SQL query to fetch movies based on movie name or director name
		    	 String hql = "SELECT COUNT(*) FROM CarRental cr " +
                         "WHERE cr.CarID = :carId " +
                         "AND ((:pickupDate BETWEEN cr.PickupDate AND cr.ReturnDate) " +
                         "     OR (:returnDate BETWEEN cr.PickupDate AND cr.returnDate) " +
                         "     OR (cr.PickupDate BETWEEN :pickupDate AND :returnDate) " +
                         "     OR (cr.ReturnDate BETWEEN :pickupDate AND :returnDate))";

		        
		    	  Query<Integer> query = session.createQuery(hql, Integer.class);
		            query.setParameter("carId", carId);
		            query.setParameter("pickupDate", pickupDate);
		            query.setParameter("returnDate", returnDate);
		        
		        // Execute query and get results
		        count = query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
			return count;
	}
	

}
