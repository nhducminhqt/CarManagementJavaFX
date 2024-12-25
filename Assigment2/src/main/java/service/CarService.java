package service;

import java.util.List;

import pojo.Car;
import repo.CarRepo;
import repo.ICarRepo;

public class CarService implements ICarService{
	private ICarRepo carRepo;
	
	public CarService(String name) {
		this.carRepo = new CarRepo(name);
	}

	@Override
	public List<Car> findAll() {
		// TODO Auto-generated method stub
		return carRepo.findAll();
	}

	@Override
	public void save(Car car) {
		// TODO Auto-generated method stub
		carRepo.save(car);
	}

	@Override
	public void delete(Long carId) {
		// TODO Auto-generated method stub
		carRepo.delete(carId);
	}

	@Override
	public Car findById(Long carId) {
		// TODO Auto-generated method stub
		return carRepo.findById(carId);
	}

	@Override
	public void update(Car car) {
		// TODO Auto-generated method stub
		carRepo.update(car);
	}

	@Override
	public List<Car> searchByName(String name) {
		// TODO Auto-generated method stub
		return carRepo.searchByName(name);
	}
	
}
