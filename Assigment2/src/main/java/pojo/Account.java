package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AccountID")
	private Long accountId;
	
	@Column(name = "AccountName",nullable = false,length = 50)
	private String accountName;
	
	@Column(name = "Role",nullable = false, length = 20)
	private String role;
	
	@OneToOne(mappedBy = "account")
	private Customer customer;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account(Long accountId, String accountName, String role, Customer customer) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.role = role;
		this.customer = customer;
	}
	
	public Account() {
		
	}
	
	
}
