package View;

import java.awt.event.MouseEvent;

import com.jfoenix.controls.JFXButton;

import Launcher.DisplayController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddReportController {
	
	private DisplayController displayCtrl;
	
	@FXML
	public void initialize() {
		/**
		 *  get DisplayController
		 */
		displayCtrl = DisplayController.getInstance();
	}
	
	@FXML
	private JFXButton btn_ar_cancel,btn_ar_ok;
	
	@FXML
	private void ARAnnulerButtonClicked() {
		displayCtrl.closeAddReportStage();
	}
	
	
	 @FXML
	 private void handleButtonAction(ActionEvent event) {
		 if(event.getSource() == btn_ar_ok) {
			 //TODO
			 displayCtrl.closeAddReportStage();
		 }
		 if(event.getSource() == btn_ar_cancel) {
			 displayCtrl.closeAddReportStage();
		 }
		 
	 }
	
	 @FXML
	 private void handleEvent(MouseEvent event) {    
	   
	 }
		
	
}
