package file.io;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import Model.Avis;
import Model.AxeDeVeille;
import Model.Client;
import Model.ListDiffusion;
import util.Const;

public class FileManager {
	
	
	@SuppressWarnings("resource")
	public FileManager() {
		File fa = new File(Const.FILE_AXELIST);
		File fd = new File(Const.FILE_DIFFUSIONLIST);
		if(!fa.exists()) {
			try {
				new PrintWriter(Const.FILE_AXELIST);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		if(!fd.exists()) {
			try {
				new PrintWriter(Const.FILE_DIFFUSIONLIST);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Userd to save one line of .txt file 
	 * @param lineStr
	 * @param path
	 * @param rewriteFileFlag  : true to delete all the content in file
	 */
	public static void saveLine(String lineStr , String filepath) {
		try {
				FileUtils.writeStringToFile(new File(filepath), lineStr,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * used to empty/create a txt file
	 * @param pathWithFilename
	 */
	public static void emptyTXT(String pathWithFilename) {
		try {
			new PrintWriter(pathWithFilename).close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	/**
	 *   ---> 1 Store a AxeDeVeille(we will name it axe after)
	 *   We need create a folder for every axe
	 *   Usage : Store those avis that could find the keyword of this axe in it 
	 */
	public static void saveAxe(AxeDeVeille axe) {
		// Step 1 
		// ---> Create a new folder to store all the avis
		createFolder(axe.getName(), 1);
		// ---> Create a file to store info
		String infoTXT = Const.FOLDER_AXE + axe.getName() + "/" + "config.txt";
		emptyTXT(infoTXT);
		// ---> Create a file to store avis
		String avisTXT = Const.FOLDER_AXE + axe.getName() + "/" + "avis.txt";
		emptyTXT(avisTXT);
		
		// Step 2
		// --> Store name and all the keywords
		saveLine(axe.getStr2File(), infoTXT);
		
		// Step 3
		// Store all the links info in another txt file
		for(Avis avis : axe.getListAvis()) {
			saveLine(avis.getStr2File(),avisTXT);
		}
		
		// Step 4
		// Save axe's name to all_axe_list.txt
		saveLine(Const.FILE_AXELIST,axe.getName());
	}
	
	
	public static void saveDiffusionList(ListDiffusion listDiffusion) {
		// Step 1
		// --> Create a folder to store all the avis
		createFolder(null, 3);
		// --> Create a file to store a list of clients
		String fileTXT = Const.FOLDER_DIFFUSION + listDiffusion.getName() + ".txt";
		emptyTXT(fileTXT);
		
		// Step 2 
		// --> Stop all the Client info
		for(Client client : listDiffusion.getListClient()) {
			saveLine(client.getStr2File(), fileTXT);
		}
		
		// Step 3
		// --> save diffusion list's name to all_diffusion_list.txt 
		saveLine(Const.FILE_DIFFUSIONLIST, listDiffusion.getName());
	};
	
	/**
	 * Create a folder as we want
	 * @param name : filename (only for option 1)
	 * @param option
	 * 1 - AxeDeVeille 
	 * 2 - Rapport
	 * 3 - ListeDiffusion
	 */
	public static void createFolder(String name, int option) {
		
		//1 AxeDeVeille
		if(option == 1) {
			File file = new File(System.getProperty("user.home")+"/AppData/Local/TSEWatch/AxeDeVeille/" + name);
			if (!file.exists()) {
				try {
					Files.createDirectories(Paths.get(System.getProperty
						("user.home")+"/AppData/Local/TSEWatch/AxeDeVeille/" + name));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		//2 Rapport
		if(option == 2) {
			File file = new File(System.getProperty("user.home")+"/AppData/Local/TSEWatch/Rapport/");
			if (!file.exists()) {
				try {
					Files.createDirectories(Paths.get(System.getProperty
						("user.home")+"/AppData/Local/TSEWatch/Rapport/"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		//3 ListeDiffusion
		if(option == 3) {
			File file = new File(System.getProperty("user.home")+"/AppData/Local/TSEWatch/Diffusion/");
			if (!file.exists()) {
				try {
					Files.createDirectories(Paths.get(System.getProperty
						("user.home")+"/AppData/Local/TSEWatch/Diffusion/"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	
	public static AxeDeVeille readAxe(String name) throws IOException{
		AxeDeVeille axe = new AxeDeVeille(); 
		
		String path = Const.FOLDER_AXE + name +"/avis.txt";	
		File f = new File(path);
		ArrayList<String> listStr = null;
		listStr = (ArrayList<String>) FileUtils.readLines(f);
		
		ArrayList<Avis> listAvis = null;
		if(listStr==null && listStr.size() == 0) {}
		else {
			listAvis = new ArrayList<Avis>();
			for(String str : listStr) {
				String[] strAvis = str.trim().split("\\|");
				listAvis.add(new Avis(strAvis[2].trim(), strAvis[0].trim(), strAvis[1].trim()));
			}
		}
		
		axe.setListAvis(listAvis);
		axe.setName(name);
		
		String pathKeywords = Const.FOLDER_AXE + name + "/config.txt";
		File fKeywords = new File(pathKeywords);
		String[] keywordsArr = FileUtils.readLines(fKeywords)
												.get(0).trim().split(",");
		ArrayList<String> keywords = new ArrayList<String>();
		
		for(String str : keywordsArr) {
			keywords.add(str.trim());
		}
		axe.setKeywords(keywords);				
		return axe;
	}

	
	public static ListDiffusion readDiffusionList(String name) throws IOException {
		ListDiffusion ld = new ListDiffusion();
		String txtPath = Const.FOLDER_DIFFUSION + name + ".txt";
		File f = new File(txtPath);
		
		ArrayList<String> listStr = null;
		listStr = (ArrayList<String>) FileUtils.readLines(f);
		ArrayList<Client> listClient = null;
		if(listStr != null && listStr.size() !=0) {
			listClient = new ArrayList<Client>();
			for(String str : listStr) {
				String[] strClient = str.trim().split("\\|");
				listClient.add(new Client(strClient[0],strClient[1]));
			}
		}
		
		ld.setName(name);
		ld.setListClient(listClient);
		return ld;
	}
	
	
	
}
