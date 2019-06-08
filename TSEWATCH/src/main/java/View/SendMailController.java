package View;

import java.awt.event.MouseEvent;

import com.jfoenix.controls.JFXButton;

import Launcher.DisplayController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SendMailController {
	
	private DisplayController displayCtrl;
	
	
	@FXML
	public void initialize() {
		displayCtrl = DisplayController.getInstance();
	}
	
	@FXML
	private JFXButton btn_sm_cancel,btn_sm_ok;
	
	@FXML
	private void ARAnnulerButtonClicked() {
		displayCtrl.closeAddReportStage();
	}
	
	
	 @FXML
	 private void handleButtonAction(ActionEvent event) {
		 if(event.getSource() == btn_sm_ok) {
			 displayCtrl.closeAddReportStage();
		 }
		 if(event.getSource() == btn_sm_cancel) {
			 displayCtrl.closeAddReportStage();
		 }
		 
	 }
	
	 @FXML
	 private void handleEvent(MouseEvent event) {    
	   
	 }
}
