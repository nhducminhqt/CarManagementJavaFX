package pojo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CustomerID")
	private Long customerId;
	
	@Column(name = "CustomerName",nullable = false,length = 50)
	private String customerName;
	
	@Column(name = "Mobile",nullable = false,length = 50)
	private String mobile;
	
	@Column(name = "Birthday",nullable = false,length = 50)
	private LocalDate  birthday;
	
	@Column(name = "IdentityCard",nullable = false,length = 50)
	private String identityCard;
	
	@Column(name = "LicenceNumber",nullable = false,length = 50)
	private String licenceNumber;
	
	@Column(name = "LicenceDate",nullable = false,length = 50)
	private LocalDate  licenceDate;
	
	@Column(name = "Email",nullable = false,length = 50,unique = true)
	private String email;
	
	@Column(name = "Password",nullable = false,length = 50)
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "AccountID")
	private Account account;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDate  getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate  birthday) {
		this.birthday = birthday;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public LocalDate  getLicenceDate() {
		return licenceDate;
	}

	public void setLicenceDate(LocalDate  licenceDate) {
		this.licenceDate = licenceDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Customer(Long customerId, String customerName, String mobile, LocalDate  birthday, String identityCard,
			String licenceNumber, LocalDate  licenceDate, String email, String password, Account account) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.mobile = mobile;
		this.birthday = birthday;
		this.identityCard = identityCard;
		this.licenceNumber = licenceNumber;
		this.licenceDate = licenceDate;
		this.email = email;
		this.password = password;
		this.account = account;
	}

	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", identityCard="
				+ identityCard + ", licenceNumber=" + licenceNumber + ", email=" + email + "]";
	}

	
	
	
	
}
