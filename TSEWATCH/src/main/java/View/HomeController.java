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
import java.lang.Thread.State;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.SynchronousQueue;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
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
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.util.Callback;
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
	private static ArrayList<String> regions = new ArrayList<String>();
	private DisplayController displayCtrl;
	private boolean modifyModeFlag = false;
	
    
    @FXML
    private JFXButton btn_add,btn_back,btn_github,btn_axe,btn_recherche,btn_diffusion,btn_rapport
    						,btn_rapport_nouveau,btn_add_client, btn_envoyer, btn_add_axe,btn_delete_axe,
    						btn_modify_axe, btn_annuler_axe,btn_save_axe,btn_recherche_ok,btn_delete_diffusion,
    						btn_add_diffusion, btn_modify_listd,btn_not_create,btn_delete_client,btn_annuler_axe_modify,
    						btn_save_axe_modify, btn_refresh,btn_option;
    
    
    
    
	@FXML
    private JFXComboBox siteList,date_parution_list,page_report_veilleList,list_diffusion,checkBox_region;
    
     @FXML
    private AnchorPane add_pane, options_pane,diffusion_pane,rapport_pane,
    			recherche_pane,axe_pane,addLd_pane;
    
     @FXML
    private Pane add_axe_pane,modify_axe_pane,option_pane;
     
    @FXML
    private JFXTextField nameVeilleTextField,nameVeilleTextField_modify,nameLd,option_mot,option_region;
    
    @FXML
    private JFXTextField textField_sender,textField_api,textField_private_api;
    
    @FXML
    private HBox hbox_parution,hbox_region;
    
    @FXML
    private JFXDatePicker option_de,option_a;
    
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
    
    
    
    @FXML
    private Label label_mot,label_region,label_de,label_a,label_parution;
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void initialize() {
    	/**
		 *  get DisplayController for displaying or some new Tab
		 */
    	
    	btn_recherche.setOpacity(0.5);
    	
        resultTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		displayCtrl = DisplayController.getInstance();
		clientTableView.setPlaceholder(new Label("vide"));
		veilleTableView.setPlaceholder(new Label("vide"));
		resultTableView.setPlaceholder(new Label("vide"));
		modify_axe_pane.setVisible(false);
		add_axe_pane.setVisible(false);
		option_pane.setVisible(false);
		hbox_parution.setVisible(false);
		
    	recherche_pane.toFront();
    	keywordsTextField.setWrapText(true);
    	
    	list_diffusion.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) ->
    		updateClientTableView((String) newValue));
    	
    	page_report_veilleList.getSelectionModel().selectedItemProperty().addListener(
    			(v,oldValue,newValue) -> updateAvisTableView((String) newValue));
    	
    	siteList.getSelectionModel().selectedItemProperty().addListener(
    			(v,oldValue,newValue)->setOption((String)newValue));
    	
    	
    	
    	
    	resultTableView.setOnMouseClicked(event -> {
    		if(event.getClickCount() == 2) {
    			Avis avis = (Avis) resultTableView.getSelectionModel().getSelectedItem();
    			try {
					java.awt.Desktop.getDesktop().browse(new URI(avis.getLink()));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
				
    		}
    	});
    	
    	
    	//Search page's table
    	for(String name : Const.namesOfSites) {
			sites.add(name);
		}
    	
    	ObservableList<String> list = FXCollections.observableArrayList(sites);
    	siteList.setItems(list);
    	siteList.getSelectionModel().select(0);
    	
    	for(String region : Const.regionList) {
    		regions.add(region);
    	}
    	ObservableList<String> listRegion = FXCollections.observableArrayList(regions);
    	checkBox_region.setItems(listRegion);
    	checkBox_region.getSelectionModel().select(0);
    	
    	
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
    
    private void en_disable(int option) {
    	switch (option) {
		case 1://Boamp
			option_mot.setDisable(true);
			option_region.setDisable(false);
			option_de.setDisable(false);
			hbox_region.setVisible(false);
			option_a.setDisable(false);
			date_parution_list.setDisable(true);
			hbox_parution.setVisible(false);
			label_mot.setTextFill(Color.web("#e9c9cd"));
			label_region.setTextFill(Color.web("#000000"));
			label_de.setTextFill(Color.web("#000000"));
			label_a.setTextFill(Color.web("#000000"));
			break;
		case 2: //Marche-publics(info)
			
			ArrayList<String> arrDate = new ArrayList<String>();
	    	for(String str : Const.dateList) {
	    		arrDate.add(str);
	    	}
	    	ObservableList<String> listDate = FXCollections.observableArrayList(arrDate);
	    	date_parution_list.setItems(listDate);
	    	date_parution_list.getSelectionModel().select(0);
			
			option_mot.setDisable(true);
			option_region.setDisable(false);
			option_de.setDisable(true);
			hbox_region.setVisible(false);
			option_a.setDisable(true);
			date_parution_list.setDisable(false);
			hbox_parution.setVisible(true);
			label_mot.setTextFill(Color.web("#e9c9cd"));
			label_region.setTextFill(Color.web("#000000"));
			label_de.setTextFill(Color.web("#e9c9cd"));
			label_a.setTextFill(Color.web("#e9c9cd"));
			
			break;
		case 3: //Proxilegales
			option_mot.setDisable(false);
			option_region.setDisable(true);
			option_de.setDisable(true);
			option_a.setDisable(true);
			hbox_region.setVisible(false);
			date_parution_list.setDisable(true);
			hbox_parution.setVisible(false);
			label_mot.setTextFill(Color.web("#000000"));
			label_region.setTextFill(Color.web("#e9c9cd"));
			label_de.setTextFill(Color.web("#e9c9cd"));
			label_a.setTextFill(Color.web("#e9c9cd"));
			break;
		case 4: //Marche-publics(gouv)
			option_mot.setDisable(true);
			option_region.setDisable(true);
			option_de.setDisable(true);
			option_a.setDisable(true);
			date_parution_list.setDisable(true);
			hbox_region.setVisible(false);
			hbox_parution.setVisible(false);
			label_mot.setTextFill(Color.web("#e9c9cd"));
			label_region.setTextFill(Color.web("#e9c9cd"));
			label_de.setTextFill(Color.web("#e9c9cd"));
			label_a.setTextFill(Color.web("#e9c9cd"));
			break;
		case 5: //Auvergnerhonealpes
			option_mot.setDisable(false);
			option_region.setDisable(false);
			option_de.setDisable(true);
			option_a.setDisable(true);
			hbox_region.setVisible(false);
			date_parution_list.setDisable(true);
			hbox_parution.setVisible(false);
			label_mot.setTextFill(Color.web("#000000"));
			label_region.setTextFill(Color.web("#000000"));
			label_de.setTextFill(Color.web("#e9c9cd"));
			label_a.setTextFill(Color.web("#e9c9cd"));
			break;
		case 6: //Ted.europa
			option_mot.setDisable(true);
			option_region.setDisable(false);
			option_de.setDisable(false);
			option_a.setDisable(false);
			date_parution_list.setDisable(true);
			hbox_parution.setVisible(false);
			hbox_region.setVisible(false);
			label_mot.setTextFill(Color.web("#e9c9cd"));
			label_region.setTextFill(Color.web("#000000"));
			label_de.setTextFill(Color.web("#000000"));
			label_a.setTextFill(Color.web("#000000"));
			break;
		case 7: //FranceMarche
			option_mot.setDisable(true);
			option_region.setDisable(false);
			option_de.setDisable(false);
			option_a.setDisable(false);
			hbox_region.setVisible(true);
			date_parution_list.setDisable(true);
			hbox_parution.setVisible(false);
			label_mot.setTextFill(Color.web("#e9c9cd"));
			label_region.setTextFill(Color.web("#000000"));
			label_de.setTextFill(Color.web("#000000"));
			label_a.setTextFill(Color.web("#000000"));
			
			break;
		case 8: //E-marchespublics 
			option_mot.setDisable(false);
			option_region.setDisable(false);
			option_de.setDisable(true);
			hbox_region.setVisible(false);
			option_a.setDisable(true);
			date_parution_list.setDisable(true);
			hbox_parution.setVisible(false);
			label_mot.setTextFill(Color.web("#000000"));
			label_region.setTextFill(Color.web("#000000"));
			label_de.setTextFill(Color.web("#e9c9cd"));
			label_a.setTextFill(Color.web("#e9c9cd"));
			break;
		case 9:  //Centraledesmarches
			option_mot.setDisable(true);
			option_region.setDisable(true);
			option_de.setDisable(true);
			option_a.setDisable(true);
			label_mot.setTextFill(Color.web("#e9c9cd"));
			label_region.setTextFill(Color.web("#e9c9cd"));
			label_de.setTextFill(Color.web("#e9c9cd"));
			label_a.setTextFill(Color.web("#e9c9cd"));
			hbox_region.setVisible(false);
			hbox_parution.setVisible(false);
			break;
		case 10: //Marchesonline
			ArrayList<String> arrDate2 = new ArrayList<String>();
	    	for(String str : Const.dateListMO) {
	    		arrDate2.add(str);
	    	}
	    	ObservableList<String> listDate2 = FXCollections.observableArrayList(arrDate2);
	    	date_parution_list.setItems(listDate2);
	    	date_parution_list.getSelectionModel().select(0);
			option_mot.setDisable(false);
			option_region.setDisable(true);
			option_de.setDisable(true);
			option_a.setDisable(true);
			hbox_region.setVisible(true);
			date_parution_list.setDisable(true);
			hbox_parution.setVisible(true);
			label_mot.setTextFill(Color.web("#000000"));
			label_region.setTextFill(Color.web("#e9c9cd"));
			label_parution.setTextFill(Color.web("#e9c9cd"));
			label_de.setTextFill(Color.web("#e9c9cd"));
			label_a.setTextFill(Color.web("#e9c9cd"));
			break;
    	}
    	
    		
    }
    
    private void setOption(String newValue) {
    	switch (newValue) {
		case "Boamp":
			en_disable(1);
			break;
		case "Marche-publics(info)":
			en_disable(2);
			break;
		case "Proxilegales":
			en_disable(3);
			break;
		case "Marche-publics(gouv)":
			en_disable(4);
			break;
		case "Auvergnerhonealpes":
			en_disable(5);
			break;
		case "Ted.europa":
			en_disable(6);
			break;
		case "FranceMarche":
			en_disable(7);
			break;
		case "E-marchespublics":
			en_disable(8);
			break;
		case "Centraledesmarches":
			en_disable(9);
			break;
		case "Marchesonline":
			en_disable(10);
			break;
		}

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
	
    public void updateAvisTableView() {
    	String value = (String) page_report_veilleList.getValue();
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
        
        if(event.getSource() == btn_github) {
        	//https://github.com/gabinguo/TSEWATCH
        	try {
				java.awt.Desktop.getDesktop().browse(new URI("https://github.com/gabinguo/TSEWATCH"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
        }
        if(event.getSource() == btn_option) {
        	option_pane.setVisible(!option_pane.isVisible());
        	if(option_pane.isVisible() == false) {
        		option_mot.setText("");
        		option_region.setText("");
        	}
        }
 
        /**
         * 	Report Page's buttons
         */
        
        if(event.getSource() == btn_rapport_nouveau) {
        	ObservableList<ObservableList<String>> selectedItems = resultTableView.getSelectionModel().getSelectedItems();
        	ArrayList<Avis> listAvis = new ArrayList<Avis>();
        	for (Iterator iterator = selectedItems.iterator(); iterator.hasNext();) {
				Avis avis =  (Avis) iterator.next();
				listAvis.add(avis);
			}
        	AddReportController.setListAvis(listAvis);
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
        	modify_axe_pane.setVisible(false);
        	nameVeilleTextField.clear();
        	keywordsTextField.clear();
        }
        
        
        if(event.getSource() == btn_add_axe) {
        	modify_axe_pane.setVisible(false);
        	add_axe_pane.setVisible(true);
        }
        
        if(event.getSource() == btn_modify_axe) {
        	btn_modify_axe_Action() ;
        }
        
        if(event.getSource() == btn_save_axe) {
        	modify_axe_pane.setVisible(false);
        	btn_save_axe_Action();
        }
        
        
        /**
         *   Page recherche
         */

        if(event.getSource() == btn_recherche_ok) {    	
        	rapport_pane.toFront();
        	page_report_veilleList.getSelectionModel().select(0);  
        	btn_axe.setOpacity(1.0);
            btn_recherche.setOpacity(1.0);
            btn_diffusion.setOpacity(1.0);
            btn_rapport.setOpacity(0.5);
        	/**
        	 *  0,1,2 -> year , month ,  day
        	 */
        	String[] timeStart=null, timeEnd=null,regions=null;
        	
        	String[] keyword = null;
        	
        	if(option_de.getValue() != null)
        	timeStart = option_de.getValue().toString().trim().split("-");
        	if(option_a.getValue() != null)
        	timeEnd = option_a.getValue().toString().trim().split("-");
        	if(option_mot.getText() != null)
        	keyword = option_mot.getText().trim().split(",");
        	if(option_region.getText() != null)
        	regions = option_region.getText().trim().split(",");
        	
        	searchEngine((String) siteList.getValue(),timeStart,timeEnd,keyword,regions);
        }
        
        /**
         *   Switch page buttons
         */
       
        
        if(event.getSource()== btn_axe)
        {
             btn_axe.setOpacity(0.5);
             btn_recherche.setOpacity(1.0);
             btn_diffusion.setOpacity(1.0);
             btn_rapport.setOpacity(1.0);
             axe_pane.toFront();
        }
        if(event.getSource()==btn_recherche)
        {
        	btn_axe.setOpacity(1.0);
            btn_recherche.setOpacity(0.5);
            btn_diffusion.setOpacity(1.0);
            btn_rapport.setOpacity(1.0);
        	 recherche_pane.toFront();
        }
        if(event.getSource()==btn_diffusion)
        {
        	btn_axe.setOpacity(1.0);
            btn_recherche.setOpacity(1.0);
            btn_diffusion.setOpacity(0.5);
            btn_rapport.setOpacity(1.0);
        	 diffusion_pane.toFront();
        	 
        }
        if(event.getSource()==btn_rapport)
        {
        	btn_axe.setOpacity(1.0);
            btn_recherche.setOpacity(1.0);
            btn_diffusion.setOpacity(1.0);
            btn_rapport.setOpacity(0.5);
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
	
	
	//TODO
	
	/**
	 * /*datePatution: toutes : "" Aujourd'hui : "= 0" Hier : "= 1" 2 derniers
	 *         jours : "< 2" 8 derniers jours : "< 8" 30 derniers jours : "< 30"
			 * *
	 * @return
	 */
//"Anjourd'hui", "Hier", "2 dernieres jours", "8 dernieres jours", "30 dernieres jours"
	public String getDateINFO(){
		String result = "";
		String str = (String) date_parution_list.getValue();
		switch (str) {
		case "Anjourd'hui":
			return "= 0";
		case "Hier":
			return "= 1";
		case "2 dernieres jours":
			return "< 2";
			
		case "8 dernieres jours":
			return "< 8";
		case "30 dernieres jours":
			return "< 30";
		default:
			break;
		}
		return result;
	}
	
	/**
	 * "Aujourd'hui", "Depuis 3 jours", "Depuis 1 semaine", "Depuis 2 semaine",
			"Depuis 3 semaine", "Depuis 1 mois"
	 * @return
	 */
/**
 *  * date: 	Aujourd'hui : "TODAY";
	 * 			Depuis 3 jours : "3_DAYS";
	 * 			Depuis 1 semaine : "1_WEEK";
	 * 			Depuis 2 semaine : "2_WEEK";
	 * 			Depuis 3 semaine : "3_WEEK";
	 * 			Depuis 1 mois : "1_MONTH";
	 * 			Depuis 3 mois : "3_MONTH";
 * @return
 */
	public String getDateMO() {
		String result = "";
		String str = (String) date_parution_list.getValue();
		
		switch (str) {
		case "Aujourd'hui":
			return "TODAY";
		case "Depuis 3 jours":
			return "3_DAYS";
		case "Depuis 1 semaine":
			return "1_WEEK";
		case "Depuis 2 semaine":
			return "2_WEEK";
		case "Depuis 3 semaine":
			return "3_WEEK";
		case "Depuis 1 mois":
			return "1_MONTH";
		default:
			break;
		}
		
		return result;
	}
	
	/**
	 * 
	 "Auvergne-Rhône-Alpes" , "Bourgogne-Franche-Comté" , "Île-de-France","Provence-Alpes-Côte d'Azur"
			,"Centre - Val de Loire", "Occitanie", "Nouvelle Aquitaine"
	 * @return
	 */
	public String getRegionFM() {
		String result = "";
		String str = (String) checkBox_region.getValue();
		switch (str) {
		case "Auvergne-Rhône-Alpes":
			return "auvergne-rhone-aplpes";
		case "Bourgogne-Franche-Comté":
			return "bourgogne-franche-comte";
		case "Île-de-France":
			return "ile-de-france";
		case "Provence-Alpes-Côte d'Azur":
			return "provence-alpes-cote+d%27azur";
		case "Occitanie":
			return "occitanie";
		case "Centre - Val de Loire":
			return "centre+-+val+de+loire";
		case "Nouvelle Aquitaine":
			return "nouvelle+aquitanie";	
		default:
			break;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private void searchEngine(String option,String[] timeStart,String[] timeEnd,String[] keywords,String[] regions) {
		final String ts,te;
		
		Filter f = new Filter();
		Crawlers c = new Crawlers();
		if(timeStart != null)  ts = timeStart[2]+"/"+timeStart[1]+"/"+timeStart[0]; else ts = null ;
		if(timeEnd != null)    te = timeEnd[2]+"/"+timeEnd[1]+"/"+timeEnd[0]; else te = null;
		
		ArrayList<String> regionList = new ArrayList<String>();
		
		for(String str : regions ) regionList.add(str);
		
		switch (option) {
		case "Boamp":
			
			Thread boamp = new Thread(new Runnable() {
				@Override
				public void run() {
					if(ts!=null&&te!=null) {
						f.classifyAvis(c.getLinksBOAMP(ts, te, regionList,5));
					}
				}				
			});
			boamp.start();
			break;
			
		case "Proxilegales":
			ArrayList<Avis> allAvisPGS = new ArrayList<Avis>();
			for(String str : keywords) allAvisPGS.addAll(c.proxiLegalesCrawler(str,1));
			new Thread(new Runnable() {	
				@Override
				public void run() {
					
					f.classifyAvis(allAvisPGS);
					
				}
			}).start();
			
		case "Marche-publics(info)":
			//TODO
			ArrayList<Avis> allAvisMPI = new ArrayList<Avis>();
			
			for(String str : regions) allAvisMPI.addAll(c.marchepublicsInfoCrawler(str,getDateINFO()));
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					f.classifyAvis(allAvisMPI);
				}
				
			}).start();
		break;
		
		case "Marche-publics(gouv)":
			new Thread(new Runnable() {
				@Override
				public void run() {
					f.classifyAvis(c.marchepublicGouvCrawler(5));
				}
				
			}).start();
		break;
		
		case "Auvergnerhonealpes":
			ArrayList<Avis> allAvisAGP = new ArrayList<Avis>();
			for(String str : keywords) allAvisAGP.addAll(c.auvergnerCrawler(regionList, str, 1));
			new Thread(new Runnable() {
				@Override
				public void run() {
					f.classifyAvis(allAvisAGP);
				}
				
			}).start();
		break;
		
		case "FranceMarche":
			new Thread(new Runnable() {
				@Override
				public void run() {
					f.classifyAvis(c.franceMarcheCrawler(getRegionFM(), ts, te, 5));
				}
				
			}).start();
			break;
		case "Marchesonline":
			new Thread(new Runnable() {
				@Override
				public void run() {
					f.classifyAvis(c.marchesOnlineCrawler(getDateMO(),regionList, 5));
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
    	nameLd.clear();
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
         	list_diffusion.getSelectionModel().select(list_diffusion.getItems().size()-1);
         	
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
