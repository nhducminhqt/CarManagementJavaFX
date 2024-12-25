package pojo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="CarRental")
public class CarRental {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "CustomerID")
	private Customer customer;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "CarID")
	private Car car;
	
	@Column(name = "PickupDate",nullable = false)
	private LocalDate pickupDate;
	
	@Column(name = "ReturnDate",nullable = false)
	private LocalDate returnDate;
	
	@Column(name = "RentPrice",nullable = false)
	private float rentPrice;
	
	@Column(name = "Status",nullable = false,length = 30)
	private String status;

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

	public LocalDate getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(LocalDate pickupDate) {
		if (returnDate != null && pickupDate.isAfter(returnDate)) {
            throw new IllegalArgumentException("PickupDate must be before ReturnDate.");
        }
		this.pickupDate = pickupDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		if (pickupDate != null && pickupDate.isAfter(returnDate)) {
            throw new IllegalArgumentException("PickupDate must be before ReturnDate.");
        }
		this.returnDate = returnDate;
	}

	public float getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CarRental(Customer customer, Car car, LocalDate pickupDate, LocalDate returnDate, float rentPrice, String status) {
		super();
		if (pickupDate.isAfter(returnDate)) {
            throw new IllegalArgumentException("PickupDate must be before ReturnDate.");
        }
		this.customer = customer;
		this.car = car;
		this.pickupDate = pickupDate;
		this.returnDate = returnDate;
		this.rentPrice = rentPrice;
		this.status = status;
	}

	public CarRental() {
		super();
	}

	@Override
	public String toString() {
		return "CarRental [id=" + id + ", customer=" + customer + ", car=" + car + ", pickupDate=" + pickupDate
				+ ", returnDate=" + returnDate + ", rentPrice=" + rentPrice + ", status=" + status + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
