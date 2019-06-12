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
	
	@SuppressWarnings("static-access")
	public void classifyAvis( ArrayList<Avis> listAvis) {
		
		ArrayList<AxeDeVeille> listAxes = App.getListVeille();
		for(Avis avis : listAvis){ 
			for(AxeDeVeille axe : listAxes) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						String path = "";
						if(avis.checkKeywordHTML(axe.getKeywords())){
							path = Const.FOLDER_AXE + axe.getName() + "/avis.txt";
							displayCtrl.getFileManager().saveAvis(avis, path);
							displayCtrl.getHomecontroller().updateAvisTableView();
						}	
					}
					
				}).start();
							
			}
		}
	}
}