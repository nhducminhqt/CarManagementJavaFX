package controller;

import java.net.URL;
import java.util.ResourceBundle;

import pojo.Customer;
import service.CustomerService;
import service.ICustomerService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProfileController implements Initializable{
	@FXML
	private TextField txtLicenceNumber;
	
	@FXML
	private TextField txtMobile;
	@FXML
	private TextField txtName;
	@FXML
	private DatePicker txtBirthday;
	
	@FXML
	private DatePicker txtLicenceDay;
	
	@FXML
	private Label noti;
	
	@FXML
	private TextField txtIdentityCard;
	

	

	
	@FXML
	private void btnUpdateAction() {
		customer.setLicenceDate(txtLicenceDay.getValue());
		customer.setMobile(txtMobile.getText());
		customer.setLicenceNumber(txtLicenceNumber.getText());
		Customer customer = customerService.findById(cusID);
		customer.setBirthday(txtBirthday.getValue());
		customer.setCustomerName(txtName.getText());
		customer.setIdentityCard(txtIdentityCard.getText());

		customerService.update(customer);
		if(customer!= null)
		noti.setText("Update Successfully");
	}
	

	private void loadData() {
		customer = customerService.findById(cusID);
		txtName.setText(customer.getCustomerName());
		txtIdentityCard.setText(customer.getIdentityCard());
		txtLicenceDay.setValue(customer.getLicenceDate());
		txtLicenceNumber.setText(customer.getLicenceNumber());
		txtMobile.setText(customer.getMobile());
		txtBirthday.setValue(customer.getBirthday());
	}
	
	private Long cusID;
	private ICustomerService customerService;
	private Customer customer;
	public ProfileController() {
		customerService = new CustomerService("hibernate.cfg.xml");
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	public TextField getTxtName() {
		return txtName;
	}

	public void setTxtName(TextField txtName) {
		this.txtName = txtName;
	}

	public TextField getTxtIdentityCard() {
		return txtIdentityCard;
	}

	public void setTxtIdentityCard(TextField txtIdentityCard) {
		this.txtIdentityCard = txtIdentityCard;
	}

	public TextField getTxtLicenceNumber() {
		return txtLicenceNumber;
	}

	public void setTxtLicenceNumber(TextField txtLicenceNumber) {
		this.txtLicenceNumber = txtLicenceNumber;
	}

	public TextField getTxtMobile() {
		return txtMobile;
	}

	public void setTxtMobile(TextField txtMobile) {
		this.txtMobile = txtMobile;
	}

	public DatePicker getTxtBirthday() {
		return txtBirthday;
	}

	public void setTxtBirthday(DatePicker txtBirthday) {
		this.txtBirthday = txtBirthday;
	}

	public DatePicker getTxtLicenceDay() {
		return txtLicenceDay;
	}

	public void setTxtLicenceDay(DatePicker txtLicenceDay) {
		this.txtLicenceDay = txtLicenceDay;
	}

	public Label getNoti() {
		return noti;
	}

	public void setNoti(Label noti) {
		this.noti = noti;
	}

	public Long getCusID() {
		return cusID;
	}

	public void setCusID(Long cusID) {
		this.cusID = cusID;
		loadData();
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
