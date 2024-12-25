package repo;

import java.util.List;

import pojo.CarProducer;

public interface ICarProducerRepo {
	public List<CarProducer> findAll();

	public void save(CarProducer carProducer);

	public void delete(Long producerId);

	public CarProducer findById(Long producerId);

	public void update(CarProducer carProducer);
}
