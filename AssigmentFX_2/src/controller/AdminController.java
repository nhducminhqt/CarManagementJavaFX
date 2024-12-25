package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

public class AdminController implements Initializable,Navigate{
	@FXML
	private StackPane contentArea;
	
	
	@FXML
	private void btnReportAction() throws IOException {
		navigateReport(contentArea);
	}
	
	@FXML
	private void btnLogoutAction() {
		Platform.exit();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	private void btnUserManagementAction() throws IOException {
		navigateUserManagement(contentArea);
	}
	
	@FXML
	private void btnCarManagementAction() throws IOException {
		navigateCarManagement(contentArea);
	}
	
	@FXML
	private void btnRentalManagementAction() throws IOException {
		navigateRentalManagement(contentArea);
	}

}
