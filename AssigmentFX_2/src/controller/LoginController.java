package controller;

import java.io.IOException;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pojo.Account;
import pojo.Customer;
import service.AccountService;
import service.CustomerService;
import service.IAccountService;
import service.ICustomerService;

public class LoginController {

	
	private ICustomerService customerService;
	private IAccountService accountService;
	public LoginController() {
		String name ="hibernate.cfg.xml";
		customerService = new CustomerService("hibernate.cfg.xml");
		accountService = new AccountService(name);
	}
	@FXML Button btnLogin;
	@FXML Button btnCancel;
	@FXML TextField txtPassword;
	@FXML TextField txtEmail;
	
	@FXML
	public void btnLoginAction () throws IOException {
		String email = txtEmail.getText();
		String pass = txtPassword.getText();
		Customer customer = customerService.searchByEmail(email);
		Account account = accountService.findById(customer.getAccount().getAccountId());
		if(customer !=null && customer.getPassword().equals(pass) ) {
			Stage win = (Stage) txtEmail.getScene().getWindow();
			win.close();
			System.out.println(account.getRole());
			if(account.getRole().equals( "customer") ) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/Customer.fxml"));
			Parent root = loader.load();
			CustomerController cController = loader.getController();
			cController.setCustomerId(customer.getCustomerId());
//			CustomerController Ccontroller = new CustomerController(customer);
//			loader.setController(Ccontroller);
			
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.show();
			} else if(account.getRole().equals( "admin")){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/admin.fxml"));
				Parent root = loader.load();
				AdminController aController = loader.getController();
//				aController.setCustomerId(customer.getCustomerId());
//				CustomerController Ccontroller = new CustomerController(customer);
//				loader.setController(Ccontroller);
				
				Scene scene = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText("Login unsuccessfully");
				alert.setContentText("You have no permission to access this function!");
				alert.showAndWait();
			}}
			else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText("Login unsuccessfully");
				alert.setContentText("You have no permission to access this function!");
				alert.showAndWait();
			}
		
		} 
	
	
	@FXML
	public void btnSignupAction() throws IOException {
		Platform.exit();
	}
}
