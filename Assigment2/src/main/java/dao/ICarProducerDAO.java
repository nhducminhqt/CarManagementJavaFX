package dao;

import java.util.List;

import pojo.CarProducer;

public interface ICarProducerDAO {
	public List<CarProducer> getAll();

	public void save(CarProducer student);

	public void delete(Long studentID);

	public CarProducer findById(Long studentID);

	public void update(CarProducer student);
}
