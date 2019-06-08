/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Launcher.DisplayController;
import Model.AxeDeVeille;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import util.Const;

/**
 *
 * @author proxc
 */
public class HomeController {
	
	/**
	 *  Variable Home Page
	 */
	private static ArrayList<String> sites = new ArrayList<String>();
	private DisplayController displayCtrl;
	
	
    
    @FXML
    private JFXButton btn_add,btn_back,btn_options,btn_axe,btn_recherche,btn_diffusion,btn_rapport
    						,btn_rapport_nouveau,btn_add_client, btn_envoyer, btn_add_axe,
    						btn_modify_axe, btn_annuler_axe,btn_save_axe,btn_recherche_ok;
    
    @SuppressWarnings("rawtypes")
	@FXML
    private JFXComboBox siteList;
    
     @FXML
    private AnchorPane add_pane, options_pane,diffusion_pane,rapport_pane,recherche_pane,axe_pane;
    
     @FXML
    private Pane add_modify_pane;
     
    @FXML
    private JFXTextField nameVeilleTextField;
    
    @FXML
    private TextField keywordsTextField;
    
    @FXML
    private TableView veilleTableView;
    
    
    @FXML
    private TableColumn<AxeDeVeille, String> colVeille;
    
    
    
    
    @SuppressWarnings("unchecked")
	@FXML
	public void initialize() {
    	/**
		 *  get DisplayController for displaying or some new Tab
		 */
		displayCtrl = DisplayController.getInstance();
		
		add_modify_pane.setVisible(false);
    	recherche_pane.toFront();
    	
    	for(String name : Const.namesOfSites) {
			sites.add(name);
		}
    	sites.add("All");
    	ObservableList<String> list = FXCollections.observableArrayList(sites);
    	siteList.setItems(list);
    	
    	
    	colVeille.setCellValueFactory(new PropertyValueFactory<>("name"));
    	veilleTableView.setItems(displayCtrl.getAxes());
    	
    	
		
		/**
		 * check Veille Table clickEvent
		 */
    	checkVeillePageClickEvent() ;
	}

    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void checkVeillePageClickEvent() {
		veilleTableView.setRowFactory(tv -> {
			TableRow row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if(!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
				}
			});
			return row;
		});
	}
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btn_add)
        {
	        add_pane.setVisible(true);
	        add_pane.toFront();
        }
       else
        if(event.getSource()==btn_back)
        {
             add_pane.setVisible(false);     
        }

        if(event.getSource() == btn_recherche_ok) {
        	rapport_pane.toFront();
        }
        
        if(event.getSource()== btn_axe)
        {
             
             axe_pane.toFront();
        }
        if(event.getSource()==btn_recherche)
        {
            
        	 recherche_pane.toFront();
        }
        if(event.getSource()==btn_diffusion)
        {
        	 diffusion_pane.toFront();
        }
        if(event.getSource()==btn_rapport)
        {
             rapport_pane.toFront();
        }
        
        if(event.getSource() == btn_rapport_nouveau) {
        	 displayCtrl.showAddReport();
        }
        
        if(event.getSource() == btn_add_client) {
        	 displayCtrl.showAddClient();
        }
        
        if(event.getSource() == btn_envoyer) {
        	 displayCtrl.showSendMail();
        }
        
        if(event.getSource() == btn_add_axe) {
        	add_modify_pane.setVisible(true);
        }
        
        if(event.getSource() == btn_modify_axe) {
        	
        }
        
        if(event.getSource() == btn_annuler_axe) {
        	add_modify_pane.setVisible(false);
        	nameVeilleTextField.setText("");
        	keywordsTextField.setText("");
        }
        
        if(event.getSource() == btn_save_axe) {
        	ArrayList<String> keywords = new ArrayList<String>();
        	String[] allKeywords = keywordsTextField.getText().trim().split(",");
        	for(int i=0;i<allKeywords.length;i++) {
        		keywords.add(allKeywords[i].trim());
        	
        	}
         	displayCtrl.getFileManager().saveAxe(
        			new AxeDeVeille(nameVeilleTextField.getText().trim(), keywords));	
         	
         	veilleTableView.getItems().add(new AxeDeVeille(nameVeilleTextField.getText(),null));
         	
         	
         	add_modify_pane.setVisible(false);
        	nameVeilleTextField.setText("");
        	keywordsTextField.setText("");
        }
    }
    
    
   
    
    
    @FXML
    private void handleEvent(MouseEvent event) {    
    }
	
    
    
    
    
}
