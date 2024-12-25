package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import pojo.Car;
import pojo.CarProducer;
import pojo.CarRental;
import pojo.Customer;
import service.CarRentalService;
import service.CarService;
import service.CustomerService;
import service.ICarRentalService;
import service.ICarService;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;

public class RentalController implements Initializable{
	
    @FXML private ComboBox<Customer> txtCustomer;
    @FXML private ComboBox<Car> txtCar;
    @FXML private TextField txtPrice;
    @FXML private DatePicker txtpickupDate;
    @FXML private DatePicker txtreturnDate;
    
	@FXML private TableView<CarRental> tbData;
    @FXML private TableColumn<CarRental, Float> rentPrice;
    @FXML private TableColumn<CarRental, LocalDate> pickupDate;
    @FXML private TableColumn<CarRental, LocalDate> returnDate;
    @FXML private TableColumn<CarRental, String> status;
    @FXML private TableColumn<CarRental, String> CustomerId;
	@FXML private TableColumn<CarRental, Long> carRentalId;
    @FXML private TableColumn<CarRental, String> carId;
    @FXML private TableColumn<CarRental, String> carName;
    @FXML private TableColumn<CarRental, String> carModelYear;
    @FXML private TableColumn<CarRental, String> color;

    @FXML private Label label;
    private ICarRentalService carRentalService;
    private ICarService carService;
    private ICustomerService customerService;
    
    private ObservableList<CarRental> tableModel;

    public RentalController() {
    	String file = "hibernate.cfg.xml";
    	carRentalService = new CarRentalService(file);
    	carService = new CarService(file);
    	customerService = new CustomerService(file);
    	tableModel = FXCollections.observableArrayList(carRentalService.findAll());
    }
    
    @FXML
	private void btnAddAction()  {
		CarRental carRental = new CarRental();
		carRental.setCar(txtCar.getValue());
		carRental.setCustomer(txtCustomer.getValue());
		carRental.setPickupDate(txtpickupDate.getValue());
		carRental.setRentPrice(Float.parseFloat(txtPrice.getText()));
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
    @FXML
	private void btnCompleteAction()  {
    	CarRental selectedCarRental = tbData.getSelectionModel().getSelectedItem();
    	if(selectedCarRental!=null) {
    		selectedCarRental.setStatus("complete");
    		carRentalService.update(selectedCarRental);
    		label.setText("Update successfully");
    		tbData.refresh();
    	}
    }
    @FXML
	private void btnCancelAction()  {
    	CarRental selectedCarRental = tbData.getSelectionModel().getSelectedItem();
    	if(selectedCarRental!=null) {
    		selectedCarRental.setStatus("cancel");
    		carRentalService.update(selectedCarRental);
    		label.setText("Update successfully");
    		tbData.refresh();
    	}
    }
    
	@SuppressWarnings("unchecked")
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
		List<Car> carList = carService.findAll();
		List<Car> carListAvailabe = new ArrayList<Car>();
		for(Car car : carList) {
			if(car.getStatus().equals("available"))
				carListAvailabe.add(car);
		}
        ObservableList<Car> cars = FXCollections.observableArrayList(carListAvailabe);
        txtCar.setItems(cars);
        ObservableList<Customer> customers = FXCollections.observableArrayList(customerService.findAll());
        txtCustomer.setItems(customers);
        tbData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object index) {
				if (tbData.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel selectionModel = tbData.getSelectionModel();
					ObservableList selectedCells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
					Object id = tablePosition.getTableColumn().getCellData(index);
					try {
						CarRental movies = carRentalService.findById(Long.valueOf(id.toString())); 
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
	private void show(CarRental carRental) {
	 	txtpickupDate.setValue(carRental.getPickupDate());
	 	txtreturnDate.setValue(carRental.getReturnDate());
	 	txtPrice.setText(String.valueOf(carRental.getRentPrice()));
	 	txtCar.setValue(carRental.getCar());
	 	txtCustomer.setValue(carRental.getCustomer());

}
	
}
