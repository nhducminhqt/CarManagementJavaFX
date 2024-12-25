package repo;

import java.util.List;

import pojo.Review;


public interface IReview {
	public List<Review> findAll();

	public void save(Review carProducer);

	public void delete(Review producerId);

	public Review findById(Long producerId);

	public void update(Review carProducer);
}
