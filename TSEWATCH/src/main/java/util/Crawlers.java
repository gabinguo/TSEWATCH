package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.ListIterator;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Model.Avis;

public class Crawlers {
	/**
	 * the number of the location : 
	 * 	       <option value="1,3,7,15,26,38,42,43,63,69,73,74"
	 *         >AUVERGNE-RHÔNE-ALPES</option> <option value="1">01 - AIN</option>
	 *         <option value="3">03 - ALLIER</option> <option value="7">07 -
	 *         ARDECHE</option> <option value="15">15 - CANTAL</option>
	 *         <option value="26">26 - DROME</option> <option value="38">38 -
	 *         ISERE</option> <option value="42">42 - LOIRE</option>
	 *         <option value="43">43 - HAUTE-LOIRE</option> <option value="63">63 -
	 *         PUY-DE-DOME</option> <option value="69">69 - RHONE</option>
	 *         <option value="73">73 - SAVOIE</option> <option value="74">74 -
	 *         HAUTE-SAVOIE</option> > <option value="21,25,39,58,70,71,89,90"
	 *         >BOURGOGNE-FRANCHE-COMTÉ</option> <option value="21">21 -
	 *         COTE-D'OR</option> <option value="25">25 - DOUBS</option>
	 *         <option value="39">39 - JURA</option> <option value="58">58 -
	 *         NIEVRE</option> <option value="70">70 - HAUTE-SAONE</option>
	 *         <option value="71">71 - SAONE-ET-LOIRE</option> <option value="89">89
	 *         - YONNE</option> <option value="90">90 - BELFORT/TERRITOIRE</option>
	 *         > <option value="22,29,35,56" >BRETAGNE</option>
	 *         <option value="22">22 - COTES-D'ARMOR</option> <option value="29">29
	 *         - FINISTERE</option> <option value="35">35 - ILLE-ET-VILAINE</option>
	 *         <option value="56">56 - MORBIHAN</option> >
	 *         <option value="18,28,36,37,41,45" >CENTRE-VAL DE LOIRE</option>
	 *         <option value="18">18 - CHER</option> <option value="28">28 -
	 *         EURE-ET-LOIR</option> <option value="36">36 - INDRE</option>
	 *         <option value="37">37 - INDRE-ET-LOIRE</option> <option value="41">41
	 *         - LOIR-ET-CHER</option> <option value="45">45 - LOIRET</option> >
	 *         <option value="201,202" >CORSE</option> <option value="201">2a -
	 *         CORSE DU SUD</option> <option value="202">2b - HAUTE CORSE</option> >
	 *         <option value="971,972,973,974,975,976" >DOM</option>
	 *         <option value="971">971 - GUADELOUPE</option> <option value="972">972
	 *         - MARTINIQUE</option> <option value="973">973 - GUYANE</option>
	 *         <option value="974">974 - REUNION</option> <option value="975">975 -
	 *         ST PIERRE ET MIQUELON</option> <option value="976">976 -
	 *         MAYOTTE</option> >
	 *         <option value="8,10,51,52,54,55,57,67,68,88" >GRAND EST</option>
	 *         <option value="8">08 - ARDENNES</option> <option value="10">10 -
	 *         AUBE</option> <option value="51">51 - MARNE</option>
	 *         <option value="52">52 - HAUTE-MARNE</option> <option value="54">54 -
	 *         MEURTHE-ET-MOSELLE</option> <option value="55">55 - MEUSE</option>
	 *         <option value="57">57 - MOSELLE</option> <option value="67">67 -
	 *         BAS-RHIN</option> <option value="68">68 - HAUT-RHIN</option>
	 *         <option value="88">88 - VOSGES</option> >
	 *         <option value="2,59,60,62,80" >HAUTS-DE-FRANCE</option>
	 *         <option value="2">02 - AISNE</option> <option value="59">59 -
	 *         NORD</option> <option value="60">60 - OISE</option>
	 *         <option value="62">62 - PAS-DE-CALAIS</option> <option value="80">80
	 *         - SOMME</option> >
	 *         <option value="75,77,78,91,92,93,94,95" >ILE-DE-FRANCE</option>
	 *         <option value="75">75 - SEINE-PARIS</option> <option value="77">77 -
	 *         SEINE-ET-MARNE</option> <option value="78">78 - LES YVELINES</option>
	 *         <option value="91">91 - ESSONNE</option> <option value="92">92 -
	 *         HAUTS-DE-SEINE</option> <option value="93">93 -
	 *         SEINE-SAINT-DENIS</option> <option value="94">94 -
	 *         VAL-DE-MARNE</option> <option value="95">95 - VAL-D'OISE</option> >
	 *         <option value="14,27,50,61,76" >NORMANDIE</option>
	 *         <option value="14">14 - CALVADOS</option> <option value="27">27 -
	 *         EURE</option> <option value="50">50 - MANCHE</option>
	 *         <option value="61">61 - ORNE</option> <option value="76">76 -
	 *         SEINE-MARITIME</option> >
	 *         <option value="16,17,19,23,24,33,40,47,64,79,86,87" >NOUVELLE
	 *         AQUITAINE</option> <option value="16">16 - CHARENTE</option>
	 *         <option value="17">17 - CHARENTE-MARITIME</option>
	 *         <option value="19">19 - CORREZE</option> <option value="23">23 -
	 *         CREUSE</option> <option value="24">24 - DORDOGNE</option>
	 *         <option value="33">33 - GIRONDE</option> <option value="40">40 -
	 *         LANDES</option> <option value="47">47 - LOT-ET-GARONNE</option>
	 *         <option value="64">64 - PYRENEES-ATLANTIQUES</option>
	 *         <option value="79">79 - DEUX-SEVRES</option> <option value="86">86 -
	 *         VIENNE</option> <option value="87">87 - HAUTE VIENNE</option> >
	 *         <option value="9,11,12,30,31,32,34,46,48,65,66,81,82"
	 *         >OCCITANIE</option> <option value="9">09 - ARIEGE</option>
	 *         <option value="11">11 - AUDE</option> <option value="12">12 -
	 *         AVEYRON</option> <option value="30">30 - GARD</option>
	 *         <option value="31">31 - HAUTE-GARONNE</option> <option value="32">32
	 *         - GERS</option> <option value="34">34 - HERAULT</option>
	 *         <option value="46">46 - LOT</option> <option value="48">48 -
	 *         LOZERE</option> <option value="65">65 - HAUTES-PYRENEES</option>
	 *         <option value="66">66 - PYRENEES-ORIENTALES</option>
	 *         <option value="81">81 - TARN</option> <option value="82">82 -
	 *         TARN-ET-GARONNE</option> >
	 *         <option value="44,49,53,72,85" >PAYS-DE-LOIRE</option>
	 *         <option value="44">44 - LOIRE ATLANTIQUE</option>
	 *         <option value="49">49 - MAINE-ET-LOIRE</option> <option value="53">53
	 *         - MAYENNE</option> <option value="72">72 - SARTHE</option>
	 *         <option value="85">85 - VENDEE</option> >
	 *         <option value="4,5,6,13,83,84" >PROVENCE-ALPES-COTE D'AZUR</option>
	 *         <option value="4">04 - ALPES-DE-HAUTE-PROVENCE</option>
	 *         <option value="5">05 - HAUTES-ALPES</option> <option value="6">06 -
	 *         ALPES-MARITIMES</option> <option value="13">13 -
	 *         BOUCHES-DU-RHONE</option> <option value="83">83 - VAR</option>
	 *         <option value="84">84 - VAUCLUSE</option>
	 * @param args
	 */
	
	public static void main(String[] args) {
		ArrayList<String> urls = new ArrayList<String>();
		Crawlers crawler = new Crawlers();
//		urls = crawler.getLinksBOAMP();
//		
//		for(String url : urls) {
//			System.out.println(url);
//		}

		/** for test **/
//		ArrayList<Avis> avisList = crawler.franceMarcheCrawler("auvergne-rhone-alpes","2019-05-01","2019-06-01",2);
//		ArrayList<Avis> avisList = crawler.proxiLegalesCrawler("info", 2);	
//		ArrayList<Avis> avisList = crawler.marchepublicsInfoCrawler("3","< 30");	

//		ArrayList<Avis> avisList = crawler.auvergnerCrawler("","",2);
		
		//crawler.tedEuropaCrawler();
		//crawler.EmarchesCrawler();
		
		ArrayList<String> location = new ArrayList<String>();
		location.add("75");
		ArrayList<Avis> avisList = crawler.getLinksBOAMP("01/06/2019","10/06/2019",location,2);	
//		ArrayList<Avis> avisList = crawler.marchesOnlineCrawler("TODAY","76",2);
		//ArrayList<Avis> avisList = crawler.marchepublicGouvCrawler(2);
		
		for(Avis avis:avisList) {
			avis.print();
		}
		
//		ArrayList<Avis> avisList = crawler.auvergnerCrawler("","it",1);
//		ArrayList<String> keywords = new ArrayList<String>();
//		keywords.add("it");
//		keywords.add("informatique");
//		
//		for(Avis avis:avisList) {
//			//avis.print();
//			if(avis.checkKeywordHTML(keywords)) {
//				System.out.println("#*#*");
//			}
//		}

	}


