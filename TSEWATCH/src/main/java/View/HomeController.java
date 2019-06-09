/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.webfirmframework.wffweb.tag.html.attribute.For;

import Launcher.DisplayController;
import Model.Avis;
import Model.AxeDeVeille;
import Model.Client;
import Model.ListDiffusion;
import file.io.FileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
	private boolean modifyModeFlag = false;
	
    
    @FXML
    private JFXButton btn_add,btn_back,btn_options,btn_axe,btn_recherche,btn_diffusion,btn_rapport
    						,btn_rapport_nouveau,btn_add_client, btn_envoyer, btn_add_axe,btn_delete_axe,
    						btn_modify_axe, btn_annuler_axe,btn_save_axe,btn_recherche_ok,btn_delete_diffusion,
    						btn_add_diffusion, btn_modify_listd,btn_not_create,btn_delete_client;
    
    
    @SuppressWarnings("rawtypes")
	@FXML
    private JFXComboBox siteList,page_report_veilleList,list_diffusion;
    

     @FXML
    private AnchorPane add_pane, options_pane,diffusion_pane,rapport_pane,
    			recherche_pane,axe_pane,addLd_pane;
    
     @FXML
    private Pane add_modify_pane;
     
    @FXML
    private JFXTextField nameVeilleTextField,nameLd;
    
    // Did not change!!! still TextArea
    @FXML
    private TextArea keywordsTextField;
    
    @FXML
    private TableView veilleTableView,resultTableView;
    
    @FXML
    private TableView<Client> clientTableView = new TableView<>();
    
    
    @FXML
    private TableColumn<AxeDeVeille, String> colVeille;
    
    @FXML
    private TableColumn<Client,String> colClient,colEmail;
    @FXML
    private TableColumn<Avis,String> colDate,colLien;
    
    
    @SuppressWarnings("unchecked")
	@FXML
	public void initialize() {
    	/**
		 *  get DisplayController for displaying or some new Tab
		 */
		displayCtrl = DisplayController.getInstance();
		clientTableView.setPlaceholder(new Label("vide"));
		veilleTableView.setPlaceholder(new Label("vide"));
		resultTableView.setPlaceholder(new Label("vide"));
		add_modify_pane.setVisible(false);
    	recherche_pane.toFront();
    	keywordsTextField.setWrapText(true);
    	list_diffusion.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) ->
    		updateClientTableView((String) newValue));
    	
    	page_report_veilleList.getSelectionModel().selectedItemProperty().addListener(
    			(v,oldValue,newValue) -> updateAvisTableView((String) newValue));
    	
    	
    	//Search page's table
    	for(String name : Const.namesOfSites) {
			sites.add(name);
		}
    	sites.add("All");
    	ObservableList<String> list = FXCollections.observableArrayList(sites);
    	siteList.setItems(list);
    	siteList.getSelectionModel().select(siteList.getItems().size()-1);
    	
    	// axe de veille pages' table
    	colVeille.setCellValueFactory(new PropertyValueFactory<>("name"));
    	veilleTableView.setItems(displayCtrl.getAxes());
    	list_diffusion.setItems(displayCtrl.getDF());
    	
    	//set col value factory
    	colClient.setCellValueFactory(new PropertyValueFactory<>("name"));
    	colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    	colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    	colLien.setCellValueFactory(new PropertyValueFactory<>("link"));
    	
	}
    
    private void updateClientTableView(String value) {
    	try {
			ArrayList<String> listStr =  (ArrayList<String>) FileUtils.readLines(new File(Const.FOLDER_DIFFUSION + value + ".txt"));
			ObservableList<Client> listClient = FXCollections.observableArrayList();
			for(int i = 0 ; i < listStr.size();i++) {
				String[] arr = listStr.get(i).split("\\|");
				listClient.add(new Client(arr[0],arr[1]));
			}
			clientTableView.setItems(listClient);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    
    private void updateAvisTableView(String value) {
    	String filepath = Const.FOLDER_AXE + value
    					+ "/avis.txt";
    	try {
			ArrayList<String> listStr = (ArrayList<String>) FileUtils.readLines(new File(filepath));
			ObservableList<Avis> listAvis = FXCollections.observableArrayList();
			for(int i =0; i< listStr.size();i++) {
				String[] arr = listStr.get(i).split("\\|");
				listAvis.add(new Avis(arr[2], arr[0], arr[1]));
			}
			resultTableView.setItems(listAvis);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    	
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
        
        if(event.getSource() == btn_delete_client) {
        	
        	ObservableList<Client> allClient , selectedOne;
        	allClient = clientTableView.getItems();
        	selectedOne =  clientTableView.getSelectionModel().getSelectedItems();
        	selectedOne.forEach(allClient::remove);
        	
        	String filepathName = Const.FOLDER_DIFFUSION + list_diffusion.getValue().toString() 
        			 				+ ".txt";
        	FileManager.emptyTXT(filepathName);
        	
        	for(int i=0 ;i < allClient.size();i++) {
        		FileManager.saveLine(allClient.get(i).getStr2File(), filepathName);
        	}
        	
        }
        
        if(event.getSource() == btn_not_create) {
        	diffusion_pane.toFront();
        	nameLd.clear();
        }
        
        if(event.getSource() == btn_annuler_axe) {
        	add_modify_pane.setVisible(false);
        	nameVeilleTextField.clear();
        	keywordsTextField.clear();
        }
        
        if(event.getSource() == btn_delete_diffusion) {
        	String str = nameLd.getText().trim();
        	ObservableList<String> allLists;
        	allLists = list_diffusion.getItems();
        	for(int i = 0 ; i < list_diffusion.getItems().size();i++) {
        		if(list_diffusion.getItems().get(i).equals(str)) {
        			list_diffusion.getItems().remove(i);
        			break;
        		}
        	}	
        	
        	for( int i = 0; i < displayCtrl.getListDiffusion().size();i++) {
        		if(displayCtrl.getListDiffusion().get(i).getName().equals(str)){
        			displayCtrl.getListDiffusion().remove(displayCtrl.getListDiffusion().get(i));
        			break;
        		}
        	}
        	
        	FileUtils.deleteQuietly(new File(Const.FOLDER_DIFFUSION + str + ".txt"));
        	
        	displayCtrl.getFileManager().emptyTXT(Const.FILE_DIFFUSIONLIST);
        	for(int i = 0 ; i < displayCtrl.getListDiffusion().size();i++)
        	{
        		displayCtrl.getFileManager().saveLine(displayCtrl.getListDiffusion().get(i).getName(), Const.FILE_DIFFUSIONLIST);
        	}
        	diffusion_pane.toFront();
        }
        
        if(event.getSource() == btn_add_diffusion) {
        	String name = nameLd.getText().trim();
        	ArrayList<Client> empty = new ArrayList<Client>();
        	boolean flag = true ;
        	if(name.length() == 0) {
        		flag = false;
        		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("Attention");
    			alert.setContentText("Rien rempli.");
    			alert.showAndWait();
        		return;
        	}
        	if(displayCtrl.getListDiffusion() != null)
        	for(int i=0;i<displayCtrl.getListDiffusion().size();i++) {
        		if(displayCtrl.getListDiffusion().get(i).getName().equals(name)) {
        			flag = false;
        		}
        	}
        	if(flag) {
	        	
	        	
	        	ListDiffusion listNew  = new ListDiffusion(name, empty);
	        	
	        	
	         	displayCtrl.getFileManager().creatediffusionListTxT(listNew);
	         	
	         	list_diffusion.getItems().add(listNew.getName());
	         	displayCtrl.getListDiffusion().add(listNew);
	         	
	         	addLd_pane.toBack();
	        	nameLd.clear();
        	}else {
        		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("Attention");
    			alert.setContentText("Deja exist!");
    			alert.showAndWait();
        	}
        }
        
        if(event.getSource() == btn_modify_listd) {
        	
        	addLd_pane.toFront();
        }
        
        if(event.getSource() == btn_delete_axe) {
        	if(veilleTableView.getSelectionModel().getSelectedItems() == null ||
        			veilleTableView.getSelectionModel().getSelectedItems().size()==0) {
        		
        	}else {
        		ObservableList<AxeDeVeille> allAxes , selectedOne;
            	allAxes = veilleTableView.getItems();
            	selectedOne =  veilleTableView.getSelectionModel().getSelectedItems();
            	AxeDeVeille axe2delete = (AxeDeVeille) veilleTableView.getSelectionModel().getSelectedItem();
            	
            	selectedOne.forEach(allAxes::remove);
            	
            	
            	for( int i = 0; i < displayCtrl.getListVeille().size();i++) {
            		if(displayCtrl.getListVeille().get(i).getName().equals(axe2delete.getName())){
            			displayCtrl.getListVeille().remove(displayCtrl.getListVeille().get(i));
            		}
            	}
            	
            	
            	try {
    				FileUtils.deleteDirectory(new File(Const.FOLDER_AXE + axe2delete.getName()));
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
            	
            	displayCtrl.getFileManager().emptyTXT(Const.FILE_AXELIST);
            	for(int i = 0 ; i < displayCtrl.getListVeille().size();i++) {
            		displayCtrl.getFileManager().saveLine(
            				displayCtrl.getListVeille().get(i).getName() +"\n", Const.FILE_AXELIST);
            	}
        	}	
        	
        }
        
        
        
        if(event.getSource() == btn_recherche_ok) {
        	
        	rapport_pane.toFront();
        	
        	ArrayList<Object> listObj = new ArrayList<Object>();
        	for(int i =0;i<displayCtrl.getListVeille().size();i++) {
        		listObj.add(displayCtrl.getListVeille().get(i).getName());	
        	}
        	page_report_veilleList.getItems().clear();
        	page_report_veilleList.getItems().addAll(listObj);
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
             ArrayList<Object> listObj = new ArrayList<Object>();
         	for(int i =0;i<displayCtrl.getListVeille().size();i++) {
         		listObj.add(displayCtrl.getListVeille().get(i).getName());	
         	}
         	page_report_veilleList.getItems().clear();
         	page_report_veilleList.getItems().addAll(listObj);
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
        	modifyModeFlag = false;
        	add_modify_pane.setVisible(true);
        }
        
        if(event.getSource() == btn_modify_axe) {
        	modifyModeFlag = true;
        	add_modify_pane.setVisible(true);
        	
        	AxeDeVeille axe2Modify = (AxeDeVeille) veilleTableView.getSelectionModel().getSelectedItem();
        	nameVeilleTextField.setText(axe2Modify.getName());
        	keywordsTextField.setText(axe2Modify.getStr2File());
        	
        }
        
        if(event.getSource() == btn_save_axe) {
        	if(modifyModeFlag) {
        		if(veilleTableView.getSelectionModel().getSelectedItems() == null ||
            			veilleTableView.getSelectionModel().getSelectedItems().size()==0) {
            		
            	}else {
            		ObservableList<AxeDeVeille> allAxes , selectedOne;
                	allAxes = veilleTableView.getItems();
                	selectedOne =  veilleTableView.getSelectionModel().getSelectedItems();
                	AxeDeVeille axe2delete = (AxeDeVeille) veilleTableView.getSelectionModel().getSelectedItem();
                	
                	selectedOne.forEach(allAxes::remove);
                	
                	
                	for( int i = 0; i < displayCtrl.getListVeille().size();i++) {
                		if(displayCtrl.getListVeille().get(i).getName().equals(axe2delete.getName())){
                			displayCtrl.getListVeille().remove(displayCtrl.getListVeille().get(i));
                		}
                	}
                	
                	
                	try {
        				FileUtils.deleteDirectory(new File(Const.FOLDER_AXE + axe2delete.getName()));
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
                	
                	displayCtrl.getFileManager().emptyTXT(Const.FILE_AXELIST);
                	for(int i = 0 ; i < displayCtrl.getListVeille().size();i++) {
                		displayCtrl.getFileManager().saveLine(
                				displayCtrl.getListVeille().get(i).getName() +"\n", Const.FILE_AXELIST);
                	}
            	}
        	}
        	boolean flag = true ;
        	if(nameVeilleTextField.getText().length()==0 || keywordsTextField.getText().length() == 0) {
        		flag = false;
        		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("Attention");
    			alert.setContentText("Rien rempli / Au moins un mot cle!");
    			alert.showAndWait();
        		return;
        	}
        	if(displayCtrl.getListVeille() != null)
        	for(int i=0;i<displayCtrl.getListVeille().size();i++) {
        		if(displayCtrl.getListVeille().get(i).getName().equals(nameVeilleTextField.getText().trim())) {
        			flag = false;
        		}
        	}
        	if(flag) {
	        	ArrayList<String> keywords = new ArrayList<String>();
	        	String[] allKeywords = keywordsTextField.getText().trim().split(",");
	        	for(int i=0;i<allKeywords.length;i++) {
	        		keywords.add(allKeywords[i].trim());
	        	
	        	}
	        	AxeDeVeille axeNew = new AxeDeVeille(nameVeilleTextField.getText().trim(), keywords);
	         	displayCtrl.getFileManager().saveAxe(axeNew);	
	         	
	         	veilleTableView.getItems().add(new AxeDeVeille(nameVeilleTextField.getText(),keywords));
	         	displayCtrl.getListVeille().add(axeNew);
	         	
	         	add_modify_pane.setVisible(false);
	        	nameVeilleTextField.setText("");
	        	keywordsTextField.setText("");
        	}else {
        		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("Attention");
    			alert.setContentText("Deja exist!");
    			alert.showAndWait();
        	}
        }
    }
    
    public void addClient2TableView(Client c) {
    	clientTableView.getItems().add(c);
    }
	
    public void saveOneClient(Client c) {
    	displayCtrl.getFileManager().saveOneClient( (String) list_diffusion.getValue() ,  c);
    }
	
    @FXML
    private void handleEvent(MouseEvent event) {    
    }
	
    
    
    
    
}
