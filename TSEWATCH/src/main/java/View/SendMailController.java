package View;

import java.awt.event.MouseEvent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Launcher.DisplayController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SendMailController {
	
	private DisplayController displayCtrl;
	
	@FXML
	private JFXComboBox comboBox_rapport;
	@FXML 
	private JFXComboBox<String> comboBox_listD = new JFXComboBox<String>();
	


	@FXML
	public void initialize() {
		displayCtrl = DisplayController.getInstance();
		comboBox_listD.setItems(displayCtrl.getDF());
		comboBox_rapport.setItems(displayCtrl.gerRP());
	}
	@FXML
	private JFXButton btn_sm_cancel,btn_sm_ok;
	
	
	
	 @FXML
	private void handleButtonAction(ActionEvent event) {
		if(event.getSource() == btn_sm_ok) {
			displayCtrl.closeSendMailStage();
		}
	    if(event.getSource() == btn_sm_cancel) {
			 displayCtrl.closeSendMailStage();
		}
		 
	}
	
	 @FXML
	 private void handleEvent(MouseEvent event) {    
	   
	 }
	 
}
