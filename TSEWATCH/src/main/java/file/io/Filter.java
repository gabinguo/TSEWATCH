package file.io;

import java.util.ArrayList;

import Launcher.DisplayController;
import Model.Avis;
import Model.AxeDeVeille;
import util.Const;

public class Filter {
	
	private DisplayController displayCtrl ;
	
	public Filter() {
		displayCtrl = DisplayController.getInstance();
	}
	
	public void classifyAvis( ArrayList<Avis> listAvis , ArrayList<AxeDeVeille> listAxes) {
		String path = "";
		for(Avis avis : listAvis){ 
			for(AxeDeVeille axe : listAxes) {
				if(avis.checkKeywordHTML(axe.getKeywords())){
					path = Const.FOLDER_AXE + axe.getName() + "/avis.txt";
					displayCtrl.getFileManager().saveAvis(avis, path);
				}
			}
		}
	}
}
