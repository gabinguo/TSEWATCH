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
	private AxeDeVeille veilleCreating;
	private AxeDeVeille veilleSelecting;
	private ArrayList<AxeDeVeille> veilleList = new ArrayList<AxeDeVeille>();
	private DisplayController displayCtrl;
	private boolean hideAddModifyFlag = false;
	private boolean modifyModeFlag = false;
	
    
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
    private Label label_warning;
    
    @FXML
    //***
    private TableView veilleTableView;
    
    @FXML
    private TableColumn colVeille;
    
    
    
    
    @FXML
	public void initialize() {
    	recherche_pane.toFront();
    	label_warning.setVisible(false);
    	add_modify_pane.setVisible(hideAddModifyFlag);
    	
    	
    	for(String name : Const.namesOfSites) {
			sites.add(name);
		}
    	sites.add("All");
    	ObservableList<String> list = FXCollections.observableArrayList(sites);
		siteList.setItems(list);
		siteList.setValue(list.get(list.size()-1));
		
		
		
		colVeille.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
    	
    	/**
		 *  get DisplayController for displaying or some new Tab
		 */
		displayCtrl = DisplayController.getInstance();
		
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
					veilleSelecting = (AxeDeVeille) row.getItem();
					
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
        	label_warning.setText("Existe deja");
        	label_warning.setText("false");
        	veilleCreating = new AxeDeVeille();
        	nameVeilleTextField.setText("");
        	keywordsTextField.setText("");
        	if(false == hideAddModifyFlag) {
        		hideAddModifyFlag = !hideAddModifyFlag;
        		add_modify_pane.setVisible(hideAddModifyFlag);
        	}
        }
        
        if(event.getSource() == btn_modify_axe) {
        	if(veilleSelecting == null) {
        		
        	}else{
	        	modifyModeFlag = true;
				hideAddModifyFlag = !hideAddModifyFlag;
				add_modify_pane.setVisible(hideAddModifyFlag);
				
				
				nameVeilleTextField.setText(veilleSelecting.getName());
				String allKeywords = "";
				for(String str : veilleSelecting.getKeywords()) {
					allKeywords = allKeywords + str + ", ";
				}
				allKeywords = allKeywords.substring(0, allKeywords.lastIndexOf(","));
				keywordsTextField.setText(allKeywords);
        	
        	
        	}
        }
        
        if(event.getSource() == btn_annuler_axe) {
        	nameVeilleTextField.setText("");
        	keywordsTextField.setText("");
        	
        	hideAddModifyFlag = !hideAddModifyFlag;
        	add_modify_pane.setVisible(hideAddModifyFlag);
        }
        
        if(event.getSource() == btn_save_axe) {
        	String nameOfVeille = nameVeilleTextField.getText().trim();
        	String[] arrKeywords = keywordsTextField.getText().trim().split(",");
        	
        	ArrayList<String> listKeywords = new ArrayList<String>();
        	for(int i =0 ;i < arrKeywords.length ; i++) {
        		listKeywords.add(arrKeywords[i].trim());
        	}
        	
        	veilleCreating = new AxeDeVeille(nameOfVeille, listKeywords);
        	updateVeilleList();
        	if(!label_warning.isVisible()) {
        		hideAddModifyFlag = !hideAddModifyFlag;
        		add_modify_pane.setVisible(hideAddModifyFlag);
        		modifyModeFlag = false;
        	}
        	
        }
        

 
    }
    public boolean addVeille2List() {
		if(veilleCreating.getName().isEmpty()) 
		{
			label_warning.setText("Rien rempli");
			return false;
		}
		if(veilleList.size()==0) return true;
		for(int i = 0 ; i < veilleList.size();i++)
		{
			if(veilleCreating.getName().equals(veilleList.get(i).getName())) 
			{
				return false;
			}
		}
		return true;
	}
    
    private void updateVeilleList() {
		if(modifyModeFlag) {
			
			String[] arrKeywords = keywordsTextField.getText().trim().split(",");
			ObservableList<String> list = FXCollections.observableArrayList(sites);
			
			ArrayList<String> listKeywords = new ArrayList<String>();
			for(int i =0;i < arrKeywords.length;i++) {
				listKeywords.add(arrKeywords[i].trim());
			}
			veilleSelecting.setName(nameVeilleTextField.getText());
			veilleSelecting.setKeywords(listKeywords);
			
//			 veilleTableView.getColumns().get(0).setVisible(false);
//			 veilleTableView.getColumns().get(0).setVisible(true);
			
		}else {
			if(addVeille2List()) {
				veilleList.add(veilleCreating);
				veilleTableView.getItems().add(veilleCreating);
				label_warning.setVisible(false);
			}
			else 
			{
				label_warning.setVisible(true);
			}
		}
	}
    
    
    @FXML
    private void handleEvent(MouseEvent event) {    
    }
	
    
    
    
    
}
