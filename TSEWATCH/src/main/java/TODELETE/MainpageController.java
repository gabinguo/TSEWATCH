package TODELETE;

import java.io.File;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import Launcher.DisplayController;
import Model.AxeDeVeille;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Const;

public class MainpageController extends Application{
	
	/**
	 *  Variables MainpageController 
	 */
	private static ArrayList<String> sites = new ArrayList<String>();
	/**
	 *  false -> hide | true -> display
	 */
	private boolean hideOptionsFlag = false;
	private boolean hideAddModifyFlag = false;
	private boolean modifyModeFlag = false;
	private DisplayController displayCtrl;
	private AxeDeVeille veilleCreating;
	private AxeDeVeille veilleSelecting;
	private ArrayList<AxeDeVeille> veilleList = new ArrayList<AxeDeVeille>();
	
	
	/**
	 *  Variables FXML
	 */
	
	@FXML
	private Label ADVWarningLabel;
	@FXML 
	private ChoiceBox<String> siteList ;
	@FXML
	private ImageView logoImageView ;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableView<AxeDeVeille> veilleTableView = new TableView();
	@SuppressWarnings("rawtypes")
	@FXML
	private TableColumn colVeille;
	@FXML
	private Pane optionsPane;
	@FXML
	private JFXButton optionsButton;
	@FXML
	private Button SMAnnulerButton;
	@FXML
	private Button ADVModifierButton;
	@FXML
	private Button DEAjouterButton;
	@FXML 
	private Button nouveauRapportButton;
	@FXML
	private Button ADVAjouterButton;
	@FXML
	private Button ADVSaveButton;
	@FXML
	private Button ADVAnnulerButton;
	
	@FXML
	private TextField nameVeilleTextField;
	@FXML
	private TextField keywordsTextField;
	@FXML
	private Tab rechercheTab;
	@FXML
	private Tab axeDeVeilleTab;
	@FXML
	private Tab rapportsTab;
	@FXML
	private Tab diffusionEmailTab;
	@FXML
	private Pane addModifyPane;
	@FXML
	private Pane infoAxePane;
	@FXML
	private TextArea displayKeywordsTextArea;
	
	@FXML
	public void initialize() {
		
		ADVWarningLabel.setVisible(false);
		colVeille.setText("Liste des veilles");
		colVeille.setCellValueFactory(new PropertyValueFactory<>("name"));
		tabChangedColorWhenSelected();
		
		/**
		 *  Add the sites we want in the main page
		 */
		File file = new File("src/main/resources/logo.jpg");
		Image image = new Image(file.toURI().toString());
		logoImageView.setImage(image);
		
		for(String name : Const.namesOfSites) {
			sites.add(name);
		}
		
		/**
		 *  Hide the optionsPane/addModifyPane (params side)
		 */
		optionsPane.setVisible(hideOptionsFlag);
		addModifyPane.setVisible(hideAddModifyFlag);
		
		/**
		 *  get DisplayController for displaying or some new Tab
		 */
		displayCtrl = DisplayController.getInstance();
		
		/**
		 * check Veille Table clickEvent
		 */
		checkVeillePageClickEvent();
		
		
		
	}
	
	private void tabChangedColorWhenSelected() {
		rechercheTab.setStyle("-fx-background-image: url(\"backG.jpg\");");
		
		diffusionEmailTab.setOnSelectionChanged(event->{
			if(diffusionEmailTab.isSelected()) {
				diffusionEmailTab.setStyle("-fx-background-image: url(\"backG.jpg\");");
			
				axeDeVeilleTab.setStyle("-fx-background-image: none");
				rechercheTab.setStyle("-fx-background-image: none");
				rapportsTab.setStyle("-fx-background-image: none");
			}
		});
		
		axeDeVeilleTab.setOnSelectionChanged(event->{
			if(axeDeVeilleTab.isSelected()) {
				axeDeVeilleTab.setStyle("-fx-background-image: url(\"backG.jpg\");");
			
				diffusionEmailTab.setStyle("-fx-background-image: none");
				rechercheTab.setStyle("-fx-background-image: none");
				rapportsTab.setStyle("-fx-background-image: none");
			}
			
		});
		rapportsTab.setOnSelectionChanged(event->{
			if(rapportsTab.isSelected()) {
				rapportsTab.setStyle("-fx-background-image: url(\"backG.jpg\");");
			
				diffusionEmailTab.setStyle("-fx-background-image: none");
				axeDeVeilleTab.setStyle("-fx-background-image: none");
				rechercheTab.setStyle("-fx-background-image: none");
			}
		});
		rechercheTab.setOnSelectionChanged(event->{
			if(rechercheTab.isSelected()) {
				rechercheTab.setStyle("-fx-background-image: url(\"backG.jpg\");");
			
				diffusionEmailTab.setStyle("-fx-background-image: none");
				axeDeVeilleTab.setStyle("-fx-background-image: none");
				rapportsTab.setStyle("-fx-background-image: none");
			}
		});
	}
	
