package View;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

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
	private JFXComboBox comboBox_AR;
	
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
	private void comboBoxClickOn() {
		ArrayList<Object> listObj = new ArrayList<Object>();
    	for(int i =0;i<displayCtrl.getListVeille().size();i++) {
    		listObj.add(displayCtrl.getListVeille().get(i).getName());	
    	} 
    	comboBox_AR.getItems().clear();
    	comboBox_AR.getItems().addAll(listObj);
	}
	
	@FXML
	private void handleEvent(MouseEvent event) {    
	   
	}
		
	
}
