package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import pojo.CarRental;
import repo.CarRentalRepo;
import repo.ICarRentalRepo;

public class CarRentalService implements ICarRentalService{
	private ICarRentalRepo carRentalRepo;
	
	public CarRentalService(String name) {
		
		this.carRentalRepo = new CarRentalRepo(name);
	}

	@Override
	public List<CarRental> findAll() {
		// TODO Auto-generated method stub
		return carRentalRepo.findAll();
	}

	@Override
	public void save(CarRental carProducer) {
		// TODO Auto-generated method stub
		carRentalRepo.save(carProducer);
	}

	@Override
	public void delete(Long producerId) {
		// TODO Auto-generated method stub
		carRentalRepo.delete(producerId);
	}

	@Override
	public CarRental findById(Long producerId) {
		// TODO Auto-generated method stub
		return carRentalRepo.findById(producerId);
	}

	@Override
	public void update(CarRental carProducer) {
		// TODO Auto-generated method stub
		carRentalRepo.update(carProducer);
	}

	@Override
	public List<CarRental> findByCustomerId(Long id) {
		// TODO Auto-generated method stub
		return carRentalRepo.findByCustomerId(id);
	}

	@Override
	public List<CarRental> findByCarId(Long id) {
		// TODO Auto-generated method stub
		return carRentalRepo.findByCarId(id);
	}

	@Override
	public boolean countOverlappingBookings(Long carId, LocalDate pickupDate, LocalDate returnDate) {
		// TODO Auto-generated method stub
		boolean check = false;
		List<CarRental> list = carRentalRepo.findByCarId(carId);
		if(list.isEmpty())return true;
		for(CarRental rent : list) {
			if(pickupDate.isAfter(rent.getPickupDate()) && pickupDate.isBefore(rent.getReturnDate()))
				check= false;
			else if(returnDate.isAfter(rent.getPickupDate()) && returnDate.isBefore(rent.getReturnDate())) check = false;
			else if(rent.getPickupDate().isAfter(pickupDate) && rent.getPickupDate().isBefore(returnDate)) check = false;
			else if(rent.getReturnDate().isAfter(pickupDate) && rent.getReturnDate().isBefore(returnDate)) check = false;
			else check = true;
		}
		return check;
	}

	@Override
	public List<CarRental> report(LocalDate pickupDate, LocalDate returnDate) {	
		// TODO Auto-generated method stub
		List<CarRental> list = carRentalRepo.findAll();
		List<CarRental> report = new ArrayList<CarRental>();
		for(CarRental rent : list) {
			if(rent.getStatus().equals("complete")) {
			if(pickupDate.isAfter(rent.getPickupDate()) && pickupDate.isBefore(rent.getReturnDate()))
				report.add(rent);
			else if(returnDate.isAfter(rent.getPickupDate()) && returnDate.isBefore(rent.getReturnDate())) report.add(rent);
			else if(rent.getPickupDate().isAfter(pickupDate) && rent.getPickupDate().isBefore(returnDate)) report.add(rent);
			else if(rent.getReturnDate().isAfter(pickupDate) && rent.getReturnDate().isBefore(returnDate)) report.add(rent);
			}
		}
		return report;
	}

}
