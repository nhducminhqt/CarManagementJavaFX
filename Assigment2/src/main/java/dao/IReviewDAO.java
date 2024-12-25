package dao;

import java.util.List;

import pojo.Review;


public interface IReviewDAO {
	public List<Review> getAll();

	public void save(Review student);

	public void delete(Long studentID);

	public Review findById(Long studentID);

	public void update(Review student);
	
	
}
