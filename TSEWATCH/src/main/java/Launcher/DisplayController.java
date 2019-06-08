package Launcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import Model.AxeDeVeille;
import Model.ListDiffusion;
import file.io.FileManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.Const;

public class DisplayController extends Application{
	private Stage primaryStage;
	private BorderPane rootLayout;
	//private static RootLayoutController rootLayCrtl;
	private static DisplayController instance;
	private static Stage sendMailStage;
	private static Stage addClientStage;
	private static Stage addReportStage;
	
	private static ArrayList<AxeDeVeille> listVeille = new ArrayList<AxeDeVeille>();
	private static ArrayList<ListDiffusion> listDiffusion = new ArrayList<ListDiffusion>();
	private static FileManager fileManager;
	
	
	public static void display(String[] args) {
		launch(args);
	}
	
	public ObservableList<AxeDeVeille> getAxes(){
		ObservableList<AxeDeVeille> listAxes = FXCollections.observableArrayList();
		for(AxeDeVeille axe : listVeille) {
			listAxes.add(axe);
		}
		return listAxes;
	}
	
	public ObservableList<ListDiffusion> getDF(){
		ObservableList<ListDiffusion> listDf = FXCollections.observableArrayList();
		for(ListDiffusion df : listDiffusion) {
			listDf.add(df);
		}
		return listDf;
	}
	
	
	/*
	 * Show the Mainpage Overview inside the root layout
	 */
	public void showMainpageOverview() {
		primaryStage.setResizable(false);
		try {
			// Load person overview
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("Home.fxml"));
			AnchorPane mainpageOverview = (AnchorPane) loader.load();
			Scene scene = new Scene(mainpageOverview);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	
	public void showAddReport() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("AddReport.fxml"));
			AnchorPane addReportPane;
			addReportPane = (AnchorPane) loader.load();
			addReportStage = new Stage();
			Scene scene = new Scene(addReportPane);
			addReportStage.setTitle("Ajouter Rapport");
			addReportStage.setScene(scene);
			addReportStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static DisplayController getInstance() {
		return instance;
	}

	public static void setInstance(DisplayController instance) {
		DisplayController.instance = instance;
	}

 	public void showAddClient() {
 		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("AddClient.fxml"));
			AnchorPane addClientPane;
			addClientPane = (AnchorPane) loader.load();
			addClientStage = new Stage();
			Scene scene = new Scene(addClientPane);
			addClientStage.setTitle("Ajouter Client");
			addClientStage.setScene(scene);
			addClientStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void showSendMail() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getClassLoader().getResource("SendMail.fxml"));
			AnchorPane sendMailPane = (AnchorPane) loader.load();
			sendMailStage = new Stage();
			Scene scene = new Scene(sendMailPane);
			sendMailStage.setTitle("Envoyer mail");
			sendMailStage.setScene(scene);
			sendMailStage.show();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeSendMailStage() {
		sendMailStage.close();
	}
	
	public void closeAddClientStage() {
		addClientStage.close();
	}
	
	public void closeAddReportStage() {
		addReportStage.close();
	}

	
	public void loadFiles () {
		FileManager.createFolder(null, 1);
		FileManager.createFolder(null, 2);
		FileManager.createFolder(null, 3);
		fileManager = new FileManager();
		ArrayList<String> allVeille = null;
		ArrayList<String> allDiffusionList = null;
		try {
			allVeille = (ArrayList<String>) FileUtils.readLines(new File(Const.FILE_AXELIST));
			allDiffusionList = (ArrayList<String>) FileUtils.readLines(new File(Const.FILE_DIFFUSIONLIST));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(allVeille.size());
		if(allVeille != null )
		for(String veilleName : allVeille) {
			try {
					listVeille.add(fileManager.readAxe(veilleName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(allDiffusionList!=null)
		for(String diffusionName : allDiffusionList) {
			try {
					listDiffusion.add(fileManager.readDiffusionList(diffusionName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public ArrayList<AxeDeVeille> getListVeille() {
		return listVeille;
	}

	public void setListVeille(ArrayList<AxeDeVeille> listVeille) {
		this.listVeille = listVeille;
	}

	public ArrayList<ListDiffusion> getListDiffusion() {
		return listDiffusion;
	}

	public void setListDiffusion(ArrayList<ListDiffusion> listDiffusion) {
		this.listDiffusion = listDiffusion;
	}
	
	

	public static FileManager getFileManager() {
		return fileManager;
	}

	public static void setFileManager(FileManager fileManager) {
		DisplayController.fileManager = fileManager;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("TSEWATCH");
		this.instance = this;
		
		loadFiles();
		
		//System.out.println(listVeille.size());
		showMainpageOverview();
	}
	
	
}