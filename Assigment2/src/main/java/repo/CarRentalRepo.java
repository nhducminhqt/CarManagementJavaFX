package repo;

import java.time.LocalDate;
import java.util.List;

import dao.CarRentalDAO;
import dao.ICarRentalDAO;
import pojo.CarRental;

public class CarRentalRepo implements ICarRentalRepo{
	private ICarRentalDAO carRentalDAO;
	
	public CarRentalRepo(String name) {
		this.carRentalDAO = new CarRentalDAO(name);
	}

	@Override
	public List<CarRental> findAll() {
		// TODO Auto-generated method stub
		return carRentalDAO.getAll();
	}

	@Override
	public void save(CarRental carProducer) {
		// TODO Auto-generated method stub
		carRentalDAO.save(carProducer);
	}

	@Override
	public void delete(Long producerId) {
		// TODO Auto-generated method stub
		carRentalDAO.delete(producerId);
	}

	@Override
	public CarRental findById(Long producerId) {
		// TODO Auto-generated method stub
		return carRentalDAO.findById(producerId);
	}

	@Override
	public void update(CarRental carProducer) {
		// TODO Auto-generated method stub
		carRentalDAO.update(carProducer);
	}

	@Override
	public List<CarRental> findByCustomerId(Long id) {
		// TODO Auto-generated method stub
		return carRentalDAO.findByCustomerId(id);
	}

	@Override
	public List<CarRental> findByCarId(Long id) {
		// TODO Auto-generated method stub
		return carRentalDAO.findByCarId(id);
	}

	@Override
	public int countOverlappingBookings(Long carId, LocalDate pickupDate, LocalDate returnDate) {
		// TODO Auto-generated method stub
		return carRentalDAO.countOverlappingBookings(carId, pickupDate, returnDate);
	}

}
