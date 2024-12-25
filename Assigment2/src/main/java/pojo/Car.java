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
@Table(name="Car")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CarID")
	private Long carId;
	
	@Column(name = "CarName",nullable = false,length = 50)
	private String carName;
	
	@Column(name = "CarModelYear",nullable = false)
	private int carModelYear;
	
	@Column(name = "Color",nullable = false,length = 50)
	private String color;
	
	@Column(name = "Capacity",nullable = false)
	private int capacity;
	
	@Column(name = "Decription",nullable = false)
	private String description;
	
	@Column(name = "ImportDate",nullable = false)
	private LocalDate  importDate;
	
	@Column(name = "RentPrice",nullable = false)
	private float rentPrice;
	
	@Column(name = "Status",nullable = false)
	private String status;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ProducerID")
	private CarProducer carProducer;

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public int getCarModelYear() {
		return carModelYear;
	}

	public void setCarModelYear(int carModelYear) {
		this.carModelYear = carModelYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate  getImportDate() {
		return importDate;
	}

	public void setImportDate(LocalDate  importDate) {
		this.importDate = importDate;
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

	public CarProducer getCarProducer() {
		return carProducer;
	}

	public void setCarProducer(CarProducer carProducer) {
		this.carProducer = carProducer;
	}

	public Car(Long carId, String carName, int carModelYear, String color, int capacity, String description,
			LocalDate  importDate, float rentPrice, String status, CarProducer carProducer) {
		super();
		this.carId = carId;
		this.carName = carName;
		this.carModelYear = carModelYear;
		this.color = color;
		this.capacity = capacity;
		this.description = description;
		this.importDate = importDate;
		this.rentPrice = rentPrice;
		this.status = status;
		this.carProducer = carProducer;
	}

	public Car() {
		super();
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carName=" + carName + ", carModelYear=" + carModelYear + ", color=" + color
				+ ", capacity=" + capacity + ", rentPrice=" + rentPrice + "]";
	}

	

	
	
	
}
