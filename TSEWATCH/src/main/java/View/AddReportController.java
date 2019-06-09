package View;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Launcher.DisplayController;
import Model.Avis;
import Model.AxeDeVeille;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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
		  generateAndShowReportHtmlPage();
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
	
	public void generateAndShowReportHtmlPage(){
		String nameOfFolder = comboBox_AR.getValue().toString();
		String nameOfReport = nameReport.getText();
		try {
			if(nameOfReport!="") {
				AxeDeVeille axe = displayCtrl.getFileManager().readAxe(nameOfFolder);
				ArrayList<Avis> avis = axe.getListAvis();
				HTMLGenerator htmlGenerator = new HTMLGenerator(avis);
				htmlGenerator.generateReport(nameOfReport);
				String pathReport = Const.FOLDER_RAPPORT + nameOfReport + ".html" ;
				Stage stage = new Stage();
				stage.setTitle("Report page");
				WebView browser = new WebView();
				WebEngine engine = browser.getEngine();
				engine.loadContent(htmlGenerator.getHtml().toBigHtmlString());
				
				engine.setUserStyleSheetLocation("C:/Users/Alienware/Desktop/assets/css/style.css");
				Scene scene = new Scene(browser,800,600, Color.web("#666970"));
				stage.setScene(scene);
				stage.show();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleEvent(MouseEvent event) {    
	   
	}
		
	
}
