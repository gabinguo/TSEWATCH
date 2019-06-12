package View;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Launcher.App;
import Launcher.DisplayController;
import Model.Avis;
import Model.AxeDeVeille;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import util.Const;
import util.HTMLGenerator;

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
	private JFXTextField nameReport;
	
	@FXML
	private void ARAnnulerButtonClicked() {
		displayCtrl.closeAddReportStage();
	}
	
	
	
	@FXML
    private void handleButtonAction(ActionEvent event) {
	  if(event.getSource() == btn_ar_ok) {
		  
		  if(generateAndShowReportHtmlPage()) {
			  displayCtrl.closeAddReportStage();
		  }
	  }
	  if(event.getSource() == btn_ar_cancel) {
		  displayCtrl.closeAddReportStage();
	  }
	 
 	}
	
	@FXML
	private void comboBoxClickOn() {
		ArrayList<Object> listObj = new ArrayList<Object>();
    	for(int i =0;i<App.getListVeille().size();i++) {
    		listObj.add(App.getListVeille().get(i).getName());	
    	} 
    	comboBox_AR.getItems().clear();
    	comboBox_AR.getItems().addAll(listObj);
	}
	
	public boolean generateAndShowReportHtmlPage(){
		String nameOfFolder = (String) comboBox_AR.getValue();
		String nameOfReport = nameReport.getText().trim();
		try {
			if(nameOfReport.length()!=0 && comboBox_AR.getValue()!=null) {
				AxeDeVeille axe = displayCtrl.getFileManager().readAxe(nameOfFolder);
				ArrayList<Avis> avis = axe.getListAvis();
				HTMLGenerator htmlGenerator = new HTMLGenerator(avis);
				htmlGenerator.generateReport(nameOfReport);
				String pathReport = Const.FOLDER_RAPPORT + nameOfReport + ".html" ;
				
				
				Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Success");
    			alert.setHeaderText("Report added");
    			alert.setContentText("Le rapport a été créé et ajouté à la liste avec succès.");
    			alert.showAndWait();
    			App.getListReport().add(nameOfReport);
    			displayCtrl.getFileManager().saveLine(nameOfReport, Const.FILE_REPORTLIST);
    			return true;
			}else {
				Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("ERROR");
    			alert.setHeaderText("ERROR");
    			alert.setContentText("Vous ne pouvez pas créer un axe de veille vide, merci de spécifier un nom et des mots clés.");
    			alert.showAndWait();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@FXML
	private void handleEvent(MouseEvent event) {    
	   
	}
		
	
}
