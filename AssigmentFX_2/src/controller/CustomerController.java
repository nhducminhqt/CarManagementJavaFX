package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import service.CustomerService;
import service.ICustomerService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.StackPane;

public class CustomerController implements Initializable,Navigate{
	private Long customerId;
	
	
@FXML
private void btnProfileAction () throws IOException {
	
	navigateProfile(contentArea,customerId);
}

@FXML
private void btnTransactionAction() throws IOException {
	navigateTransactionHistory(contentArea,customerId);
}

@FXML
private void btnLogoutAction() {
	Platform.exit();
}

	private ICustomerService customerService;
	
	public CustomerController() {
		customerService = new CustomerService("hibernate.cfg.xml");
	}
	
	@FXML
	private StackPane contentArea;

	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		 TODO Auto-generated method stub
		 if (customerId != null) { // Ensure customerId is set
		        try {
		            setContent("Profile.fxml", contentArea,customerId);

		            // Load customer data based on customerId
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
	}
	
	

	

	public StackPane getContentArea() {
		return contentArea;
	}

	public void setContentArea(StackPane contentArea) {
		this.contentArea = contentArea;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	
	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
	
	
}
