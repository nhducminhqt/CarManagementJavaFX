package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import pojo.CarRental;
import service.CarRentalService;
import service.CarService;
import service.CustomerService;
import service.ICarRentalService;
import service.ICarService;
import service.ICustomerService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReportController implements Initializable{
    @FXML private TableColumn<CarRental, String> carId;
    @FXML private TableColumn<CarRental, String> carName;
    @FXML private TableColumn<CarRental, String> carModelYear;
    @FXML private TableColumn<CarRental, String> color;
    @FXML private TableColumn<CarRental, Float> rentPrice;
    @FXML private TableColumn<CarRental, LocalDate> pickupDate;
    @FXML private TableColumn<CarRental, LocalDate> returnDate;
    @FXML private TableColumn<CarRental, String> status;
    @FXML private TableColumn<CarRental, String> CustomerId;
	@FXML private TableView<CarRental> tbData;
	@FXML private TableColumn<CarRental, Long> carRentalId;


    @FXML private DatePicker txtpickupDate;
    @FXML private DatePicker txtreturnDate;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carRentalId.setCellValueFactory(new PropertyValueFactory<>("id"));;
		pickupDate.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
		returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
		rentPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		carId.setCellValueFactory(cellData -> cellData.getValue().getCar() != null
	                ? new SimpleStringProperty(String.valueOf(cellData.getValue().getCar().getCarId()))
	                : new SimpleStringProperty("N/A"));
		carName.setCellValueFactory(cellData -> cellData.getValue().getCar() != null
                ? new SimpleStringProperty(cellData.getValue().getCar().getCarName())
                : new SimpleStringProperty("N/A"));
		carModelYear.setCellValueFactory(cellData -> cellData.getValue().getCar() != null
                ? new SimpleStringProperty(String.valueOf(cellData.getValue().getCar().getCarModelYear()))
                : new SimpleStringProperty("N/A"));
		color.setCellValueFactory(cellData -> cellData.getValue().getCar() != null
                ? new SimpleStringProperty(cellData.getValue().getCar().getColor())
                : new SimpleStringProperty("N/A"));
		
		CustomerId.setCellValueFactory(cellData -> cellData.getValue().getCustomer() != null
                ? new SimpleStringProperty(String.valueOf(cellData.getValue().getCustomer().getCustomerId()))
                : new SimpleStringProperty("N/A"));
		tbData.setItems(tableModel);
	}
	private ObservableList<CarRental> tableModel;
    private ICarRentalService carRentalService;
    private ICarService carService;
    private ICustomerService customerService;
    
    public ReportController() {
    	String file = "hibernate.cfg.xml";
    	carRentalService = new CarRentalService(file);
    	carService = new CarService(file);
    	customerService = new CustomerService(file);
    	tableModel = FXCollections.observableArrayList(carRentalService.findAll());
    }
    @FXML
	private void btnAddAction()  {
//    	if(!carRentalService.report(txtpickupDate.getValue(), txtreturnDate.getValue()).isEmpty()) {
    	tableModel = FXCollections.observableArrayList(carRentalService.report(txtpickupDate.getValue(), txtreturnDate.getValue()));
    	tbData.setItems(tableModel);}
//	}
}