	@FXML
	private void optionsButtonClicked() {
		
		hideOptionsFlag = !hideOptionsFlag;
		optionsPane.setVisible(hideOptionsFlag);
		
	}
	
	@FXML
	private void ADVAnnulerButtonClicked() {
		nameVeilleTextField.setText("");
		keywordsTextField.setText("");
		
		hideAddModifyFlag = !hideAddModifyFlag;
		addModifyPane.setVisible(hideAddModifyFlag);		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void checkVeillePageClickEvent() {
		veilleTableView.setRowFactory(tv -> {
			TableRow row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2)
				{
					Object clickedRow =  row.getItem();	
					AxeDeVeille veilleSelected = (AxeDeVeille) clickedRow;
					String allKeywords = "";
					
					for(String str : veilleSelected.getKeywords()) {
						allKeywords = allKeywords + str + ", "; 
					}
					allKeywords = allKeywords.substring(0, allKeywords.lastIndexOf(","));
					displayKeywordsTextArea.setText(allKeywords);
				}
				else if(!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
					veilleSelecting = (AxeDeVeille) row.getItem();
					
				}
			});
			return row;
		});
	}
	
	@FXML 
	private void ADVModifierButtonClicked() {
		
		if(veilleSelecting == null) {
			
		}else {
			modifyModeFlag = true;
			hideAddModifyFlag = !hideAddModifyFlag;
			addModifyPane.setVisible(hideAddModifyFlag);
			
			displayKeywordsTextArea.setText("");
			nameVeilleTextField.setText(veilleSelecting.getName());
			String allKeywords = "";
			for(String str : veilleSelecting.getKeywords()) {
				allKeywords = allKeywords + str + ", ";
			}
			allKeywords = allKeywords.substring(0, allKeywords.lastIndexOf(","));
			keywordsTextField.setText(allKeywords);
		}
	}
	
	@FXML
	private void DEAjouterButtonClicked() {
		displayCtrl.showAddClient();
	}
	
	@FXML
	private void envoyerButtonClicked() {
		displayCtrl.showSendMail();
	}
	
	@FXML
	private void nouveauRapportButtonClicked() {
		displayCtrl.showAddReport();
	}
	
	@FXML
	private void ADVAjouterButtonClicked() {
		displayKeywordsTextArea.setText("");
		ADVWarningLabel.setText("Existe déjà");
		ADVWarningLabel.setVisible(false);
		veilleCreating = new AxeDeVeille();
		nameVeilleTextField.setText("");
		keywordsTextField.setText("");
		if(false == hideAddModifyFlag ) {
			hideAddModifyFlag = !hideAddModifyFlag;
			addModifyPane.setVisible(hideAddModifyFlag);
		}
	}
	
	@FXML
	private void ADVSaveButtonClicked() {
			String nameOfVeille = nameVeilleTextField.getText().trim();
			String[] arrKeywords = keywordsTextField.getText().trim().split(",");
			
			ArrayList<String> listKeywords = new ArrayList<String>();
			for(int i =0;i < arrKeywords.length;i++) {
				listKeywords.add(arrKeywords[i].trim());
			}
			
			
			veilleCreating = new AxeDeVeille(nameOfVeille,listKeywords);
			updateVeilleList();
			
			if(!ADVWarningLabel.isVisible()) {
				hideAddModifyFlag = !hideAddModifyFlag;
				addModifyPane.setVisible(hideAddModifyFlag);
				modifyModeFlag = false;
			}
			
	}
	
	public boolean addVeille2List() {
		if(veilleCreating.getName().isEmpty()) 
		{
			ADVWarningLabel.setText("Rien rempli");
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
	public int getIndexVeille(String name) {
		for(int i = 0; i < veilleTableView.getItems().size();i++)
		{
			if(name == veilleTableView.getItems().get(i).getName()) {
				return i;
			}
		}
		return -1;
	}
	public void updateVeilleList() {
		if(modifyModeFlag) {
			
			String[] arrKeywords = keywordsTextField.getText().trim().split(",");
			
			ArrayList<String> listKeywords = new ArrayList<String>();
			for(int i =0;i < arrKeywords.length;i++) {
				listKeywords.add(arrKeywords[i].trim());
			}
			veilleSelecting.setName(nameVeilleTextField.getText());
			veilleSelecting.setKeywords(listKeywords);
			
			veilleTableView.getColumns().get(0).setVisible(false);
			veilleTableView.getColumns().get(0).setVisible(true);
			
		}else {
			if(addVeille2List()) {
				veilleList.add(veilleCreating);
				veilleTableView.getItems().add(veilleCreating);
				ADVWarningLabel.setVisible(false);
			}
			else 
			{
				ADVWarningLabel.setVisible(true);
			}
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
	
}
