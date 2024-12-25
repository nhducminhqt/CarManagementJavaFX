package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import pojo.CarProducer;

public class CarProducerDAO implements ICarProducerDAO{
	private SessionFactory sessionFactory;
	private Configuration configuration;
	
	public CarProducerDAO(String name) {
		configuration = new Configuration();
		configuration = configuration.configure(name);
		sessionFactory = configuration.buildSessionFactory();
	}
	@Override
	public List<CarProducer> getAll() {
		// TODO Auto-generated method stub
		List<CarProducer> students = null;
		Session session = sessionFactory.openSession();
		try {
			students = session.createQuery("from CarProducer", CarProducer.class).list();
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
	public void save(CarProducer student) {
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
			CarProducer student = (CarProducer) session.get(CarProducer.class,studentID);
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
	public CarProducer findById(Long studentID) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			return (CarProducer) session.get(CarProducer.class,studentID);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void update(CarProducer student) {
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
