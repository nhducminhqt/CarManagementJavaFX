package dao;

import java.util.List;

import pojo.Car;



public interface ICarDAO {
	public List<Car> getAll();

	public void save(Car student);

	public void delete(Long studentID);

	public Car findById(Long studentID);

	public void update(Car student);
	
	public List<Car> searchByName(String name);
}
