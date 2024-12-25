package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import dao.CarProducerDAO;
import dao.ICarProducerDAO;
import pojo.Car;
import pojo.CarProducer;
import pojo.CarRental;
import pojo.Customer;
import service.AccountService;
import service.CarRentalService;
import service.CarService;
import service.CustomerService;
import service.ICarRentalService;
import service.ICarService;
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

public class CarController implements Initializable{
	 
    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtModelYear;
    @FXML private TextField txtRentPrice;
    @FXML private TextField txtStatus;
    @FXML private TextField txtColor;
    @FXML private TextField txtCapacity;
    @FXML private TextField txtDescription;
    @FXML private DatePicker txtImportDate;
	@FXML private TableView<Car> tbData;
    @FXML private TableColumn<Car, Long> id;
    @FXML private TableColumn<Car, String> name;
    @FXML private TableColumn<Car, Integer> modelYear;
    @FXML private TableColumn<Car, String> color;
    @FXML private TableColumn<Car, Integer> capacity;

   
    @FXML private ComboBox<CarProducer> txtProducer;
    
    private ObservableList<Car> tableModel;
    private ICarService carService;
    private ICarRentalService carRentalService;
    private ICarProducerDAO carProductService;
    
    public CarController() {
    	String file = "hibernate.cfg.xml";
    	carService = new CarService(file);
    	carProductService = new CarProducerDAO(file);
    	carRentalService = new CarRentalService(file);
    	tableModel = FXCollections.observableArrayList(carService.findAll());
    }
    @FXML private TableColumn<Car, String> description;
    @FXML private TableColumn<Car, LocalDate> importDate;
    @FXML private TableColumn<Car, Float> rentPrice;
    @FXML private TableColumn<Car, String> status;
    @FXML private TableColumn<Car, CarProducer> producer;
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 id.setCellValueFactory(new PropertyValueFactory<>("carId"));
	        name.setCellValueFactory(new PropertyValueFactory<>("carName"));
	        modelYear.setCellValueFactory(new PropertyValueFactory<>("carModelYear"));
	        color.setCellValueFactory(new PropertyValueFactory<>("color"));
	        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
	        description.setCellValueFactory(new PropertyValueFactory<>("description"));
	        importDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
	        rentPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
	        status.setCellValueFactory(new PropertyValueFactory<>("status"));
	        producer.setCellValueFactory(new PropertyValueFactory<>("carProducer"));
	        tbData.setItems(tableModel);
	        ObservableList<CarProducer> producers = FXCollections.observableArrayList(carProductService.getAll());
	        txtProducer.setItems(producers);
	        tbData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
				@Override
				public void changed(ObservableValue observableValue, Object oldValue, Object index) {
					if (tbData.getSelectionModel().getSelectedItem() != null) {
						TableViewSelectionModel selectionModel = tbData.getSelectionModel();
						ObservableList selectedCells = selectionModel.getSelectedCells();
						TablePosition tablePosition = (TablePosition) selectedCells.get(0);
						Object id = tablePosition.getTableColumn().getCellData(index);
						try {
							Car movies = carService.findById(Long.valueOf(id.toString())); 
							showCarDetails(movies);
						} catch (Exception ex) { 
							showAlert("Infomation Board!", "Please choose the First Cell !");
						}
					}
					
				}
		});
	}
	
	 private void showCarDetails(Car car) {
	        txtId.setText(String.valueOf(car.getCarId()));
	        txtName.setText(car.getCarName());
	        txtModelYear.setText(String.valueOf(car.getCarModelYear()));
	        txtColor.setText(car.getColor());
	        txtCapacity.setText(String.valueOf(car.getCapacity()));
	        txtDescription.setText(car.getDescription());
	        txtImportDate.setValue(car.getImportDate());
	        txtRentPrice.setText(String.valueOf(car.getRentPrice()));
	        txtStatus.setText(car.getStatus());
	        txtProducer.setValue(car.getCarProducer());
	    }
	 @FXML
	    public void btnAddAction() {
	        Car newCar = new Car();
	        newCar.setCarName(txtName.getText());
	        newCar.setCarModelYear(Integer.parseInt(txtModelYear.getText()));
	        newCar.setColor(txtColor.getText());
	        newCar.setCapacity(Integer.parseInt(txtCapacity.getText()));
	        newCar.setDescription(txtDescription.getText());
	        newCar.setImportDate(txtImportDate.getValue());
	        newCar.setRentPrice(Float.parseFloat(txtRentPrice.getText()));
	        newCar.setStatus("available");
	        newCar.setCarProducer(txtProducer.getValue());

	        carService.save(newCar); // Save to the database
	        tableModel.add(newCar); // Update TableView
	    }

	    @FXML
	    public void btnUpdateAction() {
	        Car selectedCar = tbData.getSelectionModel().getSelectedItem();

	        if (selectedCar != null) {
	            selectedCar.setCarName(txtName.getText());
	            selectedCar.setCarModelYear(Integer.parseInt(txtModelYear.getText()));
	            selectedCar.setColor(txtColor.getText());
	            selectedCar.setCapacity(Integer.parseInt(txtCapacity.getText()));
	            selectedCar.setDescription(txtDescription.getText());
	            selectedCar.setImportDate(txtImportDate.getValue());
	            selectedCar.setRentPrice(Float.parseFloat(txtRentPrice.getText()));
	            selectedCar.setStatus(txtStatus.getText());
	            selectedCar.setCarProducer(txtProducer.getValue());

	            carService.update(selectedCar); // Update in database
	            tbData.refresh(); // Refresh TableView to show updated data
	        } else {
	            showAlert("Selection Error", "Please select a car to update.");
	        }
	    }

		@FXML
	    public void btnDeleteAction() {
	        Car selectedCar = tbData.getSelectionModel().getSelectedItem();
	        Long carId = selectedCar.getCarId();
	        List<CarRental> list = carRentalService.findByCarId(carId);
	        if ( list.isEmpty() ) {
	            carService.delete(carId); // Remove from database
	            tableModel.remove(selectedCar); // Remove from TableView
	        } else {
	        	selectedCar.setStatus("unavailable");
	        	carService.update(selectedCar);
	        	tbData.refresh();
	        }  
	    }

	    @FXML
	    public void btnCancelAction() {
	        // Clear all fields
	        txtId.clear();
	        txtName.clear();
	        txtModelYear.clear();
	        txtColor.clear();
	        txtCapacity.clear();
	        txtDescription.clear();
	        txtImportDate.setValue(null);
	        txtRentPrice.clear();
	        txtStatus.clear();
	        txtProducer.setValue(null);
	    }

	    public void showAlert(String header, String content) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setHeaderText(header);
	        alert.setContentText(content);
	        alert.showAndWait();
	    }
	
	

}