	/**

	 * Crawler for Boamp 
	 * @author GUO ,ZHI
	 * @param date from (debut) to (fin), location(the list of String), the number of the pages
	 * @return the list of the avis
	 * 
	 * example : 
	 * 
	 * ArrayList<String> location = new ArrayList<String>();
	 * location.add("75"); // the number of the location(Paris)
	 * ArrayList<Avis> avisList = crawler.getLinksBOAMP("01/05/2019","10/05/2019", location ,2);
	 */
	// Scrawler ONLY for https://boamp.fr/avis/liste
		public ArrayList<Avis> getLinksBOAMP(String dateparutionmin,String dateparutionmax,ArrayList<String> location,int pageNum){
			
//			String AuthFileName = this.getClass().getClassLoader().getResource("jssecacerts").getPath();
//			System.setProperty("javax.net.ssl.trustStore",AuthFileName);
			
			ArrayList<String> listLinks = new ArrayList<String>();
			ArrayList<String> listTitre = new ArrayList<String>();
			ArrayList<String> listDate = new ArrayList<String>();
			ArrayList<String> listLocation = new ArrayList<String>();
			
			for(int j = 1; j < pageNum+1 ; j++) {
				Map<String,String> params = new HashMap<String, String>();
				params.put(Const.CONF1,"0");
				params.put(Const.CONF2,"0");
			
				// Add all the keywords corresponding
	//			for(String str : Const.listDescripteur) {
	//				params.put(Const.DESCRIPTION, str);
	//			}
	//			params.put("descripteur[]", "mc38");
	//			params.put("descripteur[]", "mc283");
				
				//params.put("descripteur[]", "mc186");
	
				//params.put("numerodepartement[]", "75");
				
	//			params.put("descripteur[]", "mc68");
	//			params.put("descripteur[]", "mc97");
	//			params.put("descripteur[]", "mc453");
	//			params.put("descripteur[]", "mc454");
	//			params.put("descripteur[]", "mc162");
	//			params.put("descripteur[]", "mc163");
	//			params.put("descripteur[]", "mc186");
	//			params.put("descripteur[]", "mc463");
	//			params.put("descripteur[]", "mc283");
	//			params.put("descripteur[]", "mc171");
				params.put("page", Integer.toString(j));
				
				params.put("dateparutionmin", dateparutionmin);
				params.put("dateparutionmax", dateparutionmax);
		
				params.put("datelimitereponsemin", "");
				params.put("datelimitereponsemax", "");
			
			
	//			params.put("dateparutionmin", "01%2F06%2F2019");
	//			params.put("dateparutionmax", "07%2F06%2F2019");
				// 5 -> Cover all 1-4 options
				params.put(Const.AVIS, "5");
			
				// Get result
				String result;
				try {
					result = HTTPRequest.sendPostBoamp(Const.BOAMP, params,location);
					Document doc = Jsoup.parse(result);
					/**
					 *  To verify if the results exist in more than one page
					 */
					Elements test = doc.getElementsByClass("search-result-caption");
					System.out.println(test.html());
					ArrayList<String> linksOfPages = new ArrayList<String>();
					Elements elesPageIndex = doc.getElementsByAttributeValueStarting("href", "/avis/page?page=");
					for(Element ele : elesPageIndex)
					{
						if(!linksOfPages.contains("https://www.boamp.fr" + ele.attr("href")))
							linksOfPages.add("https://www.boamp.fr" + ele.attr("href"));
					}
	
					//get the link from the HTML data
					Elements hrefs = doc.getElementsByAttributeValueContaining("href","/avis/detail/");
					for(Element href : hrefs) {
						if(!listLinks.contains("https://www.boamp.fr" + href.attr("href")))
							listLinks.add("https://www.boamp.fr" + href.attr("href"));
					}
					
					//get the titre from the HTML data
					//Elements eles_titre = doc.getElementsByAttributeValueContaining("href","/avis/detail/");
					for(int i =0;i<hrefs.size();i +=2) {
						Element ele = hrefs.get(i);
						listTitre.add(ele.text());
					}
					
					//get the date from the HTML data
					Elements eles_date = doc.getElementsByClass("date-publishing");
					for(Element ele : eles_date) {
						listDate.add(ele.text());
					}
					
					//get the location from the HTML data
					Elements eles_location = doc.getElementsByClass("avis-geo");
					for(Element ele : eles_location) {
						listLocation.add(ele.text());
					}
					//for the PDF(DO NOT DELETE)
	//				ListIterator li = listLinks.listIterator();
	//		        while(li.hasNext()){
	//		            Object obj = li.next();
	//		            int length = obj.toString().split("/").length;
	//		            li.set("https://www.boamp.fr/avis/pdf/" + obj.toString().split("/")[length-2]);
	//		        }
	
				
	//		        System.out.println(listLinks.size());
	//		        System.out.println(listTitre.size());
	//		        System.out.println(listDate.size());
	//		        System.out.println(listLocation.size());
	//		        System.out.println(listLinks);
	//		        System.out.println(listTitre);
	//		        System.out.println(listDate);
	//		        System.out.println(listLocation);
			        
			     
			        //return listLinks;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			ArrayList<Avis> avisList = new ArrayList<Avis>();
				for (int i = 0; i < listLinks.size(); i++) {
					avisList.add(new Avis(listDate.get(i), listTitre.get(i), listLinks.get(i),listLocation.get(i)));
				}

			return avisList;

		}

	/*****************************************************/
	/**
	 * Crawler for ProxiLegales
	 * 
	 * @author ZHI
	 * @param the key words and the number of the pages(including the first page) we
	 *            set 10 avis on every page( you can change it in
	 *            "se_iMaxElementPerPage" )
	 * @return the list of the avis
	 * 
	 * example :
	 * ArrayList<Avis> avisList = crawler.proxiLegalesCrawler("info", 2);
	 */
	public ArrayList<Avis> proxiLegalesCrawler(String filtre, int pageNum) {
		// define the url of the site
		String urlProxi = "https://www.proxilegales.fr/publisher_portail/public/annonce/afficherAnnonces.jsp;jsessionid=1A20E1BB4A2D9511152F7E21406CFB34";
		// "https://www.proxilegales.fr/publisher_portail/public/annonce/afficherAnnonces.jsp;jsessionid=52E4BDCF7F89FB5D5DF58052EAEFA420";

		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();

		/** for the fourniture **/
		// for the different page
		for (int j = 0; j < pageNum; j++) {
			// define the map of the settings
			Map<String, String> params = new HashMap<String, String>();

			params.put("filtre", filtre);
			params.put("bDisplaySearchEngine", "false");
			params.put("bLaunchSearch", "true");
			params.put("sSEOperatorValue", "OR");
			params.put("filtreType", "marche.ALL_REF");
			params.put("type_avis", "tout");
			params.put("se_iMaxElementPerPage", "10");
			params.put("iIdMarcheType", "2");
			params.put("numPage", Integer.toString(j));
			/*
			 * 
			 * bDisplaySearchEngine: false bLaunchSearch: true sActionMarchePersonneItem:
			 * sMarchePersonneItemListMarche: sMarchePersonneItemListMarcheAll: filtre: info
			 * sSEOperatorValue: OR filtreType: marche.ALL_REF raisonSociale:
			 * iIdDepartement: type_avis: tout iIdMarcheType: se_tsStartDate: se_tsEndDate:
			 */

			String result = null;
			// send POST request to the site to get the HTML data
			try {
				result = HTTPRequest.sendPost(urlProxi, params);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(result);

			String[] ListString;
			// parse the HTML data
			Document doc = Jsoup.parse(result);

			// get the links from the HTML data
			Elements eles_link1 = doc.getElementsByAttributeValueContaining("onclick",
					"javascript:window.name=\"mainWindow\";OuvrirPopup");
			ArrayList<String> listHTML = new ArrayList<String>();
			for (Element ele : eles_link1) {
				// System.out.println(ele.attr("onclick"));
				listHTML.add(ele.attr("onclick"));
			}

			// split the String to get the links
			for (String link : listHTML) {
				// System.out.println(link);
				ListString = link.split("\"");
				listLinks.add("https://www.proxilegales.fr" + ListString[3]);
			}

			// get the titre from the HTML data
			Elements eles_titre = doc.getElementsByClass("post-block");

			for (int i = 0; i < eles_titre.size(); i += 2) {
				Element ele = eles_titre.get(i);
				listTitre.add(ele.text());

			}

			// get the date from the HTML data
			for (int i = 1; i < eles_titre.size(); i += 2) {
				Element ele = eles_titre.get(i);
				listDate.add(ele.text().split("heures")[0]);
			}
		}

		/** for the service **/
		// for the different page
		for (int j = 0; j < pageNum; j++) {
			// define the map of the settings
			Map<String, String> params = new HashMap<String, String>();

			params.put("filtre", filtre);
			params.put("bDisplaySearchEngine", "false");
			params.put("bLaunchSearch", "true");
			params.put("sSEOperatorValue", "OR");
			params.put("filtreType", "marche.ALL_REF");
			params.put("type_avis", "tout");
			params.put("se_iMaxElementPerPage", "10");
			params.put("iIdMarcheType", "3");
			params.put("numPage", Integer.toString(j));
			/*
			 * 
			 * bDisplaySearchEngine: false bLaunchSearch: true sActionMarchePersonneItem:
			 * sMarchePersonneItemListMarche: sMarchePersonneItemListMarcheAll: filtre: info
			 * sSEOperatorValue: OR filtreType: marche.ALL_REF raisonSociale:
			 * iIdDepartement: type_avis: tout iIdMarcheType: se_tsStartDate: se_tsEndDate:
			 */

			String result = null;
			// send POST request to the site to get the HTML data
			try {
				result = HTTPRequest.sendPost(urlProxi, params);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(result);

			String[] ListString;
			// parse the HTML data
			Document doc = Jsoup.parse(result);

			// get the links from the HTML data
			Elements eles_link1 = doc.getElementsByAttributeValueContaining("onclick",
					"javascript:window.name=\"mainWindow\";OuvrirPopup");
			ArrayList<String> listHTML = new ArrayList<String>();
			for (Element ele : eles_link1) {
				// System.out.println(ele.attr("onclick"));
				listHTML.add(ele.attr("onclick"));
			}

			// split the String to get the links
			for (String link : listHTML) {
				// System.out.println(link);
				ListString = link.split("\"");
				listLinks.add("https://www.proxilegales.fr" + ListString[3]);
			}

			// get the titre from the HTML data
			Elements eles_titre = doc.getElementsByClass("post-block");

			for (int i = 0; i < eles_titre.size(); i += 2) {
				Element ele = eles_titre.get(i);
				listTitre.add(ele.text());

			}

			// get the date from the HTML data
			for (int i = 1; i < eles_titre.size(); i += 2) {
				Element ele = eles_titre.get(i);
				listDate.add(ele.text().split("heures")[0]);
			}
		}
//		System.out.println(listLinks.size());
//		System.out.println(listTitre.size());
//		System.out.println(listDate.size());

		ArrayList<Avis> avisList = new ArrayList<Avis>();
		for (int i = 0; i < listLinks.size(); i++) {
			avisList.add(new Avis(listDate.get(i), listTitre.get(i), listLinks.get(i)));
		}

		return avisList;

	}

	/*****************************************************/

	/**
	 * Crawler for Marche-publics(info)
	 * 
	 * @author ZHI
	 * @param the loaction(the number of the location) and the date
	 * @return list of the avis
	 * 
	 *         datePatution: toutes : "" Aujourd'hui : "= 0" Hier : "= 1" 2 derniers
	 *         jours : "< 2" 8 derniers jours : "< 8" 30 derniers jours : "< 30"
	 *         
	 *   example :
	 *   ArrayList<Avis> avisList = crawler.marchepublicsInfoCrawler("3","< 30");
	 */
	public ArrayList<Avis> marchepublicsInfoCrawler(String IDR, String dateParution) {
		// define the url of the site
		String urlMPI = "http://www.marches-publics.info/mpiaws/index.cfm?fuseaction=pub.affResultats&IDs=25";

		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();
		
		/** for the service **/
		// define the map of the settings
		Map<String, String> params = new HashMap<String, String>();
		/*
		 * 
		 * IDE: EC IDN: X listeCPV: IDP: X IDR: 1,3,7,15,26,38,42,43,63,69,73,74
		 * txtLibre: txtLibreAcheteur: txtLibreVille: txtLibreLieuExec: txtLibreRef:
		 * txtLibreObjet: dateNotifDebut: dateNotifFin: txtAcheteurNom:
		 * txtAcheteurSiret: txtTitulaireNom: txtTitulaireSiret: dateParution: = 0
		 * dateExpiration: dateExpirationPassee:
		 */

		// params.put("IDE","EC");
		 params.put("IDN", "S");
		// params.put("IDP", "X");
		params.put("IDR", IDR);
		params.put("dateParution", dateParution);

		String result = null;
		// send POST request to the site to get the HTML data
		try {
			result = HTTPRequest.sendPost(urlMPI, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(result);

		// parse the HTML data
		Document doc = Jsoup.parse(result);
		
		// ArrayList<String> listLocation = new ArrayList<String>();

		// get the links from the HTML data
		Elements eles_links = doc.getElementsByAttributeValueStarting("href",
				"index.cfm?fuseaction=pub.affPublication");
		for (Element ele : eles_links) {
			listLinks.add("http://www.marches-publics.info/mpiaws/" + ele.attr("href"));
		}

		// get the titres from the HTML data
		Elements eles_titre0 = doc.getElementsByClass("AW_Table_Ligne0");
		Elements eles_titre1 = doc.getElementsByClass("AW_Table_Ligne1");
		for (int i = 2; i < eles_titre0.size(); i += 3) {
			Element ele0 = eles_titre0.get(i);
			listTitre.add(ele0.text());
			if (i < eles_titre1.size()) {
				Element ele1 = eles_titre1.get(i);
				listTitre.add(ele1.text());
			}
		}
//		//using LinkedHashSet to delete the repeated members
//		LinkedHashSet<String> setTitre = new LinkedHashSet<String>(listTitre);
//		listTitre = new ArrayList<String>(setTitre);
//		

		// get the date from the HTML data
		for (int i = 0; i < eles_titre0.size(); i += 3) {
			Element ele0 = eles_titre0.get(i);
			listDate.add(ele0.text());
			if (i < eles_titre1.size()) {
				Element ele1 = eles_titre1.get(i);
				listDate.add(ele1.text());
			}
		}

		/** for the Fournitures**/
		// define the map of the settings
		Map<String, String> params2 = new HashMap<String, String>();
		/*
		 * 
		 * IDE: EC IDN: X listeCPV: IDP: X IDR: 1,3,7,15,26,38,42,43,63,69,73,74
		 * txtLibre: txtLibreAcheteur: txtLibreVille: txtLibreLieuExec: txtLibreRef:
		 * txtLibreObjet: dateNotifDebut: dateNotifFin: txtAcheteurNom:
		 * txtAcheteurSiret: txtTitulaireNom: txtTitulaireSiret: dateParution: = 0
		 * dateExpiration: dateExpirationPassee:
		 */

		// params.put("IDE","EC");
		 params2.put("IDN", "F");
		// params.put("IDP", "X");
		params2.put("IDR", IDR);
		params2.put("dateParution", dateParution);

		String result2 = null;
		// send POST request to the site to get the HTML data
		try {
			result2 = HTTPRequest.sendPost(urlMPI, params2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(result);

		// parse the HTML data
		Document doc2 = Jsoup.parse(result2);
		
		// ArrayList<String> listLocation = new ArrayList<String>();

		// get the links from the HTML data
		Elements eles_links2 = doc2.getElementsByAttributeValueStarting("href",
				"index.cfm?fuseaction=pub.affPublication");
		for (Element ele : eles_links2) {
			listLinks.add("http://www.marches-publics.info/mpiaws/" + ele.attr("href"));
		}

		// get the titres from the HTML data
		Elements eles_titre02 = doc2.getElementsByClass("AW_Table_Ligne0");
		Elements eles_titre12 = doc2.getElementsByClass("AW_Table_Ligne1");
		for (int i = 2; i < eles_titre02.size(); i += 3) {
			Element ele0 = eles_titre02.get(i);
			listTitre.add(ele0.text());
			if (i < eles_titre12.size()) {
				Element ele1 = eles_titre12.get(i);
				listTitre.add(ele1.text());
			}
		}
//		//using LinkedHashSet to delete the repeated members
//		LinkedHashSet<String> setTitre = new LinkedHashSet<String>(listTitre);
//		listTitre = new ArrayList<String>(setTitre);
//		

		// get the date from the HTML data
		for (int i = 0; i < eles_titre02.size(); i += 3) {
			Element ele0 = eles_titre02.get(i);
			listDate.add(ele0.text());
			if (i < eles_titre12.size()) {
				Element ele1 = eles_titre12.get(i);
				listDate.add(ele1.text());
			}
		}

//		System.out.println(listLinks.size());
//		System.out.println(listTitre.size());
//		System.out.println(listDate.size());

//		System.out.println(listLinks);
//		System.out.println(listTitre);
//		System.out.println(listDate);

		ArrayList<Avis> avisList = new ArrayList<Avis>();
		for (int i = 0; i < listLinks.size(); i++) {
			avisList.add(new Avis(listDate.get(i), listTitre.get(i), listLinks.get(i)));
		}

		// for the list of the loaction
//		try {
//			result = HTTPRequest.sendGET(urlMPI);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(result);
//		Elements eles_location = doc.getElementsByAttributeValue("label", "Les régions");
//		for(Element ele : eles_location) {
//			//listLinks.add("http://www.marches-publics.info/mpiaws/"+ele.attr("href"));
//			System.out.println(ele.text());
//		}

		return avisList;
	 
	}

	/*****************************************************/

	/**
	 * Crawler for Marche-publics(gouv)
	 * 
	 * @author ZHI
	 * @param the date before today (Aujourd'hui : 1  ;2 derniers
	 *         jours : 2 ;3 derniers jours : 3; 4 derniers jours : 4....)
	 * @return list of the avis
	 * 
	 *         can't change the page because of the "PRADO_PAGESTATE" so we find the result day by day
	 * example: 
	 * ArrayList<Avis> avisList = crawler.marchepublicGouvCrawler(2);
	 */
	public ArrayList<Avis> marchepublicGouvCrawler(int dateparation) {
		/*
		 * ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeStart: 01/06/2019
		 * ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeEnd: 06/06/2019
		 * ctl0$CONTENU_PAGE$AdvancedSearch$procedureType: 1
		 * 
		 */
		/*
		 * PRADO_PAGESTATE:
		 * eJztfe1y3DayaB6FpdqzcqosaUjOpxJnS5Zkx7mWrZUcnzr3jwviYEa0OeQEJGVrt/wA501W/+
		 * 8b6MVuNwCS+OAMKWnGlm2lEjsDovHR6G50oxuNwTjoud6o6wX+
		 * IPC6wWQy9shoNOqTzjgYjP0z2ukM3V5Adt3df4e7nV/Irrf773S3u7sRZFFnA373jd+d3X9/
		 * LgrcDR2ga1boFQVlicdBOhVMHwv03ke7G6cBC+fZnyza+
		 * CXdHQ52N3YYTdMkZwFNdwIS0XjMQsp29sv/ff7fb/Rf23ESkOCcbr9P//HhSS+
		 * gdNIfDALS6fb724CD4WDo9bB5aP1tmIZnEd345QxGUQ120Dy2Ua/
		 * F2E4ojfTxyZIVjHHYPEbXbTPIvfEsjMM0YyQLk1gfrvXtTgN3vd2NMxKPKcn3pjTO+
		 * AxcOfI5iWn0Mpkm2rz4V70tV7TlFyBh/CFqASaH0FV6epqQ2bxddxYjeH3Z0CtyEU5zjp+
		 * jPAqjMJ7mFOv5Fa27FdxwdyMi8ZRMaVoUNg0ZZprSAxokcUyDjDKE62FtFxuD1aGnlF2EAT2iWSg+
		 * u1XfXvnbc3c3GJ1TAm28VMDKgfDeeJvxh6Mk3k9m84xWK1W16Vvs3TVq2AKg33K6BSkcwDDLRnHZxlCwl78HUh6f56HSfjHkvSDIaRgdJywjYWQMaKAC9DWAcoaKRBvWjBZIdYOvNgxEclgfWjrPsnm6u7Pz8ePH7RlhwBzp1jw
		 * /i8Ig3Z4m+cX2hO2EQPSftufn83/MYeWfEOxxm/
		 * f7ezKDafJRDQqKSrJwEgacpMph4SRj5UNajlqQwiLgbi0VmqRhL6tKLGqbqdlIV0UsyJv35IKkXAq9JGc0stkLwN7QTxmir9vZ3fhVVP6N0QlIqnOtr0c
		 * ///LrjvwukTTCYVk1Fb4EbgGRlYURCVN96ObIS25eTpKKgDU/Qncw4/0kzlgSpSf0rzxkwP/
		 * HSZo9JcEHja8Gpew7hOp0zmB4fPn6BYHfUS7xle6JNelVdFM0vC/
		 * Fx1jd3GMyo0tXCCS24wjUy6b7FRpd2cKLeJKkT/
		 * Moumlbg6otWVLHd7VCsaD3gzANrJnJxkZV80A2Z1ESAHbDuBTOOP5wDGwEJELi7E0yV+bmYgdp+
		 * jFhY+2DB9MgeXYuwARlPc2zrKKvcja9TiXABkLkHLME5jGj5UB0ALcC6AmAF7Gg/1Brn2M+
		 * iffKcdRg3iaXpR8/f8FdWM7WszaJehGlFvhmwWLd09p5rL3AkvZFwcgkTLdjlbhWiWeV+
		 * FZJ1yrpWSV9q2SgEybImeDDKcXNxqo6tEqsyXjWZDxrMp41Gc+
		 * ajGdNxlMmgxTMaWOPZWEQUdf3S87zXevbCYWdE3fPvQsSg15ZSjBJLGXvTRLRa2z6hI5DBhJD3V9lL92WvZTqX9XLGxpBP4RNhQqmttprbLUjOFBi0SIAz5KUnrXOnrLOXrfavcvZPyMXCew6Sq3h4lp7F2pN0YNv0Y1v0Y1v0Y1v0Y1v0Y1vMYFv4cC3cOBbOPAtWu9aY
		 * +5aY+5aY+4qY3Y7qFFMkkPGkrgiSzRmItRwjsC+AoVu6dYHTWwsE8C3VX1R/
		 * chZtKdvR4efYEHjygiRVGhhtDdoRZeNGrZsbagMTKKxa9YZKXUGEoF/
		 * nLYcBtKrpUP9d5idJ3l2ROP8QZ26h+qUPn5kpr0sY+FZnqHh+RrPhDbelEX7CUxbSGaxAj4I2p/
		 * qvv/0LiApmLBxCsr+RamQD7H6EZn/
		 * 9G5cLRruNhEJ6HkSjVE6i6IX1VixCGaZhRlipebzZ7VlVnSGM/
		 * 7pDdrJIBni7Kd3tFL563XIe4oNoKWjJHPG1MHxUgsd1vdb4WO56ryQ0LH3U+oo5x+
		 * f76xjew069nKzf4QHk4ZNT0uJtF0JJ7Tu/z5NsuTJf/nPsNp/+QdKRSlPq/p/
		 * D9WhfGeavn5YCeTzYgYYkSgddNudpAAaZjTdCRE03cGBbCVsSuIwndGtKYPdYfv9fFp/
		 * AvlgZiwyM2qMCssxULIjyKBSW3QYmYPM5+VfWJj1Fggz7NUcn7O9vS1pIoHdNchTLuLgJ+
		 * juhL2On2HZI3oBPPY4Ow/Tn39Jz5OPB+HFo03U/
		 * dKywRPe3ubPvxTbRBLPkjylyYXsvbekTX5kiCBnUc5r+yDazqG9po40YVyL/1sJZNu6VI3Im1lfKr
		 * /fM5NTUeWEgXdz20xXolX4Zh3aVeEtzI5a9d+q8S9lN36L1iDuX6B7JM8SNiM1ln+/
		 * c6N1fDArS8RZ8+
		 * 57LVmqlVnZV70SqlmpLp5xbOMt0Nv8lu6aRQ7MG1is8pgIVmgGlulzkkNHR3mUhXXepk7N8O/
		 * ga9B75R2eJkFkeiUrzdlQgnQ9aVTjIBJEFQFmkZ9KGWupSuhFIpUPmeZM+
		 * K1So2vVXYgenDhpA1UKhSZCs/
		 * QxNBVAf0Sqfl1oj6mxKK1t5xGOt6m1dmJe8IRsC8yCEBg0OCeZQe9us7CSJyYuju335gbdlgSNYlS2Vi2J2lBr3u
		 * +rI6tvq92R76ocdD2LaW7HL27Pr+
		 * UYizmQCANGKYPxpSAaym1pEWN0OMqaQPrWsAbGRJSNcqE3AciQ21ivPyRG+
		 * yMVa7czW26iZqzDh3LfXCWq+tarGEDV3pDx+MofxogvWslCU6+
		 * rmGtpffUkFpWBNA8vGF1Qu9L1JN1C68sBXJtK2iuDinqh6oIoaRVifRcgXwtZJivAvGXhO4t6TU1SGdhSXbKIFtmXfFd0qTaqIZNHajRAdFWEImOLQ
		 * +WI5GkIazYOcdfDKgMFxtVRUYHQVIFQD66XaqCqMqWVqEKn6gj+
		 * uCBqR8oqdS12W3YYXRUZpofoCLH2lrIUd6yqN9WL0LFa6llcqXmXdXQBhvejUAp41ThU1fOhjuB6AAt3PYurl7ldtH4Efqt
		 * +VNeNJal6lqTq20ipVHMNv3xCx4DgJI5JFKbUUulVUe1pYIYKoQJZlNW3sNPXKKuvtazvuLK+hbu+
		 * RVb9iqyqMpWufNxr5B7DYORIUMdc4Td6GyiyvjgaFpJGj4GTtVXkCpHYAOCpA0V5zm2J0yRPC+
		 * eVWts3mq8ax+McEl+
		 * aAFogFtdJXzaBqMsBXTwlKT0ITGoYWIswGJhVVHHZ1YT0wf6h8q1TahWyXB1NjWIxtIl6qHC611NlBtg4Ic1AFn4KotzakYaeWeCr
		 * /fmFDC71kab2uvbQLOYf9nVCzKoYgWPgvP3zcDKxN8+
		 * hKiA8sZe3hFRlLG4raT6fs3AmgE7oBEWZCaNuXsL4+
		 * r0F2MhempElhEeKJOGSjvOdCJYVtrfZqhmDCINpBtJIHwQWj25dUFcTQV3BJotr943aFDakxePQ1o2rAb
		 * +TszAKhZ5+uBTW3h1HqhTrCw0tfBbln/bPE5anbxiJ0wsQ46Q6MulLdVxdG9+VAzltD6/
		 * KtqJMWcqiqAroKUq6+norgmAP/uOH+aqgkMJA+
		 * aYNo1oryezH0vVe2j4SMrVA1YXzuHRuDzswaPC4lAtoGS6C0ljPL3tshBsZvYmzuuRjHCVkvAjK7dT31ginqQTF3n5E0yp6aaZuXQWYqkx5nGtaAvrGODnYG9jzGuC6NgWqVh5yFwkCOoYtPT
		 * +F9UyFZsI39r9EAP5Qba9vUa9qEBZFyj7mwRaaIqGkGTaYHpE5UVimBrUje8iq+
		 * VgUuXaRzVqetv13zcnyvjtK56p9WRT17CIbC56NBdVFUBSpJ+l9GzEmLjR7sSizJ+7bE/
		 * f9Gkh7cqpZaB5Fun11JPaUVXMPhYpx1Kgf4Wmz0lh8UHNMuQTWZPPlB5wSqmuzeTs4TTk1Fuw0zCxR29WsJb51NYJoJNrT
		 * +/hncmrV13bpgdlFHYR5LAzKD6hl/NbKMWElsi24vgFX4gwvBIC4CYBccmbDaYZZR+
		 * 64e0FgL6dmzKLKAZNZXNk8HiAB+rVZFfdhgWgmbrkrvIjHqAjBuhtXfQoo7dSlBEMFBwaWsPE+
		 * GTPlaBqpfyx3DK4FmbW0xj2L+tvDarq25ysDo6chozC8c1pY+
		 * mJofWNoVjWtfZW2hOZ1A2BNzSgKjegIeVB9tHe8Z8FrZNNVz7Vrq5s6+
		 * pwwMqPA0FN6zBIQ8zWk2atR0tvAaacCyKSMTiP6Fsz+sX52XNR3DVKFfppATNEh5y6uj/
		 * 1BosNjC6RGy28B1TWgKo4zjiU0KNu31A5OFSJFmXogu9BRo7UyXDjTZVDmZlGQUz0UWvt4PPAcxSPUkff6zFar842FnhyturmH1LlrNADzxE3WVm0gC0ajBE
		 * /tZCmYafFJmKev944sutGOOZAHqj7q6/frsf+
		 * cJfmcAn7DOiky0KwGTQo0AJon9yYnWAAjA8813GPCaAcpCuk+g60oZ7U4HroLSXcZlGkpaCPbx+
		 * BHUJ5BTbcANUoQrq5mIE0k+
		 * Mq0uC9JHPPV6C5DTSj09bk1gGpHAlWXT8P9GqNAO8rRmWgBgEYNgL9JkjPQXVKgnIMksKprtCAcM8shRgso4YjEIN9JWLN7jxZTwjIoz1hQrr3toXoACs8CGTWy5cHzNmA15uJIMRGQ65XQ2j9TW3fSj3o4JpsgBjWdqhYkuuwokwE06bNqWWxcaQKfi6h2kJ521KPyWwVeK6097YxHsEBrUM
		 * +auNdRfScwDB6n+5yR+
		 * XlhjmstdA0iDKMoL9IMHIAlF48tkF5Np6qSVoV0vKIf04hmmbVknn6001EpeQnQ0KBJC+
		 * KEjonJaJ5+sNMVpNwG0LWNZ8+1jGfPtYxnT/WVF0WW7ey51sGAZx+
		 * PeK7hfLaC57QhqyiCyhPyl1VlZKCenDF6EYrEElGSpihETCCvBhX2AYpnH6B4no0K+
		 * 4zEs89IPPuMxLPPSDz7jMQzz0ie5+GYpjzma0bHoXlG4ilnJGuJ+
		 * 9KvmbtaOLcZRXP7OBm0rJvav1kcTl9t/
		 * 3cahQkXQI35aNSClvH8SkyMulncPCamv96llHJDKkCee0tULJzYD3nnoTksaEUEPdQYJo7zQtLdPLZMCShcEouptKv6Y5a4tO1gs
		 * +UebYvqV5gl46jElemPvhGqGoPwFjD/zd3XFjLsnDMrwg7oHvuvX705fPXnu+
		 * O953wEPBoFCGCWTg8ZU81Ltzj+M8sVr48SYV4fei5XV4hlVnNFX9z8hC3/jF/kOxG5WXR87o15/
		 * bFyucjXRoKzl+TVb5VWTIRiA3FW67AUxUrM/FDEOTgcspgZj9ayQyoXXIDy+
		 * XqVlVM7XFwGy3oyvj2Ok7g6F77BSCvIz19Q5pZ3/WCWk1OiOGbLe8L4JTRvUiwiICV8tyWtG4K/
		 * 227Dw9FdlOeUp/lsRtiljXRo+TS7RJS/Rl3sjfghFAwUkj+Jkp/
		 * eTUIajXVHfRIl4uIWcsOYX7tCbFUgCZLxq1+Kq1eyOIhImopy3NmrcjB6kpkcDf/qKl/HYQpy/VJ+
		 * 5gN4Fn7Cbpfe7BKzqA5s+fFTcTOuws8JRYdIGOMRx2epHYCVi34JxhKmNLe+
		 * HbeUUZVoUAPAm0Pw1Ts5wsIYVvCq9xXxdRFiTKHqBlDH5bnaSqtxvWKPFeAnM2JB+
		 * iakHRvkGXsMrCtlb0WLR3slkauRwBUzljdc07reDXxS64RYvcwMLLoHllzFcloQfQ2G1dtvyy+
		 * PmaHCdiywmM0Heok34muumoohfuF7pNVmqd8jhZ1o44DEqRMRh11fTa6vGAVZ/
		 * NiJNkNEcR5dXzlJDj+Ts/eUX46HmoG2i3y+
		 * VdqAQvweJVmqnRGWYaxV0HyUiNOVgnomRcE9QGpPubWq38ol8zlLgvPrq7vdXDUiyuknEtTR/
		 * Neeu35jl4/yjvPW3andkl7GtOxHOIqw3qjwp87EtlxVgz+qau7C/AsAyqsBvc/L+
		 * Jri2FAR34W2okS4iWxPRayrHqODcggnGoWWymUOAfr5e3yWzn8R/Y64SHxFZnTvyDCLOvUy74Bk5P
		 * /Qy2e4o8vkKeFYNMYVW+
		 * RZG1LVFFBCvOExVlJfEJlLPU4rVflP785ArFaZDfim7pt1lPxTvMLIrHCesPBfoEuQaC8Kp0W1rlktDUCRip4SJpULd2jW
		 * +MjIXH6rV20qudxSieHoJwEuHM1K3l6vSqNvUD/CshQ7sURYddv3Syqc0P/
		 * BZUxmGCrfvG0Vwdg1dyC5h9qWADfheWy+0oOQ92vsqXsh6mtk5U1FPW/
		 * 0RUZnAoUdMcnXyHlvMKAFP0mLBR0SP5WFP70jyvRfyRGp3zOJXh8Wd2try3mTYF0H1AxHDjwtR546UINPa6A3AlZELua7u9Hhf4
		 * /0CjQmoD+Oy9Pljv45xeDPTHxvQEUIDax04pizd/+
		 * czM5A6qPSBr3OYMeEjRl0uM0X8RgdUSF1Hu3vv/
		 * jZeTToDXtDZ8s53jt5cers0zH95LiDn5diBfc7b7ZuvHirxUsPSPQwg+GhpxFjcjl2TkmMtAy4Obq
		 * +GodBspUmQUjyT86j/qjT8QEzL//
		 * n9atGfHwaBOvGh79afLidkYUQQEMlg7jGvwkSgsJWwCs4aT4HMyEE6/
		 * KxtAdYpfcJEwGMhzi54PYBElen00PiIixMG3E48c7XjcPuanE4gLEc8dDL6/
		 * 8wKuZPpiwMQN3KWYUTEoWIvxIrfndUslxn4JweN+LmrBuvGze9FdMXMpyJHOCxXFx1cWL+
		 * F4lKLBHnPc1jDIPg7GhA3pEOB75X0GEh5HrNBNmZrhvp/RUTZN/G+WHOkrnES+
		 * rsTSY8nMW5vsoYiadYL+
		 * UY6nRKDAVtMRQML9aNocGKyRI7M1EUzmbhVERnPC6oB7YFvYikoSTW3BlfX13QKJnPJT0meOoIaBUib2AgstO8n37oROtG5HC1iMRExCYeXyDWOF9yRLjtZf
		 * +0v/b5j1Y8f1u8EWdfCn6cve+bEqdZ4PTer13bXLGe3bO3QOL8ATpmCConx0PXlLydZkSM/
		 * cnaEbFivdtFu9XCBL9SxQ+AQOCie2WKhgdKkUpmbJmbHdBRcg6GCkKhzH5DGQuzhIvtRyOv02vPV+
		 * NutnZErlhR7w4MPKYOYbPrK7FN9Toj01pp3qamg7Vrlu6q1XNzn8qRli5IGMnt6HA2j5Kw0H/
		 * K0DXnmCUTUKJCTCcblRvWQUiA9oDy8NY3iRCTo84tVCI6ZGvH5IqVdLejMaa0a67/
		 * N0jiZBaWitGzMEYvfirRu8ePSYqPIlJbnhwEghAHlnz3GtFH1q/GuyvW4wcjHXulfZxy0/
		 * mxkxVkqarv6TxhWcrPF/xepVi2M3bOh+vfBlesePeg/
		 * DUaJeLaomRRsPgAA4Nut4tCnrCYbkVk6y2JIhBnkmqaiSb1xmtHx4q1bEz8epzkF7BnaUzT6Qxbb10f
		 * /U9rn/aqdeIupvIWr46h1D1mFCQMc4rrmYZ50Lx7e2s3r9wVq8UDGPafcaG5iKtB8rRtkzvtU+
		 * e4pIe7ckY2ulwvfj7rFzqFH/BFrMcecyegN9DqPL3cRxeF/
		 * dCZ9HCqgYvo2bMqF6EuRf6k4xxaJSktPQNK5iwziRgPkbi0INqFSqhRFqc0j2jVtuytr/
		 * cWxkEENs8BTQMaj4kVCVa4evbPafChWBXF2TvQmxN3UqzBK3GGP7KTjN+
		 * ziOkXdpRBi694r41eMszGkl3OTT89ElrlUr0Bcaj3jbq1TfML0E1tqlE+
		 * VaqCB3K65z5XsWBqMmrxPiLloUqmYNISCGIKChGv8QZopjY09bDaJnAbUF2SvXV4JL3SIyn8kUjMuC3KcX6PzkgkBRk2AxPFfOBlUO3yiXrfmHcRGb
		 * +cKDXC5JZP1b/jVN2mqa74ZEJf0wKqxUS735h3D/s6ZgkPftwkIuB0+
		 * QxHa9dG61Kf8oDKIkOGJe2K7a2UbTCBNQi3fr1wow6ODN1+
		 * 7LsUcPjGisgMs8VTw4A14zqn11cyzEa43GRbwu/2KDlLk+
		 * j6Pxldbt7AgPy1Y2PVUhBFw3xOI2CYhCdwdJL8grJGxnG/seAJ1M+
		 * MibLivsFX39pWLQbFnZYgwXOtdsu5dkm/6liFoTLF1gvZ+
		 * 8aCA1ABlem6UTAfE5bBaFlIsuq4skkkuWsXSSs+icSxlM4PNEGvr8BqDCdNizv4xtzx/
		 * GopnjxdXznx9dU0CcLrq6ZJDr9Fn7s5SdhyD66vAgZq2imsbhBOuIsVyiYYNPSVdbbVe9xxrMeVUkXGZJ7xmybrNTO
		 * +
		 * uEMdB6POU7x55viNImrtysSqPd5Yrs40z9jy5cRZrn33WbVHu9vTZgkmcpAzhgfN3E9NLmhQMHV173ghCrq4F3XWby6sWJnq6swrX5wlpa0wE9ItddJKlC1HBNoJ6
		 * +ftFStc7dGQn6XXV4AEnqepAQ3rZ/w1aGSnl+hZn1HuH/srD2W0kDgk/StvFAT+
		 * mu0J9dBBT8qBqVqnCat7slc9b+iu47ihY93ugNGg8IDhfJfXOmAqPP4n//
		 * TVbelV734wZpECDkMnl3P5lzCfV7ztDauQgK+
		 * son0uEmqWz2We0FmYUnwEg7OwcsVfZLNWC0ofcyUODEfwWGvxNCN4UGC4aNTc5JgoTOTKOKiHXHxb9XvMa6EgVn1vZmAi9lAkD9TQaqUp6Ck
		 * +MzysqkX0YZmG8IdCc9OjkmW0R0ayPDPz5qjvY+mp+lJeX9siRZGS1eIG78mrD2QpGy/
		 * SQ5KfRVWYSIsnNxnmLeKa9v8FhIgrqEXgiot3xU+EU9Ws4RahLSflc4aL6im+
		 * 1WF5IZ3fUe1JYbpRZnMSAkxeMi2RVZQGG7+
		 * EXJYpZe1ulY6E2rGf5EhnIUeA5KFwvAcSOXmZwQxOyDisUtwhe9RMT610b+
		 * fW9D6vr71BZpKypz7U3bVEiBpVJGKUoA0RP1wX4qPqfP4adD4MWMIXAiYiVUnj8ey3puHBWr3GZ6q
		 * /L+3O56FDX1mpU/bWZe8V3u49Qvs5QoENkCdRSFkKdi7YSQ8c88Ax3ybHqM9lAkAakgaD/
		 * 4GUH0j5npKyokfjZab0QSg/UPI3RskwktPTfSwbVdq7+
		 * hYxhmDrunqRAubwrzzEhB8P8vuB6r8xqpdk3rOU7qVPaHctVngRp/
		 * j8B6ZJzcKLMKOH4hpobbauB6Z4YIpvgCksa7Vfd8NlcWLhgWXKDhRTFtEkeOgwvggZXivnuYwedpEHhvk2GWbgWfSu2AVe4SCTaV7lbdXTEDO5TkJa3A8qYa2D04GyS
		 * +Hh8mxJQw/888A/3xz/
		 * 9G3KVRKbdocwmpebCX9Yzjk6PnXKmE3BAk7BAtdXG86cshnNHMwoQav3gvkdKgKUiq5PBl8cvBspg8QizC8V5zOg4cQ5fXFy
		 * +OYx1uD3S6+vMhJnTh47eNUsj3iCnTm7vtpiFDvl0UVj2MR4PhnQ/
		 * eK8yAiivDwNA992XkROWvAq9Ik5v+bX/y+rIv7FLQ/YEUUuUZiFnAw0B2MV7xQyHpwyD6//
		 * gzkB3mNiJHHN+wJ+/pVvEhjcf1Inra6MzAnD0cg0/jJ7wvavZ8zZ+U3+
		 * SZxzRidPNrZ3xkmQ7iR5FkbpFk3ZltdxBzs80/vO8cu9/cN3gP93L6p7d+m76u769nw82XAyfKM+
		 * e7Lx7iwi8Qf4jblen2y8DAGlsKKUxXTjt8PYSQlmc3DmUZ7+ukN+w6y1S7WLJteUJ8XsUwy6p/
		 * sJ4B2M01h5D0dSm3o/HFo6q6vfxg2KWUtDmh9+
		 * okFeZuTuKB1ZetTAOvUfqk8u9NGrmJ5KHnpOk1ee/hVodNFX9OJSNDxEGkTzsydeVnkpxuva/
		 * IZBM6/IRTgF/uC3j6GoM9zdeE8uiLhBvztP5n/OT2l2Gv6LPtoM4zH9tD0/n/9jTqb0CTDLLI+
		 * 3sYNPJUb28UbA3xFJn578vZANTzYfbw57nfkn/Lsj/
		 * r6k6ebPReJizFBSP0uJM7elNoohZQnw56s8Sw0iGKrI6Zbe3BM6KSpzCQs4B2RAqeaXD0iqFSF2A54kfnLMwjhQvrhyCPDplKofMLyFTsIYCG9MOVQ4F8
		 * /QVr5+jnalp8ZAg4IFDpIZgabTwiYVWZGV2SvqCYZgjEX9onrlyu/oDaoVegXVAMkW/ek4lfEU8qM
		 * +d/mc+RlG6ioVxDNH8D1jeax/rtFulD1C3DuXjWdJmB4nYZylKKie5lFUx9M/6A39+
		 * 57wAZeCv1RVDFl7NUx7jJc/RMVpJL0JkchQMfmyT8JqueUHjmFq/+ZUmaPGQuHLQm6pUkcxbLy+
		 * KnMN8XJv5a8qvP6Zk6hMsGMJWDXUxccX8pTK+IkH95S5eP5pfua5eHBPV+SgqKRPYYmkK/UJq/
		 * FqWNGClv1SYpif9EdDrDfFlj/c9yBxb/ZuiKBr/
		 * tTf1lmU8AQnX0YG48ONQFDV6gsdsVsJXJ0wpPJRilSL5h7EaQtxii1yebA3ZSLHmSlW+
		 * pqAIla18imsooVSmOCkFeNC66FSBPmbIxFVoVuoXB1VEK1utb8tvaVJMgpCKNinFNc3RPWgso2sJXyQvd8a1dxaSrAqrHj
		 * /+G3F/kPlZbO9CQjgc/ms6KBGhb536lWqvLULYEfkfakqFRPbn1/
		 * wlR4YLLaQdXgiBny1BtFU8amnAfk/wqYksMvfKi3fmzXwYzxjBzP4NQW0yyO8zaLipsMH/
		 * mQzwIR4W2HM9326+duv4WzqpCx4snmeZfN0d2fn48eP29JdsSXTtm5Pk/xie8J2snOQX+
		 * kOfyE63ZmHQZZsiRbjJNtKPmxPw8mmQ6IMej4ns7mTAPyU8Lzlm3hquYOD+01dVv9hWb+vZUV0B/
		 * OLUoKo5k2XfwK5k8Q8772hy8C3PXaWMJ5GNKhxLn/9x8+
		 * gu7c5FV4BessXLrvm098jgRbZ5v2bdF9Mmmhrc4epK6Z29ws9+N1T3tpucQ4t3i8Gxe+
		 * Ub3lyx5YHIZ3iW5KzgFo1ljYtVjvCTXRfHkK4xQlqzQ0kjRru4eWjtHrfei+
		 * iTJwz9xXzx7pYMVQvNvKHSacRBdXr9Rl/mbshosRdg0fckxfJVa/4Pbg+
		 * Xp56kkIrFBgGXO1HCd6TphZum4N+OoWbBbibUbwLqm2fwFcdfc3Li8K8nuIrG7mGqTtqy1yyum/
		 * Cd1uOXlbv6SyJbuKjEEOUXqKf2L5B7Bl6JsrVTn8H/vU67qgo+ZKS1pcMncSwvrFQA5CK03w+Z+
		 * GMskPYVwP6KDsPU/S1pXWPeCMJPXLev9+ZzXYI/
		 * OP8LFWRUn77Iy4zqBOFszDjqTYYvwDMPeHzKOTvYjL0cN/pzeZRv1ZcVeYo2Crw+
		 * 9YaCegSoDYw1Ea+2moN+GrBWD+I5cDjdHSy7svB8bV6PE6CHA397SnNDiNu8z+9fDF+tInG3Lv916
		 * /eHL76893x3vPDd3vjC/Rgy7fi39VR8ebPjzfHY1zfS/hn8/
		 * HmpiAGfEwkKklZjICFgipMpUX9eqslRseBcoO8mdO+fy1ekSX4/vdL6ogYC/
		 * keDy6lQ9PMKdTyDVV6qSENQ0t6mdfyF8gu1/vuZVe/UXbR7M6Ca/
		 * jjCa7eegUXEPD9EFu9RWKrlsUehFad0NL8xjoWYYEC+UCImjtmpJhnfWmeqelIBpbEkw21VtuE6Bt
		 * +z6KvI0UfrAyXeGUI5B21tWJB3B9O6qEPY51STyXieyH+/
		 * O4i8bec3x7kYL3yVrCOEouIO0w9GbRU4r5/AxTFfb0ku4vuVqyF/+OJMe+
		 * LiLH7osMVC61ecPYa5NqDdtdaqjWc1hXY79kanZH87wO9/JgwSVNPkwwmeA+
		 * 9GX0PhVGcIj7Y9ZU48A3oY/Hie5jl0fWVk+TwMzl7TwvM6a/53IqMyzjEoyRL9yOaaigSGwn/
		 * Xr5u9ixKclrV6hbx2xO9/J4hGLsrp+CQOb7+cs6zk9+J+9UDE3yd8BMJsnuLA/Q1VDjgY73r/
		 * IdVqeJ1eZoDBuJyo8DysyTP0EcAbLg0zgJ1w8PJBPZycUEpYGH1OjIrBq/
		 * rPSO7xdqLNjwwDbiKnVTtLBkKssZLXp9zpdq3DKFS3tU8hkZTobN6MuKYO/
		 * s6loBSHYBdd3fZ45zojDspXv2gpWeiaEjV94a7xludNqyUxgW0erUUZp2wKX/
		 * TsoIRY7I3rPoHOnWXGFD26n1iXU/4xKpnjGL6LydHjQ1lJEhIrjvhIwtf31O28juk/T6+
		 * HElmZ5IZiuQp/CnwzRfxGDZv0GecR/v7L/
		 * j71sPe0HyRftD4cm3kzdaNlxXfQu0B6R9i9pgwTYsXfalzyh8BR9wcXV+NwyDZKt8H7486HXwi/eX
		 * /vH7ViI9Pg2Dd+
		 * FhxXmeQhxZCAA3A3yQO01lxqxSfIEHVFCs4YFqBxhHSnD2WqgWr9gn5Ck8Yx8mFyITPX4vutX4teuKdrxuHK34NYdBR35aX13CnLAQlHn3cJU5AXeU5Jwqs
		 * +N1RyXLtnpQ/68brxs2q36RChjORg+8liH3HERdHwbxySj31PQUhnaa8xIS8Ix0OfK+
		 * gw0LI9ZoJsjNdN9JX/CDDoG/j/DBnyVziJXX2QGkKxe3vjJF4KpQmxFCnU2IoaIuhYLj2B95X/
		 * J6Wi52ZKApns3Aq7s0/LqgHX2PQikgaSmLN8S79BY2S+VzSY4IXUfCqPhd5AwORneb99EMnWjciV/
		 * xgF2pZJh5fINY4X3JEuO1l/7S/9vmv4y0vff7E2ZeCH2fv+
		 * 6bEaRY4vfdr1zZXrGz37C2QOH9gmghQOTkeuqbk7TQjYuxP1o6IFevdLp6oWZh4AzJWvohzfYXncFNud1BVZmyZmx3QUYKvBxWvCb2hDMzbRDy
		 * /OvI6vfZ8Ne5ma0fkihX17sDAY+oQNuP5ToCcep2Raa00b1PTwdo1y1W/
		 * NmbtU7nD388JI7kdHc7mURIW+s+zIkGKc8ySCShR3OqNyg2rfEJSJBJETI46t1CJ6JCtHZOrfv+
		 * 1ozGmtGuu/1ckTiwVo2dhjIc4qUTvnkhmIz/iUPGhJul04YQ4sOS714g+sn41ftVPnQ1GOvZK+
		 * zjlpvNjJyvIUlXf03nCspSfL/i9SrFsZ+ycD9e/Da5Y8e5B+
		 * Ws0SggmPUqrB2kBA4Nut4tCnrCYbkVk6y2JIhBnkmqaiSb1xmtHx4q1bHRxHSc5ZlzSmKbTGbbeuj76n9Y
		 * +7VXrxN3qsSyUuscY7keZIxNzmeZB8+7trd28clesFg9g2H/GhebynCX5vDhtA5l6TuB/
		 * jkt6uCtnZKPL9eJHxKEUt0SPcwYzSCk/ALcPzn310F319+K+
		 * iSfPlw0NGH6g2vPzz7JBcZ21oUHjKF/xgiLKGJ3UneLrXgjFTYpeXhIE9Wf/OtRgeezIIo/Jl3d+
		 * FdfXlQiFLveSgxkXOxeUYfY8doDKFC3dQdX8H4mIAs17ttAddHMHWtNFr+Ka8t7+
		 * fsLG6T4Zy2uJXZUUh/b62BGOXumBanKPFVkj9vden9AsyQtHXdWUmoXAH5TZt2gs6h8hBUm0pM/
		 * IRQLGDa0CFpDdoraViy49BWvazfNu7U10I1uCkDIynQjQzmk+mxF2aRPx9x+
		 * EgbN4WyKCy28RtlPihwsbeV3WxfwQDNiEXwjWYzkMBPNpy4UULbTNr5aS/
		 * IJOCRvTeodswcP7eOtXEetf24+
		 * OW2jlRy9ncftoAiWXgErGfuVB76vYqi416lfUvz6qaqQu6g1hKsd1ep58PAgvRGRYcwQY5g5CpIkJb9oyGUZbIOPGiF8aWOT5jVKzupOrD7OKOi
		 * /aUlQGZDeQfulLmv6B+UKb6N29D/SOc3wDg+YBGe/FsO8SNeL1dPmc8ceSASWndFH2va+
		 * PgxHHQfGoc1qO9E6IUGPV8LYoTQnmrqM1MTJfHQN46/fg+
		 * qoYoQMq9zxheNAp8g7dCRFKHFVRNNRxI0IGf89mNdfP7yPP8MejeZSjGPPtovPcYuK4gdbZEl99nl45TznEW6vDliL2veaXqhSnleiIoimZ53QX01TxfXI1amPtnqfpbm
		 * +LZH0/olatGr+
		 * DMiFOmRHHzktzP1PjpORigQ5uBUUC2g5jRqf83IuJJobFscUfCn1XWaTphOSYXMQ8wbh52rImZcwtg5UxLrrUSFFIMV5UnWt4LQ1IRTdX8n6rsZeyxLdKujqhFBq92m7XLOiZBaqKUGYv48
		 * /evKRTGo+pObKeNQ7rKSp3oIysjAB/lcj7Gc+
		 * SPNakPYIMVZBiIBIgrSCUNCvqYYQo8axsOJ6VDcfzdBEZJxjxKDuyKqsJyHka5zSjx8AVmFv+
		 * TTK3SVkLUl1H3pbdDbez9HiTm8B3Ott0l0ullUeeApi3fE5VjS/
		 * xzodqUJUviT9NeND58yRLkADk4ncrctSsjg5/BEGpaBKc/
		 * QnVXyHJRHi78lWIQARhOgymtEEEkPzEED8aFRtXVjQqXnSb4IGQvwNCVswe1GrrCNm4aSMBhwbNClqu6lY0W5Gz9XVkULReQe6nnKqtL0pCNCVZkmdJfN9I3PQ0i40LRrKea2xjvrXB
		 * +tYG61cbbDmkodlwT51PkZr4j/I1kpanlYDjmciK+DYkY5qYu7R+
		 * 7p6z6Oj4sCoa8SIT0uXuo4vkkjLrE38wY0pfJmRMmTnv5UnlKmXstOXkuhUET52vcuByl8GgSARG2VESgxEUUvuwmONmdGNtyzoJvbXeZGTvW5rfuKUiKvJm47MDf2bkvKS5KvM2utOKT
		 * +UlKHwE5y1lqZVAX9LWIWNUuF1KtVXczSsScioeRE7Hyofm9WpKAagSl2csl1/9rl8tJVe1f+
		 * PhtEa6Su0i7dv9IClFfPPHj3LuZDnKozCCmeTV1a0hv4I2RbvNQGuV8XuAdynJ9MVsqvGvb/
		 * AvHrycSGP1plb2jU8gxEkIDj2HsYOxmWAYKA6TbolRfBEflot0MpuReCw8TRwLEyav5ClLf0NEHr66D4gUo
		 * /h6iKQxR+St83pKNVT85/L/
		 * Ptt5Pr1fVpDn02uSZwuFvNteyIvNXBy2LBQfdzrEaIQQCcr9oRARf1OdZX/TnWV/4/
		 * evixm2guA3gAuIbrcNBEa70FOaV7jseS3gwjiIckYPeJ7eMQ/HLOD7bfrNLufV+
		 * dQhHwRG0PCXFWlWjWVwu7aQEstGMK91YyN64vy/WTmibzAeu6ki5/INFka7fH+
		 * TBVUvrN9gQWtd97Lf0Y3gC6epBO61WUHNgSpH3WkHaDoaZbdt1lz1zt0ATHVc3WRFVbeP1NE+/3+
		 * 4ByLA PRADO_POSTBACK_TARGET: ctl0$CONTENU_PAGE$AdvancedSearch$lancerRecherche
		 * PRADO_POSTBACK_PARAMETER: undefined
		 * ctl0$bandeauEntrepriseWithoutMenu$identifiantTop:
		 * ctl0$bandeauEntrepriseWithoutMenu$passwordTop:
		 * ctl0$bandeauEntrepriseWithoutMenu$quickSearch: Recherche rapide
		 * ctl0$CONTENU_PAGE$AdvancedSearch$keywordSearch:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$orgNameAM:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$organismesNames: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$choixInclusionDescendancesServices:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$inclureDescendances
		 * ctl0$CONTENU_PAGE$AdvancedSearch$type_rechercheEntite: floue
		 * ctl0$CONTENU_PAGE$AdvancedSearch$reference:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$procedureType: 1
		 * ctl0$CONTENU_PAGE$AdvancedSearch$categorie: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$clauseSociales: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$ateliersProteges: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$siae: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$ess: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$clauseSocialesCommerceEquitable: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$clauseSocialesInsertionActiviteEconomique: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$clauseEnvironnementale: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$marchesPublicsSimplifies: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$idsSelectedGeoN2:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$numSelectedGeoN2:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$referentielCPV$cpvPrincipale:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$referentielCPV$cpvSecondaires:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$referentielCPV$rechercheFloue:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$referentielCPV$cpvArborescence view: on
		 * ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneStart: 06/06/2019
		 * ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneEnd: 06/12/2019
		 * ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeStart: 01/06/2019
		 * ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeEnd: 06/06/2019
		 * ctl0$CONTENU_PAGE$AdvancedSearch$keywordSearchBottom:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$rechercheFloue:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$floueBottom
		 * ctl0$CONTENU_PAGE$AdvancedSearch$orgNamesRestreinteSearch: 0
		 * ctl0$CONTENU_PAGE$AdvancedSearch$refRestreinteSearch:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$accesRestreinteSearch:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$rechercheName:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$RadioGroup:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$tousLesJours
		 * ctl0$CONTENU_PAGE$AdvancedSearch$formatAlerte:
		 * ctl0$CONTENU_PAGE$AdvancedSearch$formatHtml
		 * 
		 * ctl0$CONTENU_PAGE$resultSearch$listePageSizeTop: 10
		 * ctl0$CONTENU_PAGE$resultSearch$numPageTop: 1
		 * ctl0$CONTENU_PAGE$resultSearch$listePageSizeBottom: 10
		 * ctl0$CONTENU_PAGE$resultSearch$numPageBottom: 1 PRADO_POSTBACK_TARGET:
		 * ctl0$CONTENU_PAGE$resultSearch$PagerTop$ctl2
		 */
		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();
		ArrayList<String> listLocation = new ArrayList<String>();
		
		// define local date
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date_local = dateFormat.format(date).toString();
		System.out.println(date_local);
		
		
		for(int j = 0; j < dateparation; j++) {
			//define teh date
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(date);
			
			rightNow.add(Calendar.DAY_OF_MONTH,-j);
			Date dt1=rightNow.getTime();
			String dateP = dateFormat.format(dt1).toString();
		
			// define the url of the site
			String urlMPI = "https://www.marches-publics.gouv.fr/?page=entreprise.EntrepriseAdvancedSearch&searchAnnCons";
		
			/** for the fourniture **/
			// define the map of the settings
			Map<String, String> params = new HashMap<String, String>();
	//		params.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneStart", "06/06/2019");
	//		params.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneEnd", "06/12/2019");
			params.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeStart", dateP);
			params.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeEnd", dateP);
			params.put("ctl0$CONTENU_PAGE$AdvancedSearch$procedureType", "1");
			params.put("ctl0$CONTENU_PAGE$AdvancedSearch$categorie", "2");
			params.put("PRADO_PAGESTATE",
					"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");
			params.put("PRADO_POSTBACK_TARGET", "ctl0$CONTENU_PAGE$AdvancedSearch$lancerRecherche");
	
	//		params.put("ctl0$CONTENU_PAGE$resultSearch$listePageSizeTop", "20");
	//		params.put("ctl0$CONTENU_PAGE$resultSearch$listePageSizeBottom", "20");
	//		params.put("ctl0$CONTENU_PAGE$resultSearch$numPageTop", "1");
	//		params.put("ctl0$CONTENU_PAGE$resultSearch$numPageBottom", "1");
			// params.put("PRADO_POSTBACK_TARGET",
			// "ctl0$CONTENU_PAGE$resultSearch$PagerTop$ctl2");

			String result = null;
			// send POST request to the site to get the HTML data
			try {
				result = HTTPRequest.sendPost(urlMPI, params);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(result);
	
			Document doc = Jsoup.parse(result);
		

			// get the links from the HTML data
			Elements eles_links = doc.getElementsByAttributeValueStarting("href",
					"https://www.marches-publics.gouv.fr/?page=entreprise.EntrepriseDetailsConsultation");
			for (int i = 0; i < eles_links.size(); i += 2) {
				Element ele = eles_links.get(i);
				// System.out.println(ele.attr("href"));
				listLinks.add(ele.attr("href"));
			}
	
			// get the titres from the HTML data
			Elements eles_titre = doc.getElementsByClass("truncate-700");
			for (int i = 0; i < eles_links.size(); i += 2) {
				Element ele = eles_titre.get(i);
				listTitre.add(ele.text());
			}
	
			// get the date from the HTML data
			Elements eles_date = doc.getElementsByClass("date date-min clearfix");
			for (Element ele : eles_date) {
				listDate.add(ele.text());
			}
	
			// get the location from the HTML data
			Elements eles_location = doc.getElementsByClass("lieux-exe");
			for (Element ele : eles_location) {
				listLocation.add(ele.text());
			}
			
			/** for the service **/
			// define the map of the settings
						Map<String, String> params2 = new HashMap<String, String>();
				//		params.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneStart", "06/06/2019");
				//		params.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneEnd", "06/12/2019");
						params2.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeStart", dateP);
						params2.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeEnd", dateP);
						params2.put("ctl0$CONTENU_PAGE$AdvancedSearch$procedureType", "1");
						params2.put("ctl0$CONTENU_PAGE$AdvancedSearch$categorie", "3");
						params2.put("PRADO_PAGESTATE",
								"eJztfe1y3DayaB6FpdqzcqosaUjOpxJnS5Zkx7mWrZUcnzr3jwviYEa0OeQEJGVrt/wA501W/+8b6MVuNwCS+OAMKWnGlm2lEjsDovHR6G50oxuNwTjoud6o6wX+IPC6wWQy9shoNOqTzjgYjP0z2ukM3V5Adt3df4e7nV/Irrf773S3u7sRZFFnA373jd+d3X9/LgrcDR2ga1boFQVlicdBOhVMHwv03ke7G6cBC+fZnyza+CXdHQ52N3YYTdMkZwFNdwIS0XjMQsp29sv/ff7fb/Rf23ESkOCcbr9P//HhSS+gdNIfDALS6fb724CD4WDo9bB5aP1tmIZnEd345QxGUQ120Dy2Ua/F2E4ojfTxyZIVjHHYPEbXbTPIvfEsjMM0YyQLk1gfrvXtTgN3vd2NMxKPKcn3pjTO+AxcOfI5iWn0Mpkm2rz4V70tV7TlFyBh/CFqASaH0FV6epqQ2bxddxYjeH3Z0CtyEU5zjp+jPAqjMJ7mFOv5Fa27FdxwdyMi8ZRMaVoUNg0ZZprSAxokcUyDjDKE62FtFxuD1aGnlF2EAT2iWSg+u1XfXvnbc3c3GJ1TAm28VMDKgfDeeJvxh6Mk3k9m84xWK1W16Vvs3TVq2AKg33K6BSkcwDDLRnHZxlCwl78HUh6f56HSfjHkvSDIaRgdJywjYWQMaKAC9DWAcoaKRBvWjBZIdYOvNgxEclgfWjrPsnm6u7Pz8ePH7RlhwBzp1jw/i8Ig3Z4m+cX2hO2EQPSftufn83/MYeWfEOxxm/f7ezKDafJRDQqKSrJwEgacpMph4SRj5UNajlqQwiLgbi0VmqRhL6tKLGqbqdlIV0UsyJv35IKkXAq9JGc0stkLwN7QTxmir9vZ3fhVVP6N0QlIqnOtr0c///LrjvwukTTCYVk1Fb4EbgGRlYURCVN96ObIS25eTpKKgDU/Qncw4/0kzlgSpSf0rzxkwP/HSZo9JcEHja8Gpew7hOp0zmB4fPn6BYHfUS7xle6JNelVdFM0vC/Fx1jd3GMyo0tXCCS24wjUy6b7FRpd2cKLeJKkT/Moumlbg6otWVLHd7VCsaD3gzANrJnJxkZV80A2Z1ESAHbDuBTOOP5wDGwEJELi7E0yV+bmYgdp+jFhY+2DB9MgeXYuwARlPc2zrKKvcja9TiXABkLkHLME5jGj5UB0ALcC6AmAF7Gg/1Brn2M+iffKcdRg3iaXpR8/f8FdWM7WszaJehGlFvhmwWLd09p5rL3AkvZFwcgkTLdjlbhWiWeV+FZJ1yrpWSV9q2SgEybImeDDKcXNxqo6tEqsyXjWZDxrMp41Gc+ajGdNxlMmgxTMaWOPZWEQUdf3S87zXevbCYWdE3fPvQsSg15ZSjBJLGXvTRLRa2z6hI5DBhJD3V9lL92WvZTqX9XLGxpBP4RNhQqmttprbLUjOFBi0SIAz5KUnrXOnrLOXrfavcvZPyMXCew6Sq3h4lp7F2pN0YNv0Y1v0Y1v0Y1v0Y1v0Y1vMYFv4cC3cOBbOPAtWu9aY+5aY+5aY+4qY3Y7qFFMkkPGkrgiSzRmItRwjsC+AoVu6dYHTWwsE8C3VX1R/chZtKdvR4efYEHjygiRVGhhtDdoRZeNGrZsbagMTKKxa9YZKXUGEoF/nLYcBtKrpUP9d5idJ3l2ROP8QZ26h+qUPn5kpr0sY+FZnqHh+RrPhDbelEX7CUxbSGaxAj4I2p/qvv/0LiApmLBxCsr+RamQD7H6EZn/9G5cLRruNhEJ6HkSjVE6i6IX1VixCGaZhRlipebzZ7VlVnSGM/7pDdrJIBni7Kd3tFL563XIe4oNoKWjJHPG1MHxUgsd1vdb4WO56ryQ0LH3U+oo5x+f76xjew069nKzf4QHk4ZNT0uJtF0JJ7Tu/z5NsuTJf/nPsNp/+QdKRSlPq/p/D9WhfGeavn5YCeTzYgYYkSgddNudpAAaZjTdCRE03cGBbCVsSuIwndGtKYPdYfv9fFp/AvlgZiwyM2qMCssxULIjyKBSW3QYmYPM5+VfWJj1Fggz7NUcn7O9vS1pIoHdNchTLuLgJ+juhL2On2HZI3oBPPY4Ow/Tn39Jz5OPB+HFo03U/dKywRPe3ubPvxTbRBLPkjylyYXsvbekTX5kiCBnUc5r+yDazqG9po40YVyL/1sJZNu6VI3Im1lfKr/fM5NTUeWEgXdz20xXolX4Zh3aVeEtzI5a9d+q8S9lN36L1iDuX6B7JM8SNiM1ln+/c6N1fDArS8RZ8+57LVmqlVnZV70SqlmpLp5xbOMt0Nv8lu6aRQ7MG1is8pgIVmgGlulzkkNHR3mUhXXepk7N8O/ga9B75R2eJkFkeiUrzdlQgnQ9aVTjIBJEFQFmkZ9KGWupSuhFIpUPmeZM+K1So2vVXYgenDhpA1UKhSZCs/QxNBVAf0Sqfl1oj6mxKK1t5xGOt6m1dmJe8IRsC8yCEBg0OCeZQe9us7CSJyYuju335gbdlgSNYlS2Vi2J2lBr3u+rI6tvq92R76ocdD2LaW7HL27Pr+UYizmQCANGKYPxpSAaym1pEWN0OMqaQPrWsAbGRJSNcqE3AciQ21ivPyRG+yMVa7czW26iZqzDh3LfXCWq+tarGEDV3pDx+MofxogvWslCU6+rmGtpffUkFpWBNA8vGF1Qu9L1JN1C68sBXJtK2iuDinqh6oIoaRVifRcgXwtZJivAvGXhO4t6TU1SGdhSXbKIFtmXfFd0qTaqIZNHajRAdFWEImOLQ+WI5GkIazYOcdfDKgMFxtVRUYHQVIFQD66XaqCqMqWVqEKn6gj+uCBqR8oqdS12W3YYXRUZpofoCLH2lrIUd6yqN9WL0LFa6llcqXmXdXQBhvejUAp41ThU1fOhjuB6AAt3PYurl7ldtH4Efqt+VNeNJal6lqTq20ipVHMNv3xCx4DgJI5JFKbUUulVUe1pYIYKoQJZlNW3sNPXKKuvtazvuLK+hbu+RVb9iqyqMpWufNxr5B7DYORIUMdc4Td6GyiyvjgaFpJGj4GTtVXkCpHYAOCpA0V5zm2J0yRPC+eVWts3mq8ax+McEl+aAFogFtdJXzaBqMsBXTwlKT0ITGoYWIswGJhVVHHZ1YT0wf6h8q1TahWyXB1NjWIxtIl6qHC611NlBtg4Ic1AFn4KotzakYaeWeCr/fmFDC71kab2uvbQLOYf9nVCzKoYgWPgvP3zcDKxN8+hKiA8sZe3hFRlLG4raT6fs3AmgE7oBEWZCaNuXsL4+r0F2MhempElhEeKJOGSjvOdCJYVtrfZqhmDCINpBtJIHwQWj25dUFcTQV3BJotr943aFDakxePQ1o2rAb+TszAKhZ5+uBTW3h1HqhTrCw0tfBbln/bPE5anbxiJ0wsQ46Q6MulLdVxdG9+VAzltD6/KtqJMWcqiqAroKUq6+norgmAP/uOH+aqgkMJA+aYNo1oryezH0vVe2j4SMrVA1YXzuHRuDzswaPC4lAtoGS6C0ljPL3tshBsZvYmzuuRjHCVkvAjK7dT31ginqQTF3n5E0yp6aaZuXQWYqkx5nGtaAvrGODnYG9jzGuC6NgWqVh5yFwkCOoYtPT+F9UyFZsI39r9EAP5Qba9vUa9qEBZFyj7mwRaaIqGkGTaYHpE5UVimBrUje8iq+VgUuXaRzVqetv13zcnyvjtK56p9WRT17CIbC56NBdVFUBSpJ+l9GzEmLjR7sSizJ+7bE/f9Gkh7cqpZaB5Fun11JPaUVXMPhYpx1Kgf4Wmz0lh8UHNMuQTWZPPlB5wSqmuzeTs4TTk1Fuw0zCxR29WsJb51NYJoJNrT+/hncmrV13bpgdlFHYR5LAzKD6hl/NbKMWElsi24vgFX4gwvBIC4CYBccmbDaYZZR+64e0FgL6dmzKLKAZNZXNk8HiAB+rVZFfdhgWgmbrkrvIjHqAjBuhtXfQoo7dSlBEMFBwaWsPE+GTPlaBqpfyx3DK4FmbW0xj2L+tvDarq25ysDo6chozC8c1pY+mJofWNoVjWtfZW2hOZ1A2BNzSgKjegIeVB9tHe8Z8FrZNNVz7Vrq5s6+pwwMqPA0FN6zBIQ8zWk2atR0tvAaacCyKSMTiP6Fsz+sX52XNR3DVKFfppATNEh5y6uj/1BosNjC6RGy28B1TWgKo4zjiU0KNu31A5OFSJFmXogu9BRo7UyXDjTZVDmZlGQUz0UWvt4PPAcxSPUkff6zFar842FnhyturmH1LlrNADzxE3WVm0gC0ajBE/tZCmYafFJmKev944sutGOOZAHqj7q6/frsf+cJfmcAn7DOiky0KwGTQo0AJon9yYnWAAjA8813GPCaAcpCuk+g60oZ7U4HroLSXcZlGkpaCPbx+BHUJ5BTbcANUoQrq5mIE0k+Mq0uC9JHPPV6C5DTSj09bk1gGpHAlWXT8P9GqNAO8rRmWgBgEYNgL9JkjPQXVKgnIMksKprtCAcM8shRgso4YjEIN9JWLN7jxZTwjIoz1hQrr3toXoACs8CGTWy5cHzNmA15uJIMRGQ65XQ2j9TW3fSj3o4JpsgBjWdqhYkuuwokwE06bNqWWxcaQKfi6h2kJ521KPyWwVeK6097YxHsEBrUM+auNdRfScwDB6n+5yR+XlhjmstdA0iDKMoL9IMHIAlF48tkF5Np6qSVoV0vKIf04hmmbVknn6001EpeQnQ0KBJC+KEjonJaJ5+sNMVpNwG0LWNZ8+1jGfPtYxnT/WVF0WW7ey51sGAZx+PeK7hfLaC57QhqyiCyhPyl1VlZKCenDF6EYrEElGSpihETCCvBhX2AYpnH6B4no0K+4zEs89IPPuMxLPPSDz7jMQzz0ie5+GYpjzma0bHoXlG4ilnJGuJ+9KvmbtaOLcZRXP7OBm0rJvav1kcTl9t/3cahQkXQI35aNSClvH8SkyMulncPCamv96llHJDKkCee0tULJzYD3nnoTksaEUEPdQYJo7zQtLdPLZMCShcEouptKv6Y5a4tO1gs+UebYvqV5gl46jElemPvhGqGoPwFjD/zd3XFjLsnDMrwg7oHvuvX705fPXnu+O953wEPBoFCGCWTg8ZU81Ltzj+M8sVr48SYV4fei5XV4hlVnNFX9z8hC3/jF/kOxG5WXR87o15/bFyucjXRoKzl+TVb5VWTIRiA3FW67AUxUrM/FDEOTgcspgZj9ayQyoXXIDy+XqVlVM7XFwGy3oyvj2Ok7g6F77BSCvIz19Q5pZ3/WCWk1OiOGbLe8L4JTRvUiwiICV8tyWtG4K/227Dw9FdlOeUp/lsRtiljXRo+TS7RJS/Rl3sjfghFAwUkj+Jkp/eTUIajXVHfRIl4uIWcsOYX7tCbFUgCZLxq1+Kq1eyOIhImopy3NmrcjB6kpkcDf/qKl/HYQpy/VJ+5gN4Fn7Cbpfe7BKzqA5s+fFTcTOuws8JRYdIGOMRx2epHYCVi34JxhKmNLe+HbeUUZVoUAPAm0Pw1Ts5wsIYVvCq9xXxdRFiTKHqBlDH5bnaSqtxvWKPFeAnM2JB+iakHRvkGXsMrCtlb0WLR3slkauRwBUzljdc07reDXxS64RYvcwMLLoHllzFcloQfQ2G1dtvyy+PmaHCdiywmM0Heok34muumoohfuF7pNVmqd8jhZ1o44DEqRMRh11fTa6vGAVZ/NiJNkNEcR5dXzlJDj+Ts/eUX46HmoG2i3y+VdqAQvweJVmqnRGWYaxV0HyUiNOVgnomRcE9QGpPubWq38ol8zlLgvPrq7vdXDUiyuknEtTR/Neeu35jl4/yjvPW3andkl7GtOxHOIqw3qjwp87EtlxVgz+qau7C/AsAyqsBvc/L+Jri2FAR34W2okS4iWxPRayrHqODcggnGoWWymUOAfr5e3yWzn8R/Y64SHxFZnTvyDCLOvUy74Bk5P/Qy2e4o8vkKeFYNMYVW+RZG1LVFFBCvOExVlJfEJlLPU4rVflP785ArFaZDfim7pt1lPxTvMLIrHCesPBfoEuQaC8Kp0W1rlktDUCRip4SJpULd2jW+MjIXH6rV20qudxSieHoJwEuHM1K3l6vSqNvUD/CshQ7sURYddv3Syqc0P/BZUxmGCrfvG0Vwdg1dyC5h9qWADfheWy+0oOQ92vsqXsh6mtk5U1FPW/0RUZnAoUdMcnXyHlvMKAFP0mLBR0SP5WFP70jyvRfyRGp3zOJXh8Wd2try3mTYF0H1AxHDjwtR546UINPa6A3AlZELua7u9Hhf4/0CjQmoD+Oy9Pljv45xeDPTHxvQEUIDax04pizd/+czM5A6qPSBr3OYMeEjRl0uM0X8RgdUSF1Hu3vv/jZeTToDXtDZ8s53jt5cers0zH95LiDn5diBfc7b7ZuvHirxUsPSPQwg+GhpxFjcjl2TkmMtAy4Obq+GodBspUmQUjyT86j/qjT8QEzL//n9atGfHwaBOvGh79afLidkYUQQEMlg7jGvwkSgsJWwCs4aT4HMyEE6/KxtAdYpfcJEwGMhzi54PYBElen00PiIixMG3E48c7XjcPuanE4gLEc8dDL6/8wKuZPpiwMQN3KWYUTEoWIvxIrfndUslxn4JweN+LmrBuvGze9FdMXMpyJHOCxXFx1cWL+F4lKLBHnPc1jDIPg7GhA3pEOB75X0GEh5HrNBNmZrhvp/RUTZN/G+WHOkrnES+rsTSY8nMW5vsoYiadYL+UY6nRKDAVtMRQML9aNocGKyRI7M1EUzmbhVERnPC6oB7YFvYikoSTW3BlfX13QKJnPJT0meOoIaBUib2AgstO8n37oROtG5HC1iMRExCYeXyDWOF9yRLjtZf+0v/b5j1Y8f1u8EWdfCn6cve+bEqdZ4PTer13bXLGe3bO3QOL8ATpmCConx0PXlLydZkSM/cnaEbFivdtFu9XCBL9SxQ+AQOCie2WKhgdKkUpmbJmbHdBRcg6GCkKhzH5DGQuzhIvtRyOv02vPV+NutnZErlhR7w4MPKYOYbPrK7FN9Toj01pp3qamg7Vrlu6q1XNzn8qRli5IGMnt6HA2j5Kw0H/K0DXnmCUTUKJCTCcblRvWQUiA9oDy8NY3iRCTo84tVCI6ZGvH5IqVdLejMaa0a67/N0jiZBaWitGzMEYvfirRu8ePSYqPIlJbnhwEghAHlnz3GtFH1q/GuyvW4wcjHXulfZxy0/mxkxVkqarv6TxhWcrPF/xepVi2M3bOh+vfBlesePeg/DUaJeLaomRRsPgAA4Nut4tCnrCYbkVk6y2JIhBnkmqaiSb1xmtHx4q1bEz8epzkF7BnaUzT6Qxbb10f/U9rn/aqdeIupvIWr46h1D1mFCQMc4rrmYZ50Lx7e2s3r9wVq8UDGPafcaG5iKtB8rRtkzvtU+e4pIe7ckY2ulwvfj7rFzqFH/BFrMcecyegN9DqPL3cRxeF/dCZ9HCqgYvo2bMqF6EuRf6k4xxaJSktPQNK5iwziRgPkbi0INqFSqhRFqc0j2jVtuytr/cWxkEENs8BTQMaj4kVCVa4evbPafChWBXF2TvQmxN3UqzBK3GGP7KTjN+ziOkXdpRBi694r41eMszGkl3OTT89ElrlUr0Bcaj3jbq1TfML0E1tqlE+VaqCB3K65z5XsWBqMmrxPiLloUqmYNISCGIKChGv8QZopjY09bDaJnAbUF2SvXV4JL3SIyn8kUjMuC3KcX6PzkgkBRk2AxPFfOBlUO3yiXrfmHcRGb+cKDXC5JZP1b/jVN2mqa74ZEJf0wKqxUS735h3D/s6ZgkPftwkIuB0+QxHa9dG61Kf8oDKIkOGJe2K7a2UbTCBNQi3fr1wow6ODN1+7LsUcPjGisgMs8VTw4A14zqn11cyzEa43GRbwu/2KDlLk+j6Pxldbt7AgPy1Y2PVUhBFw3xOI2CYhCdwdJL8grJGxnG/seAJ1M+MibLivsFX39pWLQbFnZYgwXOtdsu5dkm/6liFoTLF1gvZ+8aCA1ABlem6UTAfE5bBaFlIsuq4skkkuWsXSSs+icSxlM4PNEGvr8BqDCdNizv4xtzx/GopnjxdXznx9dU0CcLrq6ZJDr9Fn7s5SdhyD66vAgZq2imsbhBOuIsVyiYYNPSVdbbVe9xxrMeVUkXGZJ7xmybrNTO+uEMdB6POU7x55viNImrtysSqPd5Yrs40z9jy5cRZrn33WbVHu9vTZgkmcpAzhgfN3E9NLmhQMHV173ghCrq4F3XWby6sWJnq6swrX5wlpa0wE9ItddJKlC1HBNoJ6+ftFStc7dGQn6XXV4AEnqepAQ3rZ/w1aGSnl+hZn1HuH/srD2W0kDgk/StvFAT+mu0J9dBBT8qBqVqnCat7slc9b+iu47ihY93ugNGg8IDhfJfXOmAqPP4n//TVbelV734wZpECDkMnl3P5lzCfV7ztDauQgK+son0uEmqWz2We0FmYUnwEg7OwcsVfZLNWC0ofcyUODEfwWGvxNCN4UGC4aNTc5JgoTOTKOKiHXHxb9XvMa6EgVn1vZmAi9lAkD9TQaqUp6Ck+MzysqkX0YZmG8IdCc9OjkmW0R0ayPDPz5qjvY+mp+lJeX9siRZGS1eIG78mrD2QpGy/SQ5KfRVWYSIsnNxnmLeKa9v8FhIgrqEXgiot3xU+EU9Ws4RahLSflc4aL6im+1WF5IZ3fUe1JYbpRZnMSAkxeMi2RVZQGG7+EXJYpZe1ulY6E2rGf5EhnIUeA5KFwvAcSOXmZwQxOyDisUtwhe9RMT610b+fW9D6vr71BZpKypz7U3bVEiBpVJGKUoA0RP1wX4qPqfP4adD4MWMIXAiYiVUnj8ey3puHBWr3GZ6q/L+3O56FDX1mpU/bWZe8V3u49Qvs5QoENkCdRSFkKdi7YSQ8c88Ax3ybHqM9lAkAakgaD/4GUH0j5npKyokfjZab0QSg/UPI3RskwktPTfSwbVdq7+hYxhmDrunqRAubwrzzEhB8P8vuB6r8xqpdk3rOU7qVPaHctVngRp/j8B6ZJzcKLMKOH4hpobbauB6Z4YIpvgCksa7Vfd8NlcWLhgWXKDhRTFtEkeOgwvggZXivnuYwedpEHhvk2GWbgWfSu2AVe4SCTaV7lbdXTEDO5TkJa3A8qYa2D04GyS+Hh8mxJQw/888A/3xz/9G3KVRKbdocwmpebCX9Yzjk6PnXKmE3BAk7BAtdXG86cshnNHMwoQav3gvkdKgKUiq5PBl8cvBspg8QizC8V5zOg4cQ5fXFy+OYx1uD3S6+vMhJnTh47eNUsj3iCnTm7vtpiFDvl0UVj2MR4PhnQ/eK8yAiivDwNA992XkROWvAq9Ik5v+bX/y+rIv7FLQ/YEUUuUZiFnAw0B2MV7xQyHpwyD6//gzkB3mNiJHHN+wJ+/pVvEhjcf1Inra6MzAnD0cg0/jJ7wvavZ8zZ+U3+SZxzRidPNrZ3xkmQ7iR5FkbpFk3ZltdxBzs80/vO8cu9/cN3gP93L6p7d+m76u769nw82XAyfKM+e7Lx7iwi8Qf4jblen2y8DAGlsKKUxXTjt8PYSQlmc3DmUZ7+ukN+w6y1S7WLJteUJ8XsUwy6p/sJ4B2M01h5D0dSm3o/HFo6q6vfxg2KWUtDmh9+okFeZuTuKB1ZetTAOvUfqk8u9NGrmJ5KHnpOk1ee/hVodNFX9OJSNDxEGkTzsydeVnkpxuva/IZBM6/IRTgF/uC3j6GoM9zdeE8uiLhBvztP5n/OT2l2Gv6LPtoM4zH9tD0/n/9jTqb0CTDLLI+3sYNPJUb28UbA3xFJn578vZANTzYfbw57nfkn/Lsj/r6k6ebPReJizFBSP0uJM7elNoohZQnw56s8Sw0iGKrI6Zbe3BM6KSpzCQs4B2RAqeaXD0iqFSF2A54kfnLMwjhQvrhyCPDplKofMLyFTsIYCG9MOVQ4F8/QVr5+jnalp8ZAg4IFDpIZgabTwiYVWZGV2SvqCYZgjEX9onrlyu/oDaoVegXVAMkW/ek4lfEU8qM+d/mc+RlG6ioVxDNH8D1jeax/rtFulD1C3DuXjWdJmB4nYZylKKie5lFUx9M/6A39+57wAZeCv1RVDFl7NUx7jJc/RMVpJL0JkchQMfmyT8JqueUHjmFq/+ZUmaPGQuHLQm6pUkcxbLy+KnMN8XJv5a8qvP6Zk6hMsGMJWDXUxccX8pTK+IkH95S5eP5pfua5eHBPV+SgqKRPYYmkK/UJq/FqWNGClv1SYpif9EdDrDfFlj/c9yBxb/ZuiKBr/tTf1lmU8AQnX0YG48ONQFDV6gsdsVsJXJ0wpPJRilSL5h7EaQtxii1yebA3ZSLHmSlW+pqAIla18imsooVSmOCkFeNC66FSBPmbIxFVoVuoXB1VEK1utb8tvaVJMgpCKNinFNc3RPWgso2sJXyQvd8a1dxaSrAqrHj/+G3F/kPlZbO9CQjgc/ms6KBGhb536lWqvLULYEfkfakqFRPbn1/wlR4YLLaQdXgiBny1BtFU8amnAfk/wqYksMvfKi3fmzXwYzxjBzP4NQW0yyO8zaLipsMH/mQzwIR4W2HM9326+duv4WzqpCx4snmeZfN0d2fn48eP29JdsSXTtm5Pk/xie8J2snOQX+kOfyE63ZmHQZZsiRbjJNtKPmxPw8mmQ6IMej4ns7mTAPyU8Lzlm3hquYOD+01dVv9hWb+vZUV0B/OLUoKo5k2XfwK5k8Q8772hy8C3PXaWMJ5GNKhxLn/9x8+gu7c5FV4BessXLrvm098jgRbZ5v2bdF9Mmmhrc4epK6Z29ws9+N1T3tpucQ4t3i8Gxe+Ub3lyx5YHIZ3iW5KzgFo1ljYtVjvCTXRfHkK4xQlqzQ0kjRru4eWjtHrfei+iTJwz9xXzx7pYMVQvNvKHSacRBdXr9Rl/mbshosRdg0fckxfJVa/4Pbg+Xp56kkIrFBgGXO1HCd6TphZum4N+OoWbBbibUbwLqm2fwFcdfc3Li8K8nuIrG7mGqTtqy1yyum/Cd1uOXlbv6SyJbuKjEEOUXqKf2L5B7Bl6JsrVTn8H/vU67qgo+ZKS1pcMncSwvrFQA5CK03w+Z+GMskPYVwP6KDsPU/S1pXWPeCMJPXLev9+ZzXYI/OP8LFWRUn77Iy4zqBOFszDjqTYYvwDMPeHzKOTvYjL0cN/pzeZRv1ZcVeYo2Crw+9YaCegSoDYw1Ea+2moN+GrBWD+I5cDjdHSy7svB8bV6PE6CHA397SnNDiNu8z+9fDF+tInG3Lv916/eHL76893x3vPDd3vjC/Rgy7fi39VR8ebPjzfHY1zfS/hn8/HmpiAGfEwkKklZjICFgipMpUX9eqslRseBcoO8mdO+fy1ekSX4/vdL6ogYC/keDy6lQ9PMKdTyDVV6qSENQ0t6mdfyF8gu1/vuZVe/UXbR7M6Ca/jjCa7eegUXEPD9EFu9RWKrlsUehFad0NL8xjoWYYEC+UCImjtmpJhnfWmeqelIBpbEkw21VtuE6Bt+z6KvI0UfrAyXeGUI5B21tWJB3B9O6qEPY51STyXieyH+/O4i8bec3x7kYL3yVrCOEouIO0w9GbRU4r5/AxTFfb0ku4vuVqyF/+OJMe+LiLH7osMVC61ecPYa5NqDdtdaqjWc1hXY79kanZH87wO9/JgwSVNPkwwmeA+9GX0PhVGcIj7Y9ZU48A3oY/Hie5jl0fWVk+TwMzl7TwvM6a/53IqMyzjEoyRL9yOaaigSGwn/Xr5u9ixKclrV6hbx2xO9/J4hGLsrp+CQOb7+cs6zk9+J+9UDE3yd8BMJsnuLA/Q1VDjgY73r/IdVqeJ1eZoDBuJyo8DysyTP0EcAbLg0zgJ1w8PJBPZycUEpYGH1OjIrBq/rPSO7xdqLNjwwDbiKnVTtLBkKssZLXp9zpdq3DKFS3tU8hkZTobN6MuKYO/s6loBSHYBdd3fZ45zojDspXv2gpWeiaEjV94a7xludNqyUxgW0erUUZp2wKX/TsoIRY7I3rPoHOnWXGFD26n1iXU/4xKpnjGL6LydHjQ1lJEhIrjvhIwtf31O28juk/T6+HElmZ5IZiuQp/CnwzRfxGDZv0GecR/v7L/j71sPe0HyRftD4cm3kzdaNlxXfQu0B6R9i9pgwTYsXfalzyh8BR9wcXV+NwyDZKt8H7486HXwi/eX/vH7ViI9Pg2Dd+FhxXmeQhxZCAA3A3yQO01lxqxSfIEHVFCs4YFqBxhHSnD2WqgWr9gn5Ck8Yx8mFyITPX4vutX4teuKdrxuHK34NYdBR35aX13CnLAQlHn3cJU5AXeU5Jwqs+N1RyXLtnpQ/68brxs2q36RChjORg+8liH3HERdHwbxySj31PQUhnaa8xIS8Ix0OfK+gw0LI9ZoJsjNdN9JX/CDDoG/j/DBnyVziJXX2QGkKxe3vjJF4KpQmxFCnU2IoaIuhYLj2B95X/J6Wi52ZKApns3Aq7s0/LqgHX2PQikgaSmLN8S79BY2S+VzSY4IXUfCqPhd5AwORneb99EMnWjciV/xgF2pZJh5fINY4X3JEuO1l/7S/9vmv4y0vff7E2ZeCH2fv+6bEaRY4vfdr1zZXrGz37C2QOH9gmghQOTkeuqbk7TQjYuxP1o6IFevdLp6oWZh4AzJWvohzfYXncFNud1BVZmyZmx3QUYKvBxWvCb2hDMzbRDy/OvI6vfZ8Ne5ma0fkihX17sDAY+oQNuP5ToCcep2Raa00b1PTwdo1y1W/NmbtU7nD388JI7kdHc7mURIW+s+zIkGKc8ySCShR3OqNyg2rfEJSJBJETI46t1CJ6JCtHZOrfv+1ozGmtGuu/1ckTiwVo2dhjIc4qUTvnkhmIz/iUPGhJul04YQ4sOS714g+sn41ftVPnQ1GOvZK+zjlpvNjJyvIUlXf03nCspSfL/i9SrFsZ+ycD9e/Da5Y8e5B+Ws0SggmPUqrB2kBA4Nut4tCnrCYbkVk6y2JIhBnkmqaiSb1xmtHx4q1bHRxHSc5ZlzSmKbTGbbeuj76n9Y+7VXrxN3qsSyUuscY7keZIxNzmeZB8+7trd28clesFg9g2H/GhebynCX5vDhtA5l6TuB/jkt6uCtnZKPL9eJHxKEUt0SPcwYzSCk/ALcPzn310F319+K+iSfPlw0NGH6g2vPzz7JBcZ21oUHjKF/xgiLKGJ3UneLrXgjFTYpeXhIE9Wf/OtRgeezIIo/Jl3d+FdfXlQiFLveSgxkXOxeUYfY8doDKFC3dQdX8H4mIAs17ttAddHMHWtNFr+Ka8t7+fsLG6T4Zy2uJXZUUh/b62BGOXumBanKPFVkj9vden9AsyQtHXdWUmoXAH5TZt2gs6h8hBUm0pM/IRQLGDa0CFpDdoraViy49BWvazfNu7U10I1uCkDIynQjQzmk+mxF2aRPx9x+EgbN4WyKCy28RtlPihwsbeV3WxfwQDNiEXwjWYzkMBPNpy4UULbTNr5aS/IJOCRvTeodswcP7eOtXEetf24+OW2jlRy9ncftoAiWXgErGfuVB76vYqi416lfUvz6qaqQu6g1hKsd1ep58PAgvRGRYcwQY5g5CpIkJb9oyGUZbIOPGiF8aWOT5jVKzupOrD7OKOi/aUlQGZDeQfulLmv6B+UKb6N29D/SOc3wDg+YBGe/FsO8SNeL1dPmc8ceSASWndFH2va+PgxHHQfGoc1qO9E6IUGPV8LYoTQnmrqM1MTJfHQN46/fg+qoYoQMq9zxheNAp8g7dCRFKHFVRNNRxI0IGf89mNdfP7yPP8MejeZSjGPPtovPcYuK4gdbZEl99nl45TznEW6vDliL2veaXqhSnleiIoimZ53QX01TxfXI1amPtnqfpbm+LZH0/olatGr+DMiFOmRHHzktzP1PjpORigQ5uBUUC2g5jRqf83IuJJobFscUfCn1XWaTphOSYXMQ8wbh52rImZcwtg5UxLrrUSFFIMV5UnWt4LQ1IRTdX8n6rsZeyxLdKujqhFBq92m7XLOiZBaqKUGYv48/evKRTGo+pObKeNQ7rKSp3oIysjAB/lcj7Gc+SPNakPYIMVZBiIBIgrSCUNCvqYYQo8axsOJ6VDcfzdBEZJxjxKDuyKqsJyHka5zSjx8AVmFv+TTK3SVkLUl1H3pbdDbez9HiTm8B3Ott0l0ullUeeApi3fE5VjS/xzodqUJUviT9NeND58yRLkADk4ncrctSsjg5/BEGpaBKc/QnVXyHJRHi78lWIQARhOgymtEEEkPzEED8aFRtXVjQqXnSb4IGQvwNCVswe1GrrCNm4aSMBhwbNClqu6lY0W5Gz9XVkULReQe6nnKqtL0pCNCVZkmdJfN9I3PQ0i40LRrKea2xjvrXB+tYG61cbbDmkodlwT51PkZr4j/I1kpanlYDjmciK+DYkY5qYu7R+7p6z6Oj4sCoa8SIT0uXuo4vkkjLrE38wY0pfJmRMmTnv5UnlKmXstOXkuhUET52vcuByl8GgSARG2VESgxEUUvuwmONmdGNtyzoJvbXeZGTvW5rfuKUiKvJm47MDf2bkvKS5KvM2utOKT+UlKHwE5y1lqZVAX9LWIWNUuF1KtVXczSsScioeRE7Hyofm9WpKAagSl2csl1/9rl8tJVe1f+PhtEa6Su0i7dv9IClFfPPHj3LuZDnKozCCmeTV1a0hv4I2RbvNQGuV8XuAdynJ9MVsqvGvb/AvHrycSGP1plb2jU8gxEkIDj2HsYOxmWAYKA6TbolRfBEflot0MpuReCw8TRwLEyav5ClLf0NEHr66D4gUo/h6iKQxR+St83pKNVT85/L/Ptt5Pr1fVpDn02uSZwuFvNteyIvNXBy2LBQfdzrEaIQQCcr9oRARf1OdZX/TnWV/4/evixm2guA3gAuIbrcNBEa70FOaV7jseS3gwjiIckYPeJ7eMQ/HLOD7bfrNLufV+dQhHwRG0PCXFWlWjWVwu7aQEstGMK91YyN64vy/WTmibzAeu6ki5/INFka7fH+TBVUvrN9gQWtd97Lf0Y3gC6epBO61WUHNgSpH3WkHaDoaZbdt1lz1zt0ATHVc3WRFVbeP1NE+/3+4ByLA");
						params2.put("PRADO_POSTBACK_TARGET", "ctl0$CONTENU_PAGE$AdvancedSearch$lancerRecherche");
				
				//		params.put("ctl0$CONTENU_PAGE$resultSearch$listePageSizeTop", "20");
				//		params.put("ctl0$CONTENU_PAGE$resultSearch$listePageSizeBottom", "20");
				//		params.put("ctl0$CONTENU_PAGE$resultSearch$numPageTop", "1");
				//		params.put("ctl0$CONTENU_PAGE$resultSearch$numPageBottom", "1");
						// params.put("PRADO_POSTBACK_TARGET",
						// "ctl0$CONTENU_PAGE$resultSearch$PagerTop$ctl2");

						String result2 = null;
						// send POST request to the site to get the HTML data
						try {
							result2 = HTTPRequest.sendPost(urlMPI, params2);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// System.out.println(result);
				
						Document doc2 = Jsoup.parse(result2);
					

						// get the links from the HTML data
						Elements eles_links2 = doc2.getElementsByAttributeValueStarting("href",
								"https://www.marches-publics.gouv.fr/?page=entreprise.EntrepriseDetailsConsultation");
						for (int i = 0; i < eles_links2.size(); i += 2) {
							Element ele = eles_links2.get(i);
							// System.out.println(ele.attr("href"));
							listLinks.add(ele.attr("href"));
						}
				
						// get the titres from the HTML data
						Elements eles_titre2 = doc2.getElementsByClass("truncate-700");
						for (int i = 0; i < eles_links2.size(); i += 2) {
							Element ele = eles_titre2.get(i);
							listTitre.add(ele.text());
						}
				
						// get the date from the HTML data
						Elements eles_date2 = doc2.getElementsByClass("date date-min clearfix");
						for (Element ele : eles_date2) {
							listDate.add(ele.text());
						}
				
						// get the location from the HTML data
						Elements eles_location2 = doc2.getElementsByClass("lieux-exe");
						for (Element ele : eles_location2) {
							listLocation.add(ele.text());
						}
			
		}
//		System.out.println(listLinks.size());
//		System.out.println(listTitre.size());
//		System.out.println(listDate.size());
//		System.out.println(listLocation.size());
//		System.out.println(listLinks);
//		System.out.println(listTitre);
//		System.out.println(listDate);
//		System.out.println(listLocation);

		ArrayList<Avis> avisList = new ArrayList<Avis>();
		for (int i = 0; i < listLinks.size(); i++) {
			avisList.add(new Avis(listDate.get(i), listTitre.get(i), listLinks.get(i), listLocation.get(i)));
		}

		/** for the 2,4,6,8.... page **/
//		// define the map of the settings
//				Map<String, String> params2 = new HashMap<String, String>();
//				params2.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneStart", "06/06/2019");
//				params2.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneEnd", "06/12/2019");
//				params2.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeStart", "01/06/2019");
//				params2.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeEnd", "06/06/2019");
//				params2.put("ctl0$CONTENU_PAGE$AdvancedSearch$procedureType", "1");
//				params2.put("PRADO_PAGESTATE", "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");
//				params2.put("PRADO_POSTBACK_TARGET", "ctl0$CONTENU_PAGE$AdvancedSearch$lancerRecherche");

		return avisList;

	}

	/*****************************************************/

	/**
	 * Crawler for Auvergnerhonealpes
	 * 
	 * @author ZHI
	 * @param location(departemant), key word(intitule), the number of the pages(pageNum)
	 * @return list of the avis
	 * 
	 * 
	 * 
	 * the list of the location:
	 * example:
	 * 02!;!59!;!60!;!62!;!80
	 * 
	 * there are the "!;!" between the numbers, the numbers are the same with  Marche-publics(info)
	 * 
	 */
	public ArrayList<Avis> auvergnerCrawler(String departement,String intitule, int pageNum) {
//		// define the url of the site
//		String urlAuvergner = "https://auvergnerhonealpes.achatpublic.com/sdm/ent/gen/ent_recherche.do";
//
//		ArrayList<String> listLinks = new ArrayList<String>();
//		ArrayList<String> listTitre = new ArrayList<String>();
//		ArrayList<String> listDate = new ArrayList<String>();
//
//		// for the different page
//		for (int j = 0; j < pageNum; j++) {
//			Map<String, String> params = new HashMap<String, String>();
//			/*
//			 * debug: jg orderby: 0 page: -1 nbAffiche: 10 objetRecherche: -1
//			 * personnepublique: typeLieu: passation region: R01 departement:
//			 * 02!;!59!;!60!;!62!;!80 procedure: -1 marche: -1 intitule: informatique
//			 * reference: codeCPV: jour: 07 mois: 06 annee: 2019 precisionDate: apres page:
//			 * 1
//			 */
//			params.put("departement", departement);
//			params.put("intitule", intitule);
//			params.put("page", Integer.toString(j));
//			// params.put("jour", "07");
//			// params.put("mois", "06");
//			// params.put("annee", "2019");
//			// params.put("debug", "jg");
//			// params.put("", "");
//			// params.put("", "");
//			// params.put("", "");
//
//			String result = null;
//			// send POST request to the site to get the HTML data
//			try {
//				result = HTTPRequest.sendPost(urlAuvergner, params);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			// System.out.println(result);
//
//			// parse the HTML data
//			Document doc = Jsoup.parse(result);
//
//			// get the links from the HTML data
//			Elements eles_link1 = doc.getElementsByClass("lineTable03 fixed_grey arial_11");
//			Elements eles_link2 = doc.getElementsByClass("lineTable02 fixed_grey arial_11");
//			ArrayList<String> listHTML = new ArrayList<String>();
//			for (int i = 0; i < eles_link1.size(); i += 2) {
//
//				Element ele = eles_link1.get(i);
//				// System.out.println(ele.attr("onclick"));
//				listHTML.add(ele.attr("onclick"));
//				if (i < eles_link2.size()) {
//					Element ele2 = eles_link2.get(i);
//					listHTML.add(ele2.attr("onclick"));
//				}
//			}
//
//			// split the String to get the links
//			String[] ListString;
//			for (String link : listHTML) {
//				// System.out.println(link);
//				ListString = link.split("'");
//				listLinks.add("https://auvergnerhonealpes.achatpublic.com/sdm/ent/gen/ent_detail.do?selected=0&PCSLID="
//						+ ListString[3]);
//			}
//
//			// get the titre from the HTML data
//			Elements eles_titre1 = doc.getElementsByClass("lineTable03 fixed_grey arial_11 align_left");
//			Elements eles_titre2 = doc.getElementsByClass("lineTable02 fixed_grey arial_11 align_left");
//
//			for (int i = 0; i < eles_titre1.size(); i += 2) {
//				Element ele = eles_titre1.get(i);
//				listTitre.add(ele.text());
//				if (i < eles_titre2.size()) {
//					Element ele2 = eles_titre2.get(i);
//					listTitre.add(ele2.text());
//				}
//			}
//
//			// get the date from the HTML data (attention: the date is the limited date)
//			for (int i = 1; i < eles_titre1.size(); i += 2) {
//				Element ele = eles_titre1.get(i);
//				listDate.add(ele.text());
//				if (i < eles_titre2.size()) {
//					Element ele2 = eles_titre2.get(i);
//					listDate.add(ele2.text());
//				}
//			}
//
//		}
//
////		System.out.println(listLinks.size());
////		System.out.println(listTitre.size());
////		System.out.println(listDate.size());
////		System.out.println(listLinks);
////		System.out.println(listTitre);
////		System.out.println(listDate);
//		
//		ArrayList<Avis> avisList = new ArrayList<Avis>();
//		for (int i = 0; i < listLinks.size(); i++) {
//			avisList.add(new Avis(listDate.get(i), listTitre.get(i), listLinks.get(i)));
//		}
//
//		return avisList;

		/** for the website  decocher "Consultations du conseil régional uniquement"  **/
		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();
		
		/** for the service **/
		// define the url of the site
		String urlMPI_service = "https://auvergnerhonealpes.achatpublic.com/accueil/?page_id=143%2F&departement%5B0%5D="+""+"%2F&typeMarche=2&intitule="+"informatique";
		
		String result = null;
		// send POST request to the site to get the HTML data
		try {
			result = HTTPRequest.sendGET(urlMPI_service);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 //System.out.println(result);
		
		// parse the HTML data
		Document doc = Jsoup.parse(result);
		
		// get the links from the HTML data
		//Elements eles_link = doc.getElementsByAttributeValueContaining("href", "/avis/detail/");
		//System.out.println(eles_link.size());
		
		/**
		 * 07 - Ardèche
		 * 26 - Drôme
		 * 43 - Haute-Loire
		 * 63 - Puy-de-Dôme
		 * 01 - Ain
		 * 42 - Loire
		 * 74 - Haute-Savoie
		 * 03 - Allier
		 * 73 - Savoie
		 * 38 - Isère
		 * 69 - Rhône
		 * 15 - Cantal
		 * https://auvergnerhonealpes.achatpublic.com/accueil/?page_id=143%2F&departement%5B0%5D=01&departement%5B1%5D=03&typeMarche=2&intitule=informatique
		 */
	}

	/*****************************************************/

	/**
	 * Crawler for Ted.europa!! Attention : we can't solve the problem because of the pid and 302 307
	 * @throws Exception 
	 */
	public void tedEuropaCrawler()   {
		// define the url of the site
		//String urlAuvergner = "https://ted.europa.eu/TED/search/expertSearch.do";
		String urlAuvergner = "https://www.e-marchespublics.com/TED/search/searchResult.do?action=initPage&pid=searchResult";
		//String urlAuvergnerExpert = "https://ted.europa.eu/TED/search/expertSearch.do";
		
		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();
		
		Map<String, String> params = new HashMap<String, String>();
		/*
		 * chk: 'Call for expressions of interest'
			chk: 'Periodic indicative notice with call for competition'
			chk: 'Qualification system with call for competition'
			chk: 'Prior information notice with call for competition'
			action: search
			lgId: en
			quickSearchCriteria: 
			_searchCriteria.statisticsMode: on
			searchCriteria.searchScope: CURRENTLY_ACTIVE
			searchCriteria.freeText: 
			searchCriteria.countryList: FR
			Rs.pick.671668.refDataId: COUNTRY
			searchCriteria.documentTypeList: 'Call for expressions of interest','Periodic indicative notice with call for competition','Qualification system with call for competition','Prior information notice with call for competition'
			Rs.pick.671669.refDataId: DOCUMENT_TYPE
			searchCriteria.contractList: 
			Rs.pick.671670.refDataId: CONTRACT
			searchCriteria.ojs: 
			searchCriteria.documentNumber: 
			searchCriteria.publicationDateChoice: RANGE_PUBLICATION_DATE
			searchCriteria.fromPublicationDate: 01/05/2019
			searchCriteria.toPublicationDate: 01/06/2019
			searchCriteria.cpvCodeList: 
			Rs.pick.671671.refDataId: CPV_CODE
			searchCriteria.nutsCodeList: 
			Rs.pick.671672.refDataId: NUTS_CODE
			searchCriteria.mainActivityList: 
			Rs.pick.671673.refDataId: MAIN_ACTIVITY
			searchCriteria.documentationDate: 
			searchCriteria.deadline: 
			searchCriteria.place: 
			searchCriteria.regulationList: 
			Rs.pick.671674.refDataId: REGULATION
			searchCriteria.procedureList: 
			Rs.pick.671675.refDataId: PROCEDURE
			searchCriteria.directiveList: 
			Rs.pick.671676.refDataId: DIRECTIVE
			searchCriteria.authorityName: 
			searchCriteria.typeOfAuthorityList: 
			Rs.pick.671677.refDataId: TYPE_OF_AUTHORITY
			searchCriteria.headingAList: 
			Rs.pick.671678.refDataId: HEADING_A
			_searchCriteria.statisticsMode: on
			Rs.gp.6158052.pid: secured
			Rs.gp.6158053.pid: secured
		*/
//		params.put("searchCriteria.countryList", "FR");
//		params.put("searchCriteria.fromPublicationDate", "01/05/2019");
//		params.put("searchCriteria.toPublicationDate", "01/06/2019");
//		params.put("chk", "'Call for expressions of interest'");
//		params.put("chk", "'Periodic indicative notice with call for competition'");
//		params.put("chk", "'Qualification system with call for competition'");
//		params.put("chk", "'Prior information notice with call for competition'");
//		params.put("searchCriteria.documentTypeList", "'Call for expressions of interest','Periodic indicative notice with call for competition','Qualification system with call for competition','Prior information notice with call for competition'");
//		params.put("action", "search");
//		params.put("lgId", "en");
//		params.put("searchCriteria.searchScope", "CURRENTLY_ACTIVE");
//		params.put("_searchCriteria.statisticsMode", "on");
//		params.put("searchCriteria.publicationDateChoice", "RANGE_PUBLICATION_DATE");
//		params.put("lgId", "en");
		
		/*
	action: search
	lgId: en
	quickSearchCriteria: 
	expertSearchCriteria.searchScope: CURRENTLY_ACTIVE
	expertSearchCriteria.query: CY=[FR] AND TD=[I or M or O or A] AND PD=[20190501 <> 20190601]
	_expertSearchCriteria.statisticsMode: on
	Rs.gp.6531439.pid: secured
	Rs.gp.6531440.pid: secured	
*/		
		
		
		params.put("action", "search");
		params.put("expertSearchCriteria.searchScope", "CURRENTLY_ACTIVE");
		params.put("expertSearchCriteria.query", "CY=[FR] AND TD=[I or M or O or A] AND PD=[20190501 <> 20190601]");
		params.put("_expertSearchCriteria.statisticsMode","on");
		
		
		
		
		String HTML = null;
		//send the GET request to the site to get HTML data
		try {
			HTML = HTTPRequest.sendPostTed(urlAuvergner,params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println(HTML);
		
		
		

		
		
		
		
		
		
//		String result = null;
//		// send POST request to the site to get location
//		try {
//			result = HTTPRequest.sendPost(urlAuvergner, params)[0];
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//		System.out.println(result);
		
		
		
	}
	/*****************************************************/

	/**
	 * Crawler for FranceMarche
	 * 
	 * @author ZHI
	 * @param the location, the time from (debut) to (fin),and the number of the
	 *            pages(including the first page)
	 * @return list of the avis
	 * 
	 *         the list for the localisation:
	 * 
	 *         auvergne-rhone-alpes bourgogne-franche-comte bretagne
	 *         centre+-+val+de+loire ...
	 */
	public ArrayList<Avis> franceMarcheCrawler(String localisation, String date_parution_debut,
			String date_parution_fin, int pageNum) {
		// define the link's url to the website
		String urlFM = "https://www.francemarches.com/search?search=";

		// define the map of the parametres(settings)
		Map<String, String> params = new HashMap<String, String>();

		// define the type de presentation(domain d'activité): travaux ,
		// services,fournitures and autre
		String typeDePresentation1 = "travaux";
		String typeDePresentation2 = "services";
		String typeDePresentation3 = "fournitures";
		String typeDePresentation4 = "autre";

		params.put("typeDePresentation1", typeDePresentation1);
		params.put("typeDePresentation2", typeDePresentation2);
		params.put("typeDePresentation3", typeDePresentation3);
		params.put("typeDePresentation4", typeDePresentation4);

		// define the type de annonce(type d'avis) :AAPC, Avis d'attribution, Avis
		// rectificatif, Autre
		String typeDAnnonce1 = "avis+d%27attribution";
		String typeDAnnonce2 = "aapc";
		String typeDAnnonce3 = "avis%252Brectificatif";
		String typeDAnnonce4 = "autre";

		params.put("typeDAnnonce1", typeDAnnonce1);
		params.put("typeDAnnonce2", typeDAnnonce2);
		params.put("typeDAnnonce3", typeDAnnonce3);
		params.put("typeDAnnonce4", typeDAnnonce4);

		// define the localisation(Lieu d'exéution) 'number max' = 8 :
		// auvergne-rhone-alpes , bourgogne-franche-comte , bretagne ,
		// centre+-+val+de+loire , corse , .....
		String localisation1 = "auvergne-rhone-alpes";
		String localisation2 = "bourgogne-franche-comte";
		String localisation3 = "bretagne";
		String localisation4 = "centre+-+val+de+loire";

		params.put("localisation", localisation);
		params.put("localisation1", localisation1);
		params.put("localisation2", localisation2);
		params.put("localisation3", localisation3);
		params.put("localisation4", localisation4);

		// define "Date de publiccation"
//				String date_parution_debut = "2019-05-01";
//				String date_parution_fin = "2019-06-01";

		params.put("date_parution_debut", date_parution_debut);
		params.put("date_parution_fin", date_parution_fin);

		// define local date
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String date_local = dateFormat.format(date).toString();

		params.put("date_local", date_local);

		// create the url by all the settings for GET request
		urlFM = "https://www.francemarches.com/search?date_parution_debut=%3E=" + params.get("date_parution_debut")
				+ "&date_parution_fin=%3C=" + params.get("date_parution_fin")
				+ "&c=q%253D%252523all%252BAND%252Bfm_class_date_cloture_dt%25253E%25253D" + params.get("date_local")
				+ "%2526b%253D0%2526s%253D%2526sl%253Dxml%2526lang%253Dfr%2526hf%253D15%2526r%253Df%25252FtypeDePrestation%25252F"
				+ params.get("typeDePresentation1") + "%2526r%253Df%25252FtypeDAnnonce%25252F"
				+ params.get("typeDAnnonce2")
				+ "%2526r%253Df%25252FsecteurDActivite%25252Fservices%252Bde%252Btechnologies%252Bde%252Bl%252527information%25252C%252Bconseil%25252C%252Bdeveloppement%252Bde%252Blogiciels%25252C%252Binternet%252Bet%252Bappui%2526r%253Df%25252F"
				+ "localisation%25252F" + params.get("localisation") + "%2526r%253Df%25252FtypeDePrestation%25252F"
				+ params.get("typeDePresentation2") + "&search=&date=&alerte_Name=";
		// System.out.println(urlFM);
		String result = null;
		try {
			result = HTTPRequest.sendGET(urlFM);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(result);

		// parse the HTML data
		Document doc = Jsoup.parse(result);
		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();
		ArrayList<String> listLocation = new ArrayList<String>();

		Elements eles = doc.getElementsByAttributeValueStarting("href", "https://www.francemarches.com/appel-offre/");
		for (Element ele : eles) {
			listLinks.add(ele.attr("href"));
		}

		Elements eles2 = doc.getElementsByClass("dateParution");

		for (Element ele : eles2) {
			// System.out.println(ele.text());
			listDate.add(ele.text());
		}
		Elements elesTitre = doc.getElementsByClass("titre");

		for (Element ele : elesTitre) {
			// System.out.println(ele.text());
			listTitre.add(ele.text());
		}

		Elements elesLocation = doc.getElementsByClass("localisation");
		for (Element ele : elesLocation) {
			// System.out.println(ele.text());
			listLocation.add(ele.text());
		}
		// using LinkedHashSet to delete the repeated members
		LinkedHashSet<String> setLinks = new LinkedHashSet<String>(listLinks);
		listLinks = new ArrayList<String>(setLinks);
		LinkedHashSet<String> setTitre = new LinkedHashSet<String>(listTitre);
		listTitre = new ArrayList<String>(setTitre);

//				System.out.println(listLinks);
//				System.out.println(listTitre);
//				System.out.println(listDate);

		// create the list fo the avis
		ArrayList<Avis> avisList = new ArrayList<Avis>();
		for (int i = 0; i < listLinks.size(); i++) {
			avisList.add(new Avis(listDate.get(i), listTitre.get(i), listLinks.get(i), listLocation.get(i)));
		}

		System.out.println(avisList.size());

		/** for the others pages **/

		// define the number of the pages(including the first page)
//				int pageNum = 2;
		// for the others pages
		for (int i = 1; i < pageNum; i++) {

			// define the url of the other pages
			urlFM = "https://www.francemarches.com/search?c=q%253D%252523all%252BAND%252Bfm_class_date_cloture_dt%25253E%25253D"
					+ params.get("date_local") + "%252BAND%252Bfm_class_date_parution_dt%25253E%25253D"
					+ params.get("date_parution_debut") + "%252BAND%252Bfm_class_date_parution_dt%25253C%25253D"
					+ params.get("date_parution_fin") + "%2526b%253D" + 15 * (i - 1)
					+ "%2526s%253D%2526sl%253Dxml%2526lang%253Dfr%2526hf%253D15%2526r%253Df%25252FtypeDePrestation%25252F"
					+ params.get("typeDePresentation1") + "%2526r%253Df%25252FtypeDAnnonce%25252F"
					+ params.get("typeDAnnonce2")
					+ "%2526r%253Df%25252FsecteurDActivite%25252Fservices%252Bde%252Btechnologies%252Bde%252Bl%252527information%25252C%252Bconseil%25252C%252Bdeveloppement%252Bde%252Blogiciels%25252C%252Binternet%252Bet%252Bappui"
					+ "%2526r%253Df%25252Flocalisation%25252F" + params.get("localisation")
					+ "%2526r%253Df%25252FtypeDePrestation%25252F" + params.get("typeDePresentation2") + "&b=" + 15 * i
					+ "&s=&sa=&search=&date_parution_debut=%3E=" + params.get("date_parution_debut")
					+ "&date_parution_fin=%3C=" + params.get("date_parution_fin");

			// System.out.println(urlFM);

			try {
				result = HTTPRequest.sendGET(urlFM);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// parse the HTML data
			Document doc2 = Jsoup.parse(result);
			ArrayList<String> listLinks2 = new ArrayList<String>();
			ArrayList<String> listTitre2 = new ArrayList<String>();
			ArrayList<String> listDate2 = new ArrayList<String>();

			Elements eles3 = doc2.getElementsByAttributeValueStarting("href",
					"https://www.francemarches.com/appel-offre/");
			for (Element ele : eles3) {
				listLinks2.add(ele.attr("href"));
			}

			Elements eles4 = doc2.getElementsByClass("dateParution");

			for (Element ele : eles4) {
				// System.out.println(ele.text());
				listDate2.add(ele.text());
			}

			Elements eles5 = doc2.getElementsByClass("titre");
			for (Element ele : eles5) {
				// System.out.println(ele.text());
				listTitre2.add(ele.text());
			}

			Elements elesLocation2 = doc.getElementsByClass("localisation");
			for (Element ele : elesLocation2) {
				// System.out.println(ele.text());
				listLocation.add(ele.text());
			}
			// using LinkedHashSet to delete the repeated members
			setLinks = new LinkedHashSet<String>(listLinks2);
			listLinks2 = new ArrayList<String>(setLinks);
			setTitre = new LinkedHashSet<String>(listTitre2);
			listTitre2 = new ArrayList<String>(setTitre);

//					System.out.println(listLinks2);
//					System.out.println(listTitre2);
//					System.out.println(listDate2);
			for (int j = 0; j < listLinks2.size(); j++) {
				avisList.add(new Avis(listDate2.get(j), listTitre2.get(j), listLinks2.get(j), listLocation.get(i)));
			}

		}

		System.out.println(avisList.size());
		return avisList;

	}
	/*****************************************************/

	/**
	 * Crawler for E-marchespublics    it doesn't work maybe because of the cookie (there is maybe some kinds of the encoding because it's different everytime)
	 */
	public void EmarchesCrawler() {
		// define the url of the site
		String urlEMarche = "https://www.e-marchespublics.com/appel-offre";
		
		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();
		
		// define the map of the settings
		Map<String, String> params = new HashMap<String, String>();

		/*
		 * 
		 * _token
		 * _token: VKKppFE0OlaWmqT7Igmfu3BhpwNdw6dH66HOo1r3
			category: Service
			what: informatique
			where: all
			notice_type: AAPC
		 */
		params.put("_token", "VKKppFE0OlaWmqT7Igmfu3BhpwNdw6dH66HOo1r3");
		params.put("category", "Tous");
		params.put("what", "informatique");
		params.put("where", "all");
		params.put("notice_type", "AAPC");
		
		String result = null;
		// send POST request to the site to get the HTML data
		try {
			result = HTTPRequest.sendPost(urlEMarche, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		
		

	}
	/*****************************************************/


	/**
	 * Crawler for Marchesonline
	 * @author ZHI
	 * @param the date ,the location and the number of the pages(including the first page) 
	 * @return the list of the avis
	 * 
	 * date: 	Aujourd'hui : "TODAY";
	 * 			Depuis 3 jours : "3_DAYS";
	 * 			Depuis 1 semaine : "1_WEEK";
	 * 			Depuis 2 semaine : "2_WEEK";
	 * 			Depuis 3 semaine : "3_WEEK";
	 * 			Depuis 1 mois : "1_MONTH";
	 * 			Depuis 3 mois : "3_MONTH";
	 * 
	 *  
	 * the location :the number of the area, for example : Ville de Paris : "75"
	 * 
	 *   
	 */
	public ArrayList<Avis> marchesOnlineCrawler(String date,String location,int pageNum) {
		// define the url of the site
		String urlEMarcheOnline = "https://www.marchesonline.com/appels-offres/en-cours";
		
		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();
		ArrayList<String> listLocation = new ArrayList<String>();
		// define the map of the settings
		Map<String, String> params = new HashMap<String, String>();
		/*
		 * *
		 * __posted: 1
			mots_cle: 
			mots_cle_exclure: 
			id_ref_type_recherche: 1
			type_map: 1
			id_ref_departement[]: 8
			id_ref_departement[]: 10
			id_ref_departement[]: 52
			id_ref_departement[]: 53
			id_ref_departement[]: 55
			id_ref_departement[]: 56
			id_ref_departement[]: 58
			id_ref_departement[]: 68
			id_ref_departement[]: 69
			id_ref_departement[]: 89
			id_ref_region[]: 21
			id_ref_type_avis: 1
			id_ref_domaine_activite: 4
			nature_prestation_checkAll: 
			id_ref_nature_travaux[]: 4
			id_ref_nature_travaux[]: 10
			id_ref_nature_travaux[]: 11
			id_ref_nature_travaux[]: 12
			id_ref_nature_travaux[]: 21
			id_ref_nature_travaux[]: 22
			id_ref_nature_travaux[]: 37
			id_ref_nature_travaux[]: 65
			id_ref_nature_travaux[]: 68
			id_ref_nature_travaux[]: 69
			id_ref_nature_travaux[]: 70
			id_ref_nature_travaux[]: 71
			id_ref_nature_travaux[]: 72
			id_ref_nature_travaux[]: 73
			id_ref_nature_travaux[]: 74
			id_ref_nature_travaux[]: 75
			id_ref_nature_travaux[]: 76
			id_ref_nature_travaux[]: 77
			id_ref_nature_travaux[]: 78
			id_ref_nature_travaux[]: 79
			id_ref_nature_travaux[]: 80
			id_ref_nature_travaux[]: 81
			id_ref_nature_travaux[]: 82
			id_ref_nature_travaux[]: 83
			id_ref_nature_travaux[]: 84
			id_ref_nature_travaux[]: 85
			id_ref_nature_travaux[]: 86
			id_ref_nature_travaux[]: 87
			id_ref_nature_travaux[]: 93
			id_ref_nature_travaux[]: 94
			id_ref_nature_travaux[]: 95
			id_ref_nature_travaux[]: 104
			date_mel_type: 
			date_mise_en_ligne: 3_DAYS
			date_mise_en_ligne_from: 
			date_mise_en_ligne_to: 
			date_limite_from: 
			date_limite_to: 
			Rechercher: Lancer la recherche
		 */
//		params.put("__posted", "1");
//		
//		params.put("id_ref_type_recherche", "1");
//		params.put("type_map", "1");
//		
//		params.put("date_mise_en_ligne", "3_DAYS");
//		params.put("id_ref_region[]", "21");
//		params.put("id_ref_departement[]", "8"); 
//		params.put("id_ref_departement[]", "10");
//		params.put("id_ref_departement[]", "52");
//		params.put("id_ref_departement[]", "53");
//		params.put("id_ref_departement[]", "55");
//		params.put("id_ref_departement[]", "56");
//		params.put("id_ref_departement[]", "58");
//		params.put("id_ref_departement[]", "68");
//		params.put("id_ref_departement[]", "69");
//		params.put("id_ref_departement[]", "89");
//		
//
//		params.put("id_ref_type_avis", "1");
//		params.put("id_ref_domaine_activite", "4");
//		
//		params.put("id_ref_nature_travaux[]", "4");
//		params.put("id_ref_nature_travaux[]", "10");
//		params.put("id_ref_nature_travaux[]", "11");
//		params.put("id_ref_nature_travaux[]", "12");
//		params.put("id_ref_nature_travaux[]", "21");
//		params.put("id_ref_nature_travaux[]", "22");
//		params.put("id_ref_nature_travaux[]", "37");
//		params.put("id_ref_nature_travaux[]", "65");
//		params.put("id_ref_nature_travaux[]", "68");
//		params.put("id_ref_nature_travaux[]", "69");
//		params.put("id_ref_nature_travaux[]", "70");
//		params.put("id_ref_nature_travaux[]", "71");
//		params.put("id_ref_nature_travaux[]", "72");
//		params.put("id_ref_nature_travaux[]", "73");
//		params.put("id_ref_nature_travaux[]", "74");
//		params.put("id_ref_nature_travaux[]", "75");
//		params.put("id_ref_nature_travaux[]", "76");
//		params.put("id_ref_nature_travaux[]", "77");
//		params.put("id_ref_nature_travaux[]", "78");
//		params.put("id_ref_nature_travaux[]", "79");
//		params.put("id_ref_nature_travaux[]", "80");
//		params.put("id_ref_nature_travaux[]", "81");
//		params.put("id_ref_nature_travaux[]", "82");
//		params.put("id_ref_nature_travaux[]", "83");
//		params.put("id_ref_nature_travaux[]", "84");
//		params.put("id_ref_nature_travaux[]", "85");
//		params.put("id_ref_nature_travaux[]", "86");
//		params.put("id_ref_nature_travaux[]", "87");
//		params.put("id_ref_nature_travaux[]", "93");
//		params.put("id_ref_nature_travaux[]", "94");
//		params.put("id_ref_nature_travaux[]", "95");
//		params.put("id_ref_nature_travaux[]", "104");
//		
//		params.put("Rechercher", "Lancer la recherche");
		/**
		 * __posted: 1
mots_cle: 
mots_cle_exclure: 
id_ref_type_recherche: 1
type_map: 1
id_ref_departement[]: 76
id_ref_type_avis: 1
date_mel_type: 1
date_mise_en_ligne: TODAY
date_mise_en_ligne_from: 
date_mise_en_ligne_to: 
date_limite_from: 
date_limite_to: 
Rechercher: Lancer la recherche
		 */
		params.put("__posted", "1");
		params.put("id_ref_type_recherche", "1");
		params.put("type_map", "1");
		params.put("id_ref_departement[]", location);
		params.put("id_ref_type_avis", "1");
		params.put("date_mel_type", "1");
		params.put("date_mise_en_ligne", date);
		params.put("Rechercher", "Lancer la recherche");
		
		String result = null;
		// send POST request to the site to get the HTML data
		try {
			result = HTTPRequest.sendPost(urlEMarcheOnline, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(result);
		
		// parse the HTML data
		Document doc = Jsoup.parse(result);
		
		// get the links from the HTML data
		Elements eles_link = doc.getElementsByAttributeValueContaining("href", "/appels-offres/avis/");
		//System.out.println(eles_link.size());
		
		for (Element ele : eles_link) {
			//System.out.println(ele.attr("href"));
			listLinks.add("https://www.marchesonline.com" + ele.attr("href"));
		}
		
		//get the titre from the HTML data
		Elements eles_Titre = doc.getElementsByClass("identityNotice");
		for (Element ele : eles_Titre) {
			//System.out.println(ele.text());
			listTitre.add(ele.text());
		}
		
		//get the date from the HTML data
		Elements eles_date = doc.getElementsByClass("onlineDate");
		for(Element ele : eles_date) {
			//System.out.println(ele.text());
			listDate.add(ele.text());
		}
		
		//get the location from the HTML data
		Elements eles_location = doc.getElementsByClass("location");
		for(Element ele :eles_location) {
			//System.out.println(ele.text());
			listLocation.add(ele.text());
		}
		
		/** for the others pages **/
		
		//int pageNum = 2;
		
		for(int i = 2;i<pageNum+1;i++) {
			// define the url of the site
			String urlEMarcheOnline2 = "https://www.marchesonline.com/appels-offres/en-cours?page="+i;
					
//			ArrayList<String> listLinks2 = new ArrayList<String>();
//			ArrayList<String> listTitre2 = new ArrayList<String>();
//			ArrayList<String> listDate2 = new ArrayList<String>();
//			ArrayList<String> listLocation2 = new ArrayList<String>();
			// define the map of the settings
			Map<String, String> params2 = new HashMap<String, String>();
		
			params2.put("__posted", "1");
			params2.put("id_ref_type_recherche", "1");
			params2.put("type_map", "1");
			params2.put("id_ref_departement[]", location);
			params2.put("id_ref_type_avis", "1");
			params2.put("date_mel_type", "1");
			params2.put("date_mise_en_ligne", date);
			params2.put("Rechercher", "Lancer la recherche");
			
			String result2 = null;
			// send POST request to the site to get the HTML data
			try {
				result2 = HTTPRequest.sendPost(urlEMarcheOnline2, params2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(result);
			
			// parse the HTML data
			Document doc2 = Jsoup.parse(result2);
			
			// get the links from the HTML data
			Elements eles_link2 = doc2.getElementsByAttributeValueContaining("href", "/appels-offres/avis/");
			//System.out.println(eles_link.size());
			
			for (Element ele : eles_link2) {
				//System.out.println(ele.attr("href"));
				listLinks.add("https://www.marchesonline.com" + ele.attr("href"));
			}
			
			//get the titre from the HTML data
			Elements eles_Titre2 = doc2.getElementsByClass("identityNotice");
			for (Element ele : eles_Titre2) {
				//System.out.println(ele.text());
				listTitre.add(ele.text());
			}
			
			//get the date from the HTML data
			Elements eles_date2 = doc2.getElementsByClass("onlineDate");
			for(Element ele : eles_date2) {
				//System.out.println(ele.text());
				listDate.add(ele.text());
			}
			
			//get the location from the HTML data
			Elements eles_location2 = doc2.getElementsByClass("location");
			for(Element ele :eles_location2) {
				//System.out.println(ele.text());
				listLocation.add(ele.text());
			}
		}
		
//		System.out.println(listLinks.size());
//		System.out.println(listTitre.size());
//		System.out.println(listDate.size());
//		System.out.println(listLocation.size());
//		
//		
//		System.out.println(listDate);
//		System.out.println(listLocation);
//		System.out.println(listLinks);
//		System.out.println(listTitre);
		
		ArrayList<Avis> avisList = new ArrayList<Avis>();
		for (int i = 0; i < listLinks.size(); i++) {
			avisList.add(new Avis(listDate.get(i), listTitre.get(i), listLinks.get(i),listLocation.get(i)));
		}
		
		return avisList;
	}
}