package pojo;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Review {
	 @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CustomerID")
	private Customer customer;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CarID")
	private Car car;
	
	@Column(name = "ReviewStar",nullable = false)
	private int reviewStar;
	
	@Column(name = "Comment")
	private String commnet;
	
	

	public Review(Customer customer, Car car, int reviewStar, String commnet) {
		super();
		if (reviewStar < 1 || reviewStar > 5) {
            throw new IllegalArgumentException("ReviewStar must be between 1 and 5.");
        }
		this.customer = customer;
		this.car = car;
		this.reviewStar = reviewStar;
		this.commnet = commnet;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public int getReviewStar() {
		return reviewStar;
	}

	public void setReviewStar(int reviewStar) {
		 if (reviewStar < 1 || reviewStar > 5) {
	            throw new IllegalArgumentException("ReviewStar must be between 1 and 5.");
	        }
		this.reviewStar = reviewStar;
	}

	public String getCommnet() {
		return commnet;
	}

	public void setCommnet(String commnet) {
		this.commnet = commnet;
	}
	
	
}
