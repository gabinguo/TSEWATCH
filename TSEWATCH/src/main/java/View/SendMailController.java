package View;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

import Launcher.App;
import Launcher.DisplayController;
import Model.Client;
import Model.ListDiffusion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import util.Const;
import util.Mailjet;

public class SendMailController {
	
	private DisplayController displayCtrl;
	
	@FXML
	private JFXComboBox comboBox_rapport;
	@FXML 
	private JFXComboBox<String> comboBox_listD = new JFXComboBox<String>();
	


	@FXML
	public void initialize() {
		displayCtrl = DisplayController.getInstance();
		comboBox_listD.setItems(App.getDF());
		comboBox_rapport.setItems(App.gerRP());
	}
	@FXML
	private JFXButton btn_sm_cancel,btn_sm_ok,btn_preview;
	
	
	
	 @FXML
	private void handleButtonAction(ActionEvent event) {
		if(event.getSource() == btn_sm_ok) {
			sendEmail_Action();
			displayCtrl.closeSendMailStage();
		}
	    if(event.getSource() == btn_sm_cancel) {
			 displayCtrl.closeSendMailStage();
		}
	    if(event.getSource() == btn_preview) {
	    	preview_report_Action();
	    }
		 
	}
	
	 public void sendEmail_Action() {
		 
		 
		 String listDName = comboBox_listD.getValue().toString();
		 String reportName = comboBox_rapport.getValue().toString();
		 File input = new File(Const.FOLDER_RAPPORT + reportName + ".html");
		 Document doc = null;
		 try {
		 	doc = Jsoup.parse(input, "UTF-8");
	 	 } catch (IOException e) {
		 	 e.printStackTrace();
		 }
		 if(listDName.length()!=0  && reportName.length() !=0) {
			 
			 ListDiffusion listD;
			 try {
				 listD = displayCtrl.getFileManager().readDiffusionList(listDName);
				 for(Client client : listD.getListClient())
					 try {
						 Mailjet.SendMail(App.getConfigStrArr()[2], client.getEmail(), doc.html());
					 } catch (MailjetException e) {
						 e.printStackTrace();
					 } catch (MailjetSocketTimeoutException e) {
						 e.printStackTrace();
					 }
			 } catch (IOException e) {
				e.printStackTrace();
			 }
			 
		 }
		
	 }
	 
	 public void preview_report_Action() {
		 if(comboBox_rapport.getValue().toString().length() != 0) {
	    		String pathReport = Const.FOLDER_RAPPORT + comboBox_rapport.getValue() + ".html";
	    		try {
					java.awt.Desktop.getDesktop().browse(new File(pathReport).toURI());
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	 }
	 
	 @FXML
	 private void handleEvent(MouseEvent event) {    
	   
	 }
	 
}
