package util;

public class Const {
	
	/**
	 *  Name list of websites we need to crawl 
	 */
	final static public String BOAMP = "https://www.boamp.fr/avis/liste";
	
	
	final static public String[] namesOfSites = {
			"Proxilegales", "Boamp" , "Marche-publics(info)",
			"Marche-publics(gouv)", "Auvergnerhonealpes",
			"Ted.europa","FranceMarche","E-marchespublics",
			"Centraledesmarches","Marchesonline"
	};
	
	
	final static public String[] dateList = {
			"Anjourd'hui", "Hier", "2 dernieres jours", "8 dernieres jours", "30 dernieres jours"
	};
	
	final static public String[] dateListMO = {
			"Aujourd'hui", "Depuis 3 jours", "Depuis 1 semaine", "Depuis 2 semaine",
			"Depuis 3 semaine", "Depuis 1 mois"
	};
	
	final static public String[] regionList = {
			"Auvergne-Rhône-Alpes" , "Bourgogne-Franche-Comté" , "Île-de-France","Provence-Alpes-Côte d'Azur"
			,"Centre - Val de Loire", "Occitanie", "Nouvelle Aquitaine"
	};
	
	
	
	/**
	 *  Configurations for Boamp.fr
	 */
	
	final static public String CONF1 = "estrecherchesimple";
	
	final static public String CONF2 = "archive";
	
	final static public String DESCRIPTION = "descripteur[]";
	
	final static public String AVIS = "typeavis[]"; /****** typeavis[] : 1 ~ 5  ********/
	
	final static public String[] listDescripteur = {"mc68","mc97","mc453","mc454","mc162","mc163","mc186","mc463","mc283","mc171"};
	
	/****** ******/
	final static public String DATESTART = "dateparutionmin";
	
	final static public String DATEEND = "dateparutionmax";
	
	
	/**
	 *  Some file/folder path
	 */
	final static public String FOLDER_DIFFUSION = System.getProperty("user.home")+"/AppData/Local/TSEWatch/Diffusion/";
	
	final static public String FOLDER_RAPPORT = System.getProperty("user.home")+"/AppData/Local/TSEWatch/Rapport/";
	
	final static public String FOLDER_AXE = System.getProperty("user.home") + "/AppData/Local/TSEWatch/AxeDeVeille/";
	
	final static public String URL_MAIN_CSS = "https://keywatch.digital-league.org/TSEWatch/Demo/assets/css/main.css";
	
	final static public String URL_STYLE_CSS = "https://keywatch.digital-league.org/TSEWatch/Demo/assets/css/style.css";
	
	final static public String FILE_AXELIST = FOLDER_AXE + "all_axe_list.txt";
	
	final static public String FILE_DIFFUSIONLIST = FOLDER_DIFFUSION + "all_diffusion_list.txt";
	
	final static public String FILE_REPORTLIST = FOLDER_RAPPORT + "all_report_list.txt";
}
