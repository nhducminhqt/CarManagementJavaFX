package controller;


import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import pojo.Account;
import pojo.Car;
import pojo.CarRental;
import pojo.Customer;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionController implements Initializable {
    @FXML private DatePicker txtpickupDate;
    @FXML private DatePicker txtreturnDate;
    @FXML private Label label;
    @FXML private TableColumn<CarRental, String> carModelYear;
    @FXML private TableColumn<CarRental, String> color;
    @FXML private TableColumn<CarRental, Float> rentPrice;
    @FXML private TableColumn<CarRental, LocalDate> pickupDate;
    @FXML private TableColumn<CarRental, LocalDate> returnDate;
    @FXML private TableColumn<CarRental, String> status;
    
    @FXML private TableView<CarRental> tbData;


    @FXML private ComboBox<Car> txtCar;

    @FXML private TableColumn<CarRental, String> carId;
    @FXML private TableColumn<CarRental, String> carName;
    private ICarService carService;
    private ICustomerService customerService;
    
    
    private ICarRentalService carRentalService;
    private ObservableList<CarRental> tableModel;
    private Long cusId;
    
    public TransactionController() {
        // Initialize services
        String file = "hibernate.cfg.xml";
        carRentalService = new CarRentalService(file);
        carService = new CarService(file);
        customerService = new CustomerService(file);
        tableModel = FXCollections.observableArrayList();
    }
    
    @FXML
   	private void btnAddAction()  {
   		CarRental carRental = new CarRental();
   		Car car = txtCar.getValue();
   		Customer customer = customerService.findById(cusId);
   		long daysBetween = ChronoUnit.DAYS.between(txtpickupDate.getValue(), txtreturnDate.getValue());
   		carRental.setCar(car);
   		carRental.setCustomer(customer);
   		carRental.setPickupDate(txtpickupDate.getValue());
   		carRental.setRentPrice(car.getRentPrice()*daysBetween);
   		carRental.setReturnDate(txtreturnDate.getValue());
   		carRental.setStatus("processing");
   		boolean check = carRentalService.countOverlappingBookings(carRental.getCar().getCarId(), carRental.getPickupDate(), carRental.getReturnDate());
   		if(check) {
   			if(carRental.getCar().getStatus().equals("available")) {
   			carRentalService.save(carRental);
   			tableModel.add(carRental); 
   			label.setText("Add successfully");}
   			else label.setText("Unavailable car");
   		}
   		else label.setText("This car has been rented");
   	}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize table columns
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
		
	
		tbData.setItems(tableModel);
		List<Car> carList = carService.findAll();
		List<Car> carListAvailabe = new ArrayList<Car>();
		for(Car car : carList) {
			if(car.getStatus().equals("available"))
				carListAvailabe.add(car);
		}
        ObservableList<Car> cars = FXCollections.observableArrayList(carListAvailabe);
        txtCar.setItems(cars);
    }

    public void setCusId(Long cusId) {
        this.cusId = cusId;
        View(); // Fetch and display data after cusId is set
    }

    @FXML
    public void View() {
        // Ensure cusId is set
        if (cusId != null) {
            List<CarRental> rentals = carRentalService.findByCustomerId(cusId);
            if (rentals != null && !rentals.isEmpty()) {
                System.out.println("Data loaded for customer ID: " + cusId + ", Total records: " + rentals.size());
                tableModel.setAll(rentals); // Set new data
                tbData.refresh(); // Refresh table view
            } else {
                System.out.println("No data found for customer ID: " + cusId);
                tableModel.clear(); // Clear if no data found
            }
        } else {
            System.out.println("Customer ID is null. Cannot load data.");
        }
    }
}
