package View;

import java.awt.event.MouseEvent;

import com.jfoenix.controls.JFXButton;

import Launcher.DisplayController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddClientController {
	
	
	
	
	@FXML
	private Button ACAjouterButton;
	
	@FXML
	private TextField clientText;
	
	@FXML 
	private TextField emailText;
	
	@FXML
	private DisplayController displayCtrl;
	
	
	@FXML
	public void initialize() {
		displayCtrl = DisplayController.getInstance();
	}
	
	@FXML
	private JFXButton btn_ac_cancel,btn_ac_ok;
	
	 @FXML
	 private void handleButtonAction(ActionEvent event) {
		 if(event.getSource() == btn_ac_ok) {
			 //TODO
			 displayCtrl.closeAddClientStage();
		 }
		 if(event.getSource() == btn_ac_cancel) {
			 displayCtrl.closeAddClientStage();
		 }
		 
	 }
	
	 @FXML
	 private void handleEvent(MouseEvent event) {    
	   
	 }
	
}
