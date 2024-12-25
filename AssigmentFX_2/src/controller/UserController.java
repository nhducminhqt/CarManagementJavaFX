package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import pojo.Account;
import pojo.Customer;
import service.AccountService;
import service.CustomerService;
import service.IAccountService;
import service.ICustomerService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserController implements Initializable{

    @FXML private TableColumn<Customer, String> email;
    @FXML private TableColumn<Customer, LocalDate> birthday;
    @FXML private TableColumn<Customer, String> identityCard;

    @FXML private TableColumn<Customer, String> licenceNumber;
    @FXML private TableColumn<Customer, LocalDate> licenceDate;
    @FXML private TableColumn<Customer, String> role;
    @FXML private TableColumn<Customer, String> accountName;
	@FXML private TableView<Customer> tbData;
    
    private ObservableList<Customer> tableModel;
    private ICustomerService customerService;
    private IAccountService accountService;
    @FXML private TextField txtLicenceNumber;
    @FXML private PasswordField txtPassword;
    @FXML private DatePicker txtBirthday;
    @FXML private DatePicker txtLicenceDate;
    @FXML private ComboBox<String> txtRole;
    @FXML private TableColumn<Customer, Long> id;
    @FXML private TableColumn<Customer, String> name;
    @FXML private TableColumn<Customer, String> mobile;
    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtMobile;
    @FXML private TextField txtEmail;
    @FXML private TextField txtAccountName;
    @FXML private TextField txtIdentityCard;

    

    
    public UserController() {
    	String file = "hibernate.cfg.xml";
    	customerService = new CustomerService(file);
    	accountService = new AccountService(file);
    	tableModel = FXCollections.observableArrayList(customerService.findAll());
    }
    
    @FXML
    public void btnAddAction() {
    	Account newAccount = new Account();
    	newAccount.setAccountName(txtAccountName.getText());
    	newAccount.setRole(txtRole.getValue());
    	
    	Customer newCustomer = new Customer();
        newCustomer.setCustomerName(txtName.getText());
        newCustomer.setMobile(txtMobile.getText());
        newCustomer.setEmail(txtEmail.getText());
        newCustomer.setBirthday(txtBirthday.getValue());
        newCustomer.setIdentityCard(txtIdentityCard.getText());
        newCustomer.setLicenceNumber(txtLicenceNumber.getText());
        newCustomer.setLicenceDate(txtLicenceDate.getValue());
        newCustomer.setPassword(txtPassword.getText());
        newCustomer.setAccount(newAccount);
        
        customerService.save(newCustomer); // Save to the database
        tableModel.add(newCustomer); 
    }
    
    @FXML
    public void btnUpdateAction() {
    	Customer selectedCustomer = tbData.getSelectionModel().getSelectedItem();
    	
        if (selectedCustomer != null) {
            selectedCustomer.setCustomerName(txtName.getText());
            selectedCustomer.setMobile(txtMobile.getText());
            selectedCustomer.setEmail(txtEmail.getText());
            selectedCustomer.setBirthday(txtBirthday.getValue());
            selectedCustomer.setIdentityCard(txtIdentityCard.getText());
            selectedCustomer.setLicenceNumber(txtLicenceNumber.getText());
            selectedCustomer.setLicenceDate(txtLicenceDate.getValue());
            Account account = accountService.findById(selectedCustomer.getAccount().getAccountId());
            account.setAccountName(txtAccountName.getText());
            account.setRole(txtRole.getValue());
            selectedCustomer.setAccount(account);
            customerService.update(selectedCustomer); // Update in database
            tbData.refresh(); // Refresh TableView to show updated data
        }
    }
    
    @FXML
    public void btnDeleteAction() {
    	 Customer selectedCustomer = tbData.getSelectionModel().getSelectedItem();
    	    if (selectedCustomer != null) {
    	        customerService.delete(selectedCustomer.getCustomerId()); // Remove from database
    	        tableModel.remove(selectedCustomer);      // Remove from TableView
    	    }
    }
    
    @FXML
    public void btnCancelAction() {
    	 txtId.clear();
         txtName.clear();
         txtMobile.clear();
         txtEmail.clear();
         txtAccountName.clear();
         txtIdentityCard.clear();
         txtLicenceNumber.clear();
         txtPassword.clear();
         txtBirthday.setValue(null);
         txtLicenceDate.setValue(null);
         txtRole.setValue(null);
    }
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		id.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        identityCard.setCellValueFactory(new PropertyValueFactory<>("identityCard"));
        licenceNumber.setCellValueFactory(new PropertyValueFactory<>("licenceNumber"));
        licenceDate.setCellValueFactory(new PropertyValueFactory<>("licenceDate"));
        
        // Get accountName and role from the Account associated with each Customer
        accountName.setCellValueFactory(cellData -> cellData.getValue().getAccount() != null
                ? new SimpleStringProperty(cellData.getValue().getAccount().getAccountName())
                : new SimpleStringProperty("N/A"));
        
        role.setCellValueFactory(cellData -> cellData.getValue().getAccount() != null
                ? new SimpleStringProperty(cellData.getValue().getAccount().getRole())
                : new SimpleStringProperty("N/A"));

        // Set data to TableView
        tbData.setItems(tableModel);
        ObservableList<String> roles = FXCollections.observableArrayList();
        accountService.findAll().forEach(account -> {
            if (!roles.contains(account.getRole())) {
                roles.add(account.getRole());
            }
        });

        // Populate the ComboBox with roles
        txtRole.setItems(roles);
        tbData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object index) {
				if (tbData.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel selectionModel = tbData.getSelectionModel();
					ObservableList selectedCells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
					Object id = tablePosition.getTableColumn().getCellData(index);
					try {
						Customer movies = customerService.findById(Long.valueOf(id.toString())); 
						show(movies);
					} catch (Exception ex) { 
						showAlert("Infomation Board!", "Please choose the First Cell !");
					}
				}
				
			}
	});
	}
	public void showAlert(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	private void show(Customer customer) {
		 	txtId.setText(String.valueOf(customer.getCustomerId()));
	        txtName.setText(customer.getCustomerName());
	        txtMobile.setText(customer.getMobile());
	        txtEmail.setText(customer.getEmail());
	        txtBirthday.setValue(customer.getBirthday());
	        txtIdentityCard.setText(customer.getIdentityCard());
	        txtLicenceNumber.setText(customer.getLicenceNumber());
	        txtLicenceDate.setValue(customer.getLicenceDate());
	        txtPassword.setText(customer.getPassword());

	        if (customer.getAccount() != null) {
	            txtAccountName.setText(customer.getAccount().getAccountName());
	            txtRole.setValue(customer.getAccount().getRole());
	        } else {
	            txtAccountName.clear();
	            txtRole.setValue(null);
	        }
	}

}
