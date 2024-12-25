package repo;

import java.time.LocalDate;
import java.util.List;

import pojo.CarRental;


public interface ICarRentalRepo {
	public List<CarRental> findAll();

	public void save(CarRental carProducer);

	public void delete(Long producerId);

	public CarRental findById(Long producerId);

	public void update(CarRental carProducer);
	
	public List<CarRental> findByCustomerId(Long id);
	
	public List<CarRental> findByCarId(Long id);
	
	public int countOverlappingBookings (Long carId, LocalDate pickupDate, LocalDate returnDate);
}
