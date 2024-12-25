package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public interface Navigate {
	default void navigateCarManagement(StackPane contentArea) throws IOException{
		setContentAdmin("Car.fxml", contentArea);
	}
	default void navigateRentalManagement(StackPane contentArea) throws IOException{
		setContentAdmin("Rental.fxml", contentArea);
	}
	default void navigateReport(StackPane contentArea) throws IOException{
		setContentAdmin("Report.fxml", contentArea);
	}
	
	
	default void navigateProfile(StackPane contentArea,Long CusId) throws IOException{
		setContent("Profile.fxml", contentArea, CusId);
	}
	
	default void navigateTransactionHistory(StackPane contentArea,Long CusId) throws IOException{
		setContent("Transaction.fxml", contentArea, CusId);
	}
	default void navigateUserManagement(StackPane contentArea) throws IOException{
		setContentAdmin("User.fxml", contentArea);
	}

	
	
	default void setContent(String page,StackPane contentArea,Long CusId) throws IOException{
		try {
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/" + page));
			  Node newContent = loader.load();
			 
			  if(page.equals("Profile.fxml")) {
				  ProfileController profile = loader.getController();
				  profile.setCusID(CusId);
			  } else if(page.equals("Transaction.fxml") ){
				  TransactionController transactionController = loader.getController();
				  transactionController.setCusId(CusId);
			  } 
	            
	            // Clear old content
	            contentArea.getChildren().clear();
	            
	            // Add new content
	            contentArea.getChildren().add(newContent);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	default void setContentAdmin(String page,StackPane contentArea) throws IOException{
		try {
			  FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/" + page));
			  Node newContent = loader.load();
			 
			  if(page.equals("User.fxml") ){
				  UserController adminController = loader.getController();
//			  Node newContent = loader.load();
			  } else if(page.equals("Car.fxml") ){
				  CarController carController = loader.getController();
				
			  } else if(page.equals("Rental.fxml") ){
				  RentalController rentalController = loader.getController();
				  
			  } else if(page.equals("Report.fxml") ){
				  ReportController reportController = loader.getController();
				  
			  }
	            
	            // Clear old content
	            contentArea.getChildren().clear();
	            
	            // Add new content
	            contentArea.getChildren().add(newContent);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
