package file.io;

import java.util.ArrayList;

import Launcher.App;
import Launcher.DisplayController;
import Model.Avis;
import Model.AxeDeVeille;
import util.Const;

public class Filter {
	
	private DisplayController displayCtrl ;
	
	public Filter() {
		displayCtrl = DisplayController.getInstance();
	}
	
	public void classifyAvis( ArrayList<Avis> listAvis) {
		String path = "";
		ArrayList<AxeDeVeille> listAxes = App.getListVeille();
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
