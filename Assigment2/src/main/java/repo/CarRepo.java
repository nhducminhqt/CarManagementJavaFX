package repo;

import java.util.List;

import dao.CarDAO;
import dao.ICarDAO;
import pojo.Car;

public class CarRepo implements ICarRepo{
	private ICarDAO carDAO;
	
	
	
	public CarRepo(String name) {
		carDAO = new CarDAO(name);
	}

	@Override
	public List<Car> findAll() {
		// TODO Auto-generated method stub
		return carDAO.getAll();
	}

	@Override
	public void save(Car car) {
		// TODO Auto-generated method stub
		carDAO.save(car);
	}

	@Override
	public void delete(Long carId) {
		// TODO Auto-generated method stub
		carDAO.delete(carId);
	}

	@Override
	public Car findById(Long carId) {
		// TODO Auto-generated method stub
		return carDAO.findById(carId);
	}

	@Override
	public void update(Car car) {
		// TODO Auto-generated method stub
		carDAO.update(car);
	}

	@Override
	public List<Car> searchByName(String name) {
		// TODO Auto-generated method stub
		return carDAO.searchByName(name);
	}

}
