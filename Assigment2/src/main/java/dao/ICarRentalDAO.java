package dao;

import java.time.LocalDate;
import java.util.List;

import pojo.CarRental;

public interface ICarRentalDAO {
	public List<CarRental> getAll();

	public void save(CarRental student);

	public void delete(Long studentID);

	public CarRental findById(Long studentID);

	public void update(CarRental student);
	
	public List<CarRental> findByCustomerId(Long id);
	
	public List<CarRental> findByCarId(Long id);
	
	public int countOverlappingBookings (Long carId, LocalDate pickupDate, LocalDate returnDate);
	
}
