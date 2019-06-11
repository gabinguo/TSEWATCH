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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.webfirmframework.wffweb.tag.html.attribute.For;
import com.webfirmframework.wffweb.tag.html.identifier.TBodyAttributable;

import Launcher.App;
import Launcher.DisplayController;
import Model.Avis;
import Model.AxeDeVeille;
import Model.Client;
import Model.ListDiffusion;
import file.io.FileManager;
import file.io.Filter;
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
import util.Crawlers;

/**
 * TODO
 * C:\Users\Alienware\AppData\Local\TSEWatch\
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
    						btn_add_diffusion, btn_modify_listd,btn_not_create,btn_delete_client,btn_annuler_axe_modify,
    						btn_save_axe_modify, btn_refresh;
    
    
    @SuppressWarnings("rawtypes")
	@FXML
    private JFXComboBox siteList,page_report_veilleList,list_diffusion;
    

     @FXML
    private AnchorPane add_pane, options_pane,diffusion_pane,rapport_pane,
    			recherche_pane,axe_pane,addLd_pane;
    
     @FXML
    private Pane add_axe_pane,modify_axe_pane;
     
    @FXML
    private JFXTextField nameVeilleTextField,nameVeilleTextField_modify,nameLd;
    
    // Did not change!!! still TextArea
    @FXML
    private TextArea keywordsTextField,keywordsTextField_modify;
    
    @FXML
    private TableView veilleTableView,resultTableView;
    
    @FXML
    private TableView<Client> clientTableView = new TableView<>();
    
    
    @FXML
    private TableColumn<AxeDeVeille, String> colVeille;
    
    @FXML
    private TableColumn<Client,String> colClient,colEmail;
    @FXML
    private TableColumn<Avis,String> colDate,colTitre;
    
    
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
		modify_axe_pane.setVisible(false);
		add_axe_pane.setVisible(false);
    	recherche_pane.toFront();
    	keywordsTextField.setWrapText(true);
    	
    	list_diffusion.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) ->
    		updateClientTableView((String) newValue));
    	
    	page_report_veilleList.getSelectionModel().selectedItemProperty().addListener(
    			(v,oldValue,newValue) -> updateAvisTableView((String) newValue));
    	
    	resultTableView.setOnMouseClicked(event -> {
    		if(event.getClickCount() == 2) {
    			Avis avis = (Avis) resultTableView.getSelectionModel().getSelectedItem();
    			try {
					java.awt.Desktop.getDesktop().browse(new URI(avis.getLink()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
    		}
    	});
    	
    	
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
    	veilleTableView.setItems(App.getAxes());
    	list_diffusion.setItems(App.getDF());
    	page_report_veilleList.setItems(App.getAxes4ReportPage());
    	//set col value factory
    	colClient.setCellValueFactory(new PropertyValueFactory<>("name"));
    	colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

    	colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    	colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
    		
	}
    
    private void updateClientTableView(String value) {
    	if(value!=null) {
	    	try {
				ArrayList<String> listStr =  (ArrayList<String>) FileUtils.readLines(new File(Const.FOLDER_DIFFUSION + value + ".txt"),Charsets.ISO_8859_1);
				ObservableList<Client> listClient = FXCollections.observableArrayList();
				for(int i = 0 ; i < listStr.size();i++) {
					String[] arr = listStr.get(i).split("\\|");
					listClient.add(new Client(arr[0],arr[1]));
				}
				clientTableView.setItems(listClient);			
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else{
    		clientTableView.getItems().clear();
    	}
	}

    
    private void updateAvisTableView(String value) {
    	if(value != null) {
    		if(value.length()!=0) {
		    	String filepath = Const.FOLDER_AXE + value
		    					+ "/avis.txt";
		    	
		    	try {
					ArrayList<String> listStr = (ArrayList<String>) FileUtils.readLines(new File(filepath),Charsets.ISO_8859_1);
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
    	}else {
    		resultTableView.getItems().clear();
    	}
    	
    }
	
	@FXML
    private void handleButtonAction(ActionEvent event) {
		/*
		 *  Pop-up left page
		 */
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
        
        
        /**
         * 	Report Page's buttons
         */
        
        if(event.getSource() == btn_rapport_nouveau) {
       	 displayCtrl.showAddReport();
       }
        
        
       /**
        *  (Diffusion) Send Email page's Buttons
        */
       
        if(event.getSource() == btn_add_client) {
       	 displayCtrl.showAddClient();
       }
        
        if(event.getSource() == btn_delete_client) {
        	btn_delete_Client_Action();
        }
        
        if(event.getSource() == btn_add_diffusion) {
        	btn_add_diffusion_Action();
        }
        
        if(event.getSource() == btn_modify_listd) {
        	
        	addLd_pane.toFront();
        }
        if(event.getSource() == btn_refresh) {
        	updateAvisTableView((String) page_report_veilleList.getValue());
        }
        if(event.getSource() == btn_not_create) {
        	diffusion_pane.toFront();
        	nameLd.clear();
        }
        
        if(event.getSource() == btn_delete_diffusion) {
        	btn_delete_diffusion_Action();
        }
        
        
        if(event.getSource() == btn_envoyer) {
       	 displayCtrl.showSendMail();
       }
        
        /**
         *   Axe de Veille's page's Buttons
         */
      
        if(event.getSource() == btn_delete_axe) {
        	btn_delete_axe_Action();
        }
        
        if(event.getSource() == btn_annuler_axe) {
        	add_axe_pane.setVisible(false);
        	nameVeilleTextField.clear();
        	keywordsTextField.clear();
        }
        
        
        if(event.getSource() == btn_add_axe) {
        	modifyModeFlag = false;
        	add_axe_pane.setVisible(true);
        }
        
        if(event.getSource() == btn_modify_axe) {
        	btn_modify_axe_Action() ;
        }
        
        if(event.getSource() == btn_save_axe) {
        	btn_save_axe_Action();
        }
        
        
        /**
         *   Page recherche
         */

        if(event.getSource() == btn_recherche_ok) {    	
        	rapport_pane.toFront();
        	page_report_veilleList.getSelectionModel().select(0);  
        	
        	searchEngine((String) siteList.getValue());
        }
        
        /**
         *   Switch page buttons
         */
       
        
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
        	page_report_veilleList.getSelectionModel().select(0);
             rapport_pane.toFront();  
        }
        if(event.getSource() == btn_annuler_axe_modify) {
        	btn_annuler_axe_modify_Action();
        }
        if(event.getSource() == btn_save_axe_modify) {
        	btn_save_axe_modify_Action();
        }
    }
	
	private void searchEngine(String option) {
		Filter f = new Filter();
		Crawlers c = new Crawlers();
		switch (option) {
		case "Auvergnerhonealpes":
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					f.classifyAvis(c.auvergnerCrawler("", "it", 1));
				}
			}).start();;
			break;
		case "Boamp":
			new Thread( new Runnable() {

				@Override
				public void run() {
					f.classifyAvis(c.getLinksBOAMP("01/05/2019","10/05/2019",2));
				}
				
			}).start();
			break;
		case "Proxilegales":
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					f.classifyAvis(c.proxiLegalesCrawler("logiciel", 5));
					
				}
			}).start();
			
		case "Marche-publics(info)":
			new Thread(new Runnable() {
				@Override
				public void run() {
					f.classifyAvis(c.marchepublicsInfoCrawler("75", "<8"));
				}
				
			}).start();
		break;
		default:
			break;
		}
		
	}

	public void btn_save_axe_modify_Action() {
		
		
		try {
			FileUtils.deleteDirectory(new File(Const.FOLDER_AXE + ((AxeDeVeille)veilleTableView.getSelectionModel().getSelectedItem()).getName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int axe2ModifyIndex = (int) veilleTableView.getSelectionModel().getSelectedIndex();
		
		
		String name = nameVeilleTextField_modify.getText().trim();
		String[] keywordsArr = keywordsTextField_modify.getText().trim().split(","); 
		boolean flag = true;
		
		if(! ((AxeDeVeille)veilleTableView.getSelectionModel().getSelectedItem()).getName().equals(name) ) {
			for(int i=0;i<App.getListVeille().size();i++) {
	    		if(App.getListVeille().get(i).getName().equals(nameVeilleTextField_modify.getText().trim())) {
	    			flag = false;
	    			Alert alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Error");
	    			alert.setHeaderText("Attention");
	    			alert.setContentText("Vous ne pouvez pas créé un axe avec nom déjà existant.\n"
	    					+ "Veuillez choisir un autre nom!");
	    			alert.showAndWait();
	    		}
	    	}
		}
		if(flag) {
			if(name.length() != 0 && keywordsArr.length != 0) {		
				ArrayList<String> listKeywords = new ArrayList<String>();
				for(String str : keywordsArr) {
					listKeywords.add(str);
				}			
				AxeDeVeille axeNew = new AxeDeVeille(name,listKeywords);
				veilleTableView.getItems().remove(axe2ModifyIndex);
				veilleTableView.getItems().add(axe2ModifyIndex, axeNew);
				
				
				App.getListVeille().remove(axe2ModifyIndex);
				App.getListVeille().add(axe2ModifyIndex,axeNew);

				displayCtrl.getFileManager().saveAxe(axeNew,false);	
				reWriteAllAxeListTXT();
				
				
				modify_axe_pane.setVisible(false);

			}else {
				flag = false;
	    		Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Attention");
				alert.setContentText("Vous devez renseigner un titre et des mots clés.");
				alert.showAndWait();
			}
		}
		
		page_report_veilleList.getSelectionModel().clearSelection();
		updateAvisTableView("");
		
	}
	
	public void btn_annuler_axe_modify_Action() {
		modify_axe_pane.setVisible(false);
		nameVeilleTextField_modify.setText("");
    	keywordsTextField_modify.setText("");   	
	}
	
	public void btn_delete_Client_Action() {
		if(list_diffusion.getValue()!=null) {
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
	}
	
	public void btn_modify_axe_Action() {
		if(veilleTableView.getItems().size() != 0) {
	    	modify_axe_pane.setVisible(true);
	    	
	    	AxeDeVeille axe2Modify = (AxeDeVeille) veilleTableView.getSelectionModel().getSelectedItem();
	    	nameVeilleTextField_modify.setText(axe2Modify.getName());
	    	keywordsTextField_modify.setText(axe2Modify.getStr2File());   
    	
		}else {
			
		}
	}
	
	public void btn_delete_diffusion_Action() {
		String str = nameLd.getText().trim();
    	ObservableList<String> allLists;
    	allLists = list_diffusion.getItems();
    	
    	
    	// remove the item youw wanna delete in the comboBox
    	for(int i = 0 ; i < list_diffusion.getItems().size();i++) {
    		if(list_diffusion.getItems().get(i).equals(str)) {
    			list_diffusion.getItems().remove(i);
    			break;
    		}
    	}	
    	
    	// remove the item related in the static list of App
    	for( int i = 0; i < App.getListDiffusion().size();i++) {
    		if(App.getListDiffusion().get(i).getName().equals(str)){
    			App.getListDiffusion().remove(App.getListDiffusion().get(i));
    			break;
    		}
    	}
    	
    	// detele the file .txt which store all the client in this listOfD
    	FileUtils.deleteQuietly(new File(Const.FOLDER_DIFFUSION + str + ".txt"));
    	
    	displayCtrl.getFileManager().emptyTXT(Const.FILE_DIFFUSIONLIST);
    	for(int i = 0 ; i < App.getListDiffusion().size();i++)
    	{
    		displayCtrl.getFileManager().saveLine(App.getListDiffusion().get(i).getName(), Const.FILE_DIFFUSIONLIST);
    	}
    	diffusion_pane.toFront();
	}
	
	public void btn_save_axe_Action() {
		
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
    	if(App.getListVeille() != null)
    	for(int i=0;i<App.getListVeille().size();i++) {
    		if(App.getListVeille().get(i).getName().equals(nameVeilleTextField.getText().trim())) {
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
         	
         	veilleTableView.getItems().add(axeNew);
         	App.getListVeille().add(axeNew);
         	
         	ArrayList<Object> listObj = new ArrayList<Object>();
         	for(int i =0;i<App.getListVeille().size();i++) {
         		listObj.add(App.getListVeille().get(i).getName());	
         	}
         	page_report_veilleList.getItems().clear();
         	page_report_veilleList.getItems().addAll(listObj);
         	
         	add_axe_pane.setVisible(false);
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
	
	public void btn_delete_axe_Action() {
		if(veilleTableView.getSelectionModel().getSelectedItems() == null ||
    			veilleTableView.getSelectionModel().getSelectedItems().size()==0) {
    		
    	}else {
    		ObservableList<AxeDeVeille> allAxes , selectedOne;
        	allAxes = veilleTableView.getItems();
        	selectedOne =  veilleTableView.getSelectionModel().getSelectedItems();
        	AxeDeVeille axe2delete = (AxeDeVeille) veilleTableView.getSelectionModel().getSelectedItem();
        	
        	selectedOne.forEach(allAxes::remove);
        	
        	
                   		
        	for( int i = 0; i < App.getListVeille().size();i++) {
        		if(App.getListVeille().get(i).getName().equals(axe2delete.getName())){
        			App.getListVeille().remove(App.getListVeille().get(i));
        		}
        	}
        	
        	
        	try {
				FileUtils.deleteDirectory(new File(Const.FOLDER_AXE + axe2delete.getName()));
			} catch (IOException e) {
				e.printStackTrace();
			}	
        	reWriteAllAxeListTXT();
        	ArrayList<Object> listObj = new ArrayList<Object>();
         	for(int i =0;i<App.getListVeille().size();i++) {
         		listObj.add(App.getListVeille().get(i).getName());	
         	}
         	page_report_veilleList.getItems().clear();
         	page_report_veilleList.getItems().addAll(listObj);
         	
         	
    	}	
		page_report_veilleList.getSelectionModel().clearSelection();
		updateAvisTableView("");
	}
  
	public void addClient2TableView(Client c) {
    	clientTableView.getItems().add(c);
    }
	
	public void btn_add_diffusion_Action() {
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
    	if(App.getListDiffusion() != null)
    	for(int i=0;i<App.getListDiffusion().size();i++) {
    		if(App.getListDiffusion().get(i).getName().equals(name)) {
    			flag = false;
    		}
    	}
    	if(flag) {
        	
        	
        	ListDiffusion listNew  = new ListDiffusion(name, empty);
        	
        	
         	displayCtrl.getFileManager().creatediffusionListTxT(listNew);
         	
         	list_diffusion.getItems().add(listNew.getName());
         	App.getListDiffusion().add(listNew);
         	
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
    
	public void saveOneClient(Client c) {
    	displayCtrl.getFileManager().saveOneClient( (String) list_diffusion.getValue() ,  c);
    }
	
	
	public void reWriteAllAxeListTXT() {
		displayCtrl.getFileManager().emptyTXT(Const.FILE_AXELIST);
    	for(int i = 0 ; i < App.getListVeille().size();i++) {
    		displayCtrl.getFileManager().saveLine(
    				App.getListVeille().get(i).getName(), Const.FILE_AXELIST);
    	}
	}
	
    @FXML
    private void handleEvent(MouseEvent event) {    
    }
	
    
    
    
    
}
