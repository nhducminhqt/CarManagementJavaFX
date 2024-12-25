package pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="CarProducer")
public class CarProducer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ProducerID")
	private Long producerId;
	
	@Column(name = "ProcuderName",nullable = false,length = 50)
	private String procuderName;
	
	@Column(name = "Address",nullable = false,length = 50)
	private String address;
	
	@Column(name = "Country",nullable = false,length = 50)
	private String country;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ProducerID")
	private Set<Car> cars;

	public Long getProducerId() {
		return producerId;
	}

	public void setProducerId(Long producerId) {
		this.producerId = producerId;
	}

	public String getProcuderName() {
		return procuderName;
	}

	public void setProcuderName(String procuderName) {
		this.procuderName = procuderName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public CarProducer(Long producerId, String procuderName, String address, String country, Set<Car> cars) {
		super();
		this.producerId = producerId;
		this.procuderName = procuderName;
		this.address = address;
		this.country = country;
		this.cars = cars;
	}

	public CarProducer() {
		super();
	}

	@Override
	public String toString() {
		return this.getProcuderName();
	}
	
	
}
