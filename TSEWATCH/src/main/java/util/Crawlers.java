package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	 * Crawler for Boamp (GUO's WORKING ON)
	 */

//	// Scrawler ONLY for https://boamp.fr/avis/liste
//	public ArrayList<String> getLinksBOAMP() {
//
//		String AuthFileName = this.getClass().getClassLoader().getResource("jssecacerts").getPath();
//		System.setProperty("javax.net.ssl.trustStore", AuthFileName);
//
//		Map<String, String> params = new HashMap<String, String>();
//
//		
//		/*
//		 * estrecherchesimple: 0
//			archive: 0
//			idweb: 
//			nomacheteur: 
//			fulltext: 
//			descripteur[]: mc68
//			descripteur[]: mc97
//			descripteur[]: mc453
//			descripteur[]: mc454
//			descripteur[]: mc162
//			descripteur[]: mc163
//			descripteur[]: mc186
//			descripteur[]: mc463
//			descripteur[]: mc283
//			descripteur[]: mc171
//			numerodepartement[]: 77
//			typeavis[]: 5
//			typeavis[]: 1
//			typeavis[]: 2
//			typeavis[]: 3
//			typeavis[]: 4
//			dateparutionmin: 01/05/2019
//			dateparutionmax: 01/06/2019
//			datelimitereponsemin: 
//			datelimitereponsemax: 
//			famille: 
//			prestataire: 
//		 */
//		params.put("estrecherchesimple", "0");
//		params.put("archive", "0");
//		
////		params.put("idweb", "");
////		params.put("nomacheteur", "");
////		params.put("fulltext", "");
//
//		// Add all the keywords corresponding
////		for(String str : Const.listDescripteur) {
////			params.put(Const.DESCRIPTION, str);
////		}
//		
////		params.put("descripteur[]", "mc63");
////		params.put("descripteur[]", "mc83");
//		
//
////		params.put("descripteur%5B%5D", "mc68");
////		params.put("descripteur%5B%5D", "mc97");
////		params.put("descripteur%5B%5D", "mc453");
////		params.put("descripteur%5B%5D", "mc454");
////		params.put("descripteur%5B%5D", "mc162");
////		params.put("descripteur%5B%5D", "mc163");
////		params.put("descripteur%5B%5D", "mc186");
////		params.put("descripteur%5B%5D", "mc463");
////		params.put("descripteur%5B%5D", "mc283");
////		params.put("descripteur%5B%5D", "mc171");
//		
//		
//		
//		
//		params.put("numerodepartement%5B%5D", "75");
//
//		// 5 -> Cover all 1-4 options
////		params.put("typeavis%5B%5D", "5");
////		params.put("typeavis%5B%5D", "1");
////		params.put("typeavis%5B%5D", "2");
////		params.put("typeavis%5B%5D", "3");
////		params.put("typeavis%5B%5D", "4");
////		
//		params.put("dateparutionmin", "01/06/2019");
//		params.put("dateparutionmax", "07/06/2019");
//
//		params.put("datelimitereponsemin", "");
//		params.put("datelimitereponsemax", "");
////		params.put("famille", "");
////		params.put("prestataire", "");
//		// Get result
//		String result;
//		try {
//			result = HTTPRequest.sendPostBoamp(Const.BOAMP, params);
//			
//			//System.out.println(result);
//			Document doc = Jsoup.parse(result);
//
////			Elements test = doc.getElementsByClass("search-result-caption");
////			System.out.println(test.html());
//			
//			ArrayList<String> listLinks = new ArrayList<String>();
//			ArrayList<String> listTitre = new ArrayList<String>();
//			ArrayList<String> listDate = new ArrayList<String>();
//			ArrayList<String> listLocation = new ArrayList<String>();
//			
//			
//			// get the links from the HTML data
//			Elements eles_link = doc.getElementsByAttributeValueContaining("href", "/avis/detail/");
//			//System.out.println(eles_link.size());
//						
//			for (Element ele : eles_link) {
//			//System.out.println(ele.attr("href"));
//				if (!listLinks.contains("https://www.boamp.fr" + ele.attr("href")))
//					listLinks.add("https://www.boamp.fr" + ele.attr("href"));
//			}
//			System.out.println(listLinks.size());
//			System.out.println(listLinks);
//			//get the titre from the HTML data
//			
//			
////			ArrayList<String> linksOfPages = new ArrayList<String>();
////			Elements elesPageIndex = doc.getElementsByAttributeValueStarting("href", "/avis/page?page=");
////			for (Element ele : elesPageIndex) {
////				if (!linksOfPages.contains("https://www.boamp.fr" + ele.attr("href")))
////					linksOfPages.add("https://www.boamp.fr" + ele.attr("href"));
////			}
////
////			ArrayList<String> listLinks = new ArrayList<String>();
////			Elements hrefs = doc.getElementsByAttributeValueContaining("href", "/avis/detail/");
////			for (Element href : hrefs) {
////				if (!listLinks.contains("https://www.boamp.fr" + href.attr("href")))
////					listLinks.add("https://www.boamp.fr" + href.attr("href"));
////			}
////
////			ListIterator li = listLinks.listIterator();
////			while (li.hasNext()) {
////				Object obj = li.next();
////				int length = obj.toString().split("/").length;
////				li.set("https://www.boamp.fr/avis/pdf/" + obj.toString().split("/")[length - 2]);
////			}
////			System.out.println(listLinks.size());
////			return listLinks;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
//
//	}

	
	//this version can get a part of the data(not all of them and we don't know why),so we use it before we solve the problem
	// Scrawler ONLY for https://boamp.fr/avis/liste
		public ArrayList<Avis> getLinksBOAMP(String dateparutionmin,String dateparutionmax,int pageNum){
			
			String AuthFileName = this.getClass().getClassLoader().getResource("jssecacerts").getPath();
			System.setProperty("javax.net.ssl.trustStore",AuthFileName);
			
			Map<String,String> params = new HashMap<String, String>();
			
			params.put(Const.CONF1,"0");
			params.put(Const.CONF2,"0");
			
			// Add all the keywords corresponding
//			for(String str : Const.listDescripteur) {
//				params.put(Const.DESCRIPTION, str);
//			}
//			params.put("descripteur[]", "mc38");
//			params.put("descripteur[]", "mc283");
			
			
			params.put("descripteur[]", "mc68");
			params.put("descripteur[]", "mc97");
			params.put("descripteur[]", "mc453");
			params.put("descripteur[]", "mc454");
			params.put("descripteur[]", "mc162");
			params.put("descripteur[]", "mc163");
			params.put("descripteur[]", "mc186");
			params.put("descripteur[]", "mc463");
			params.put("descripteur[]", "mc283");
			params.put("descripteur[]", "mc171");
			params.put("page", Integer.toString(pageNum));
			
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
				result = HTTPRequest.sendPost(Const.BOAMP, params);
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
				
				
				ArrayList<String> listLinks = new ArrayList<String>();
				ArrayList<String> listTitre = new ArrayList<String>();
				ArrayList<String> listDate = new ArrayList<String>();
				ArrayList<String> listLocation = new ArrayList<String>();
				
				
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

				
		        System.out.println(listLinks.size());
		        System.out.println(listTitre.size());
		        System.out.println(listDate.size());
		        System.out.println(listLocation.size());
		        System.out.println(listLinks);
		        System.out.println(listTitre);
		        System.out.println(listDate);
		        System.out.println(listLocation);
		        
		        ArrayList<Avis> avisList = new ArrayList<Avis>();
				for (int i = 0; i < listLinks.size(); i++) {
					avisList.add(new Avis(listDate.get(i), listTitre.get(i), listLinks.get(i),listLocation.get(i)));
				}

				return avisList;
		        
		        
		        
		        
		        
		        //return listLinks;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return null;
	        
		}
	public static void main(String[] args) {
		ArrayList<String> urls = new ArrayList<String>();
		Crawlers crawler = new Crawlers();
//		urls = crawler.getLinksBOAMP();
//		
//		for(String url : urls) {
//			System.out.println(url);
//		}

		/** for test **/
//		ArrayList<Avis> avisList = crawler.franceMarcheCrawler("bretagne","2019-06-01","2019-06-10",2);
//		ArrayList<Avis> avisList = crawler.proxiLegalesCrawler("orange", 2);	
//		ArrayList<Avis> avisList = crawler.marchepublicsInfoCrawler("75","< 8");	
//		ArrayList<Avis> avisList = crawler.marchepublicGouvCrawler("01/04/2019","30/04/2019");
//		ArrayList<Avis> avisList = crawler.auvergnerCrawler("","",2);
		
		//crawler.tedEuropaCrawler();
		//crawler.EmarchesCrawler();
		
//		ArrayList<Avis> avisList = crawler.getLinksBOAMP("01/06/2019","07/06/2019",1);	
		ArrayList<Avis> avisList = crawler.marchesOnlineCrawler("3_DAYS","65",2);
		
//		ArrayList<Avis> avisList = crawler.auvergnerCrawler("","it",1);
//		ArrayList<String> keywords = new ArrayList<String>();
//		keywords.add("it");
//		keywords.add("informatique");
		
		for(Avis avis:avisList) {
			avis.print();
//			if(avis.checkKeywordHTML(keywords)) {
//				System.out.println("#*#*");
//			}
		}
		

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
	 */
	public ArrayList<Avis> proxiLegalesCrawler(String filtre, int pageNum) {
		// define the url of the site
		String urlProxi = "https://www.proxilegales.fr/publisher_portail/public/annonce/afficherAnnonces.jsp;jsessionid=1A20E1BB4A2D9511152F7E21406CFB34";
		// "https://www.proxilegales.fr/publisher_portail/public/annonce/afficherAnnonces.jsp;jsessionid=52E4BDCF7F89FB5D5DF58052EAEFA420";

		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();

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
	 * @param the loaction and the date
	 * @return list of the avis
	 * 
	 *         loaction(IDR):
	 *
	 *         <option value="1,3,7,15,26,38,42,43,63,69,73,74"
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
	 * 
	 * 
	 * 
	 *         datePatution: toutes : "" Aujourd'hui : "= 0" Hier : "= 1" 2 derniers
	 *         jours : "< 2" 8 derniers jours : "< 8" 30 derniers jours : "< 30"
	 */
	public ArrayList<Avis> marchepublicsInfoCrawler(String IDR, String dateParution) {
		// define the url of the site
		String urlMPI = "http://www.marches-publics.info/mpiaws/index.cfm?fuseaction=pub.affResultats&IDs=25";

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
		// params.put("IDN", "X");
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
		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();
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
	 * @param the date from (debut) to (fin)
	 * @return list of the avis
	 * 
	 *         can't change the page because of the "PRADO_PAGESTATE"
	 * 
	 */
	public ArrayList<Avis> marchepublicGouvCrawler(String date_parution_debut, String date_parution_fin) {
		// define the url of the site
		String urlMPI = "https://www.marches-publics.gouv.fr/?page=entreprise.EntrepriseAdvancedSearch&searchAnnCons";

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
		// define the map of the settings
		Map<String, String> params = new HashMap<String, String>();
//		params.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneStart", "06/06/2019");
//		params.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneEnd", "06/12/2019");
		params.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeStart", date_parution_debut);
		params.put("ctl0$CONTENU_PAGE$AdvancedSearch$dateMiseEnLigneCalculeEnd", date_parution_fin);
		params.put("ctl0$CONTENU_PAGE$AdvancedSearch$procedureType", "1");
		params.put("PRADO_PAGESTATE",
				"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");
		
	//	params.put("PRADO_PAGESTATE", "eJztvctz3DiW6O31/SsY7pmSPWHZSTKfrnZPpFNpl+rq1Zkpz/TERCioTEqiK5PM4kNl90RFzPau72p2HXfV2nyrb3eXiu8fmb/kwwFAEiDBR0qZdko6Ha2yRAIgcAAcHLzOz7AvjItma9o2ze55d9rRz7uG2Z11uvq5bZ03jOl5c6Ybna71Vn/7H87bxo/WW+PtfwRvm2+fT8N54zn5u535u/H2P36PH+jP5QjNbIBW/CB5YtAojTROGx7IX++9fT6e+s4yPPXnz38M3nY7b5+/8e0g8CJ/agdvptbcdme+Y/tvBsmvH/9lIv/12vWm1vTKfv05+Odf3rWmtn3RNjuN7rQ7bb5umJ2eaeptSJ6k/skJnPO5/fzHc5KLNLOd6rz1WjXyNrLtuZw//mQNeexW51HX62SyP1s4rhOEvhU6nitnN/fuXhnXjbfPzy13ZltR/9J2Q1oCned8abn2/MC79KRy0bdyWjpLy4yjOO4v8xrReBaawpfee9ZiWe9zuY5gtHlCR9a1cxlR+RxGc2fuuJeRDeHMtK3rabzu2+dzy720Lu0g/2Uihv3QXgy8CITj0DxXFYjIIbD37KnnuvY0tH1ItQWhdfgUqTt7bPvXztQ+tEOHvdbTnBnJ34b+9rlvL22LpHEgRKuZTZoX+kX3l0PPHXiLZWintZx+0cyphmYmRF55tOMnFcKIm9EeKUSSKFT5jDzoR59JN5hdRY6Qfpzl/nQa2c78xPNDy5lnMtQRI7SlCEkJBW3YVeSWNPPntKWQjPDe2SYpXYXhMnj75s1vv/32emH5pGMFu8vofO5Mg9eXXnT9+sJ/45AO8+X18mr5z0vSat5Z8MXX9Ls/eQtSTJqrTtwavdC5cKa0OSbZgkK6wosgyTVrKEWRm8oWnG04+WoVm5KYZs2mlBtVoIBEk322rq2A6rcD69ye5xMj0Sb2lxCE22y8ff5HFvhPvn1BdOCVlJMXL3/84xv+nouwB5nOhRR6POlpRBmGztxyArlgWeEkeqK8wQqqO/uSfI6UeOC5oe/Ng5H9a+T4RLOceEH43pr+kqTP655r1SEJbi99kj1aue24+d9T49F20GJ10kpbVZzwgKuemWg2uNbCLq0hMhZoGhM9T7qdilHnKey7F17wPprPV02rk6bFn6h6pVKhxr1hzwmmuZLxxHpp8qTZnM+9KZGu4yZqH/LvzEgnI03EcsOJtxTKpsMHguA3z59JLwxSDCsKr1g01rLeR2GYtq+kNK1Gqt46TCGd+B4px8JOMiJH0NMILRZh32Xt35HSp5L33H6SD4Xk882l9OXv33B856U1ckOIWoGJD8zsg2KrNjcu5UaK3FgQP+hlG6beyD3Rc0+M3BMz96SZe9LKPWnnnnTkhkn0zPSXsQ1DUS5oN/ckVxgjVxgjVxgjVxgjVxgjVxhDKAy0YNo2+n7oTOe2bppJzzP13LuRTcZVGFv715ZLLNZEg/HGkny9SiMalUmP7JnjE40hjr78K82aX0kMy/QrE3tOvmP5l8x8E1NtVabaYD2QSzHXAIycpjRy9WwI9Ww007E9Kf0H69ojo07d4d3oFqfRv66fDsudmWtzZq7Nmbk2Z+banJlrc2auA5k5+Zk5+Zk5+Zm5ftLM5bmZy3Mzl+emkGe9AdbIhTf0fc9NmzRMseZgHR2SWR8xFUuHTZLE8+oZxupGNZgukT/vy0PZ8AupbjedGvEWnJNoq1OrTVfa7jy1rpAxLsZmNkxPCNPhAvx5XDMb0Jpz9te/OOGVF4WHthuhKbaFppicf+hM/TD0nfMohAnvMaxUPZ8kjwYeKTbT6qwGTKKkn6nePzubWgGZOrsBmShcJ8Z8F4IfWstnZ7O00mCkmltT+8qbz0Czs0f7aV7hESll6IQgFcXr38WU/fhjUOJnE5iBE83ghs/O7HS6oLY/t1QapC0deqE2szXIr50TR+79neRRbnYXNnT4+tjWhHWX3+9tnxsV9nn5gkIPlkszqwV2opFep8oJ1g1+uPRC790/mh8g2D+ae0JArk/T8D84YlYe2SxBXkIlzWd/QSTCRdpp1lujIWJY2MEbB6IGbyAju55/ablOsLB3L30yOrz+vLxUr4viFKVoiqKYkOS2K5LuSHRQYktqvrUkOp8+/8bKrFWgzOCr2fxpr1+/5m3CI6PrNAqoiiN/Ervf8o/dD/DshX1N+tir8MoJXv4YXHm/7TnXL3bA9guSBEc0vZ2XP8bDhOcuvCiwvWv+9VZJmnQxEqKczyMa2iSq7YqkV/UhSRkr5X8nhZyfmYoT0NVmbmJ/37LpqmDKscnh6vM62YgW41fb0LoYPyfZXq3v10p8O+acD3GuCKMbsUy8D56/sBRrCu3GSrWMk85EcLlyt42aHa7WpLMt7oaIk06x8jILQkaBVWfW3CYq2nRdYT7LF6BIDS3IvPWjFZEPHUbz0FHtcjUU2b/HLob8VfrBsTedZ3dSU7s6YyLJVlRPsTHFGtWcSBb6U6KBc4YU7F5Z6b63HflsvyzIfFrcpoS9IderEytRClUNLWetwUSCWJfQqo9j2zLIVErtmXUP8luVWr1BgPUJnhaZNDikg06vrDDT3vVqZcXXU3TI20/VCeo1GzSoUZ5aWiViQrX7flvMmTqteovJ69r6a+U6zd36i94ylT0m1zmgEU592/ZJ/gKiGpJhqahjNKjIqqK0c9nqZAoiDJSF+xSkGdIZ2PEvXib9nii1u01q1meEbGLvZtu2aETTr5V2D9Hyg25J28XQBWnaqabM2oRp1ysNL67igqkQRM61bxeETi1B3qpJ6uUR9Hwbqm8qCsaHaCmCHhaa8tkUej3TdDwAKTd/eJZr21k7U8hYqaUZn2EZ8F4Zf1JMVBImPT9SEaMpChS6PVuQnltR4JA6mzkwJkKQjhBHl0WRRrEDIYa46F1qn4qmlvREVEnph8h/ri3xQ0ItNXPdrWwhO32UmbawD4HUPtl+AONZ+jVxB6KRS6mV65XSrrYsLiLhwdzh6l+cWIrGe1cWsDpCTnatXK8u27KRvsPkm35H3PbJaapWTlO180JJDXdJvrRAJ0TAnutacyewcwa/qMgNKVrGwBAj5VpWOyedttSy2lLK8njMw+dk1841q3barNJnYrsyYSTiI5BPcg4N6oROBzJf6wi6Pl5WZppGPpnHQ4vCZSqxIoIhZhT0OZ1pjL0oiDe+xNBmJvk0cVgKstyv2QjSATBqsR5URRGrg3zivRXYe9Nsa+jkKqHTyQYR1WVTUtJ7g6HwrpHYHPy5mBuF2dHNN+qu0NONlqgzyAzIsUOiC79M51FuROoa2Qem+D0z1sGJtVKVXjOftVzn77blhhimZxNOSM8bXDkXF/nBsysqCION5TVjijoWhpUgWi59Z8EijewLUGXZOOLgxaZmP9WI1stXTS+nhHuCJqGajvY7dvyXzcyzqWZPRpLMVEeSmj5RWPREbkFYSQU1WTcpDt3OhLbJgFScD6neqBnwk3XuzB1mxQ9L4+ZHx56oxdrMQnM+zKMvgyvPj4KJb7nBNVHjVrqg0ubGulg3ps4zMq4fX9Rt8TOhKuNH6UGi+ElTrm9BEfTJD90IEBUFVwbCOykbaV3xzn7Ct+2TmRGPGeSiihVnUO1cP24n0wZPEr0A88aiWFLXM5MvVsbrZb7GVvK839y5Z82KYukN9dcq40kmQTy2H9pBempqIQ5dcTTRmDJor6kZ0czkk0abkDGvIl4z3wLFWR70Lms6tWdkSI/GpD4DZpnQgf1XdqWgK6bXzrVecUIYPxLGMYMMoQE0lCCEBINDa2kJXUYh2l4+y+L0MX6k5x/lu5YhDf/NbGHptxvCx8X5ZfyolX+Ul4KRl4K4vRA/EtfZ23nBZGUhzRfjZ/mCm/mCm6YiZr5w4rQwu1Cpt8Wc5IssTvdAqWQWIuUFPqlUUhfvKBYxS+Jmu3n58ieP1cx383rxJOM0U2FjJ8yp2qY0W6JDV2UUqYm25G/82RvnwkujdCf7CVWM7KIxMX6IWUZv2pxYfiLsXLx2Jl4iM7iIQNTNlDSXyM/HkyZmDT7i9qfTfHVKk1kwOUhhigNnlwesKeyJ++mZkVwUaYqbjAr77gwMIVLvmetJcSxp1SWJBgYOyZjnzwbWzBcWrqH1z/iIQa2gbCgpcSPX+uvHlWxtwxQyZo8d3ybZu7LjmT7LWjuTtVwwKX2xbTHLa4XIkpkRP8ycrODL2If9k34uvtRsmuKqtzJ41kZfWr61sEmHvrRPfI+oeUXTbCmM9DrxpFUB6KS+fTm3P5Fp/0xeWY7D65mmSr5TFSWrOnjZ2aW2n6358CQXRWHl14jVzMRKe1xmWUKKld95qhdPVCLxM3FBtnAbR0qlW1jSsljZwSJuTupYMNuH5YGPoB5JGH4XMZtqur5RuM8jBc+OIarNHClCdsWNhxbnQLk4UkswxI+URsvO+Hic98f9w1y7kZY5oA+k31CHb6ul/9H3oqVN5OuotEhHmjVIWqAiYnblPtsTchF6GTkrek82jrSQIjTdD2QoinyljLt6YdMti5WdKUg5G8DBSWI8EzM9F1FqCWwjrDqSpBJMoVh0p4kt8ylsl66kFNpy2SqiSksC6SffOwPFpEBaypE7UUEEqTUQ+V14kU9sl4C0nD1vmgsutQW2MVMeo1fQEg4tl+h3y1GM3r3illAWy8hUKLXe+mAeEIOnQEf18vrgY51oiuliT5giQK8XjuWeBnnbSV7qoZKsitFRfFScQcKWne3z4zXBh7Ra8rKSFD5VUfViGtJSj9jf0uhKbW1IazysC9SOauQKbjTEvROSDXrG96NvLa/i6biUQjPTCJ35PIodJ+yRmZw7y0VpKT4qGmnpgY8j+7dgbodhrsoMeWmnIbbkkkjdTJvMxRjZMyvb0Qx5YafJmnKdiHp+8mzoucmzoecmz4a4Vx4/ys2dDT23MGDkl0cMPbP5nDtaJ2VZFBEJfGH9mgvSy4jeOvfta4e5yph7QQBKJBvJUIgiv4Bi5BdQDCMvivwaiZFfIzHyayRGfo3EyK+RGNk1ko+RM7MDeiJsYc+c7BqJIayRbORUmHy9XZeOgmfP2Nz9FA3MrKvSX+2UTltM/yd77nhUAVV62BEf1LwLIJyYEQeL1U/MtDdblVxvcAPI0O8oisKCPcn7EtXHgtbUoLtSh3HdKNZ0q588E44blpzUFNIV92NKtrTzR9HKd7RzrX6N3jkOE1ll96NXElXlEb2Czr/69nVOGHlPOGuSDrE9BsdHk+HR6dlJ/yPNAT2NQhrAIrgc+r44vdTj5b/sc2HXRzh/rj6YzmuXqWVf4RqA3RolQ/45vQQ4Yj5hZHn2ZzT8TLiYZEo5gdLz5tWu5SiNHdQmjTOth1IRCyfqu+ycg0ZjxiWjp7XyBy4LLk+ZtL6SwEH+MDk/Smvw0++u67lZ31C1cprG/P0b6tzkniAp5cXYEjZmkzvG8MbJ3rMoakDCSc+abT2j+Jv1BjzI3XWyTjmOFgvL/5oXOkl5HH4FkR+DLTZhfzADA5TkM/bk2dmFY89n8ka9N/fYpS/oDTN6ZQuklUbxoBkf/Rhf2+KPp3MrCNhzGNnT52TS4y14buhbXXg7cwKi17/y1zQDH5wv8NnSW2GsFOmCLV1+im/VpfIZ2bAh4riwxPE7tw7ILBf2JXzf84XkNjfiJjoqVQ3i8fDqA/rijR02w+im8cXdV5DXtQNnCsVtADFfhi7VtHiul42xLPpoYeVimtmY+bNBRmaMIfVq+59Yiof9pJGLJ4HTzpjcjg3413Xp+HSLHZ+mDUz4YkbOdm7lWLxKQ7pun8zw0q7IpgVy0vXu15VfT8seKM6fGGZl/sX+CnfuFZdZmSb6xjdV0yFVvqlKxqvne5YbaHNL829vLm5vfJto7FfafMcBgUfz2xvNi8if3vlnm16/JyGn0ljz+50cE8RK+tALA2klMTnsmh68n3tsDSZuYxfxg0wr+B5CbQn3YuV7v9Zy6XvTq9ubu9yNpS10cGVPfwGFKbfQzEl0+4s1DbekibUKbwnTXN7vlrBwuOJ34UTwoTezk++wDSYI14v3YRdsOE+Dkf+kwfRCnw8kKg1GesAyOZcTLzcKaj+2coSTccw7VXxGVj7bA3oKCjp3cqZaNgvkOz+458HyR/bdHlWlR9bC7h9mplMNtU7cs0Lrf9pfP4AlwB22OPQX9ZUVaiRDz1Yq6cTqAD0yoee1uO3B/LoatP2kz5+dnRNVnHpYoAaCmQ0j+NCiAXrZAFee7/yV2CXWvD93LuNgzWywYEqMsvl7y+eGit7NhvjNt5b8ndpMSrV3TYOIytGaQmXaYaIBNmseycPYU6iWeLzmAkvvFX9L45V8f++ray3g2H314BYf7FbctqS73XmtsIoegORTmwr0gWJuthXqX6E/Vx8IUyOOORWHQh5Dz5vA4Rh4xWc/sLnxLHn47MwSin/EcyS+D7l4TVK5u7u72sSDsBoxRjSe8SDJeaCRELRYHTkRMiOJWHnfPm/Qf3tyANu1iJU5S1aqG/LrAA6Shun7ElE4JMBaCw5eiQdX1uKcaH0w7chXF2QUJYM1sfR29t0ZbGo5tvZiMNh/qb3otLqtrrarnfRH+2NtYM/sL5reeVkqFRgDjcU95VLRRJy3xnrl0iJNdBiS7MGuJZzvpdIZWy60ZSKbw9ubmTP1dgNv6ljRF+1Fu9domEQyB385PqqUx5fOdNPyMNcrD73RywmEiCHVQXResEM0hE2GAhpAC6IlmUw4ZKb6is8a/NQWZBMJMsVwvWs6i4DG1Wi0oHFZvhNUyvDCuNq0DJvrlWGH5OWQHuO8/btvs/Jbl74zJeZW5KcyseYOyC+RitnsJV2u0dHGJ5WyOW+6m5ZNa83tCzpcVjikj0Xs2ozm0n+seSIlS/tsRy4cqaDdMRPznu2wYxpxO4yVXKu6QTYuNy309pobZDsv82Hke0sul0DrX1zQozHa7U3oW+4lhAuohBqNRELTuhKadq83LaHOmpslfCwrImexcC7ZSY9Xceshw4L8yAoc3lgjbXZ7c23PveWSt0cPVjCJWJnK62QE2ageT39pzDctyO56BQnOlLNy3Aep0X5JBaHX1/2X7Y2Xv7fm8ufVm6UNuOKH0ptmVuNUK5zW501LQV+znd3KD4GW9jOxMR1iclI5NLOat1EtiJl5sXFBrNnu1mHempMEvZ5FF4WIwoWtmkuYeIAWSXXGbnawI+3IuyITFYgFOnti+74TelRtv+gZjVb9fjVrhhsX5JoN9WYnI8dAs/zF7Q0bplqNXna2Uj1MXXY2blnq6zbPs+NUBG3p2nLmfDgaLpZzz4ntn+QYnHbiexfEiHLAre08GbD2HIu0PdLy4Aa5NQdJ9hp3MInsrr9xSa7ZSNcbUsfk85rb/zX1XG/hJIbRB8eFEwEBF2+fLpPEL9mpb75yMGUNsZPT70al+KzNm/H6mu34Tk+WXjI/DujU+ZUWxs1SNN+DpeeHAV1fMFupYVlvsnPV3fwwuGbDu0WeH8OkhF2B5F2UzPiIBDrNZhOUvOW79u7c2v1kzedEnfFWU91oAmO2cXGs2coGB7QnXnRNxiyp0zQa3dpD12/ml40Xe902cRNcijPqGmjdE98mGsbX4quemelB9ehtbHx6pa/ZLO6QbJ+6seXCrhnx1bYdutEfaCdJe7hvzwh7Xzcrn9/ly6Fsb3Dflc8x041BoyOFef91AFsUeZQb3/UUD0HCbl8ucNlxCn6Ck37uJCJfswI72TEQvHNlHZXR4xZfczHucuxCPL8xtqO5nX6Jf7stf9txp3MyM9qzg6ntzqzc2bN4Q0jYHhcP0ogX5CE5dgsmVxThZONT3kqjNztc+xtvp5EUj+hXK/fSwP9L+HWZ3eGHZpduvK7QOMQbTk1l0vTKdVWa0nkMle/UJ9qctnxnllWY6BybkSBteuwpq5gkl4Xg9IKd9JiQNqM8DDtMB5PGj/LGZWsT+5ZGsm/Jdi2hMcPgyfP5LbYsq42G9ZYZmgI/cEMKCv7Jk2O85QU1HtgeJHT8pKB25shdeVHNTW9Dr3n9Qq7TOFaNgjYf2B4gfOvE9+hByh2LHWUtL2Fv4zarytkqPZwZ++TIabuMlWlBATag3Npq5WZrkDPYHPQfpYIDIgzzRbNLndGQOY+ujW9v+GEctjHH02K7cy+888Cb3/49tMsnQSRD5salsW4tCKphubTnpMN41GWk5kXXtl/ZcfSHpgObuYL68Q2H7z60rVsNsls0Uw9Wv+pV58Y1/bpPNHSFItauyNYDO0IABih3EA6K+cTyQ5Jb37HCdFGzSiXpG1dJa16vhLwkWyQwBb29IbNG56KqcjsPbNOeXmaF9anbG829vbn0ps7tTVUhuw9xZz5bSDLk7t3eTH1ipo1J7U6dC7oRS55dwNGi72yzrX9fHvJ6khpV1sxahvTWymanGd982x0yI5aTEdo0s1JF3deY+Ob74vBcLGkU+uXVCaXc+Oiz7n3vZksqJZkiTyPfh4VmupttXdvTuFOnN50LRdCEsaix+enCmo2pptx5OR/XSuYKC6bdAi1IVVm5IGCesPm+vWaDq74YovPg9oYIgXqGqhDD5jv+Biyy8VfYf1/YdBft18jhZ4rYIumvUaUiMDc8nxAXHWQ3IOAc9tLzVYBhcb2huYnlhkbuDgjJDSgPkp1HefmDFIWeEoq+bHou/a0XDWCpnjmdgwOW5b38W0yf1zzsddODA9/ZRPs9duGZ4DtH9sIJbMBu0C4sOBVg/rPFB8mOc6oOMhvBMynFcWjBQkFmi0b0hg6uyZh3jj11zOI7rY/Rk4YgWJFw08kKdsjcFUpizbk8aAl7ZrBYpRT0MHF8+KTEXAW5TM6EhFYYhVlPPSKRS3YOGNDwVUc86NDJgtbwr1GPMW0kh1X4A2GghvbjRefz6sMnNVCiPnhcohb7vxHBsguv8TEZHW6rj9jmbDaEHh+kGSWYxqJwwh5tN7kST2/EtrhSfp74oWKKkF9pTeyS+OmUXZWXntW7w6q6b8/7ojPrE83uHYSkBCNr5qTO+aCbKYonBtraslVxh02JnpbtEoaIJ2/mVJF4VomdiCJpsNPKqqNCou1obsB2hGNQwDa4YO5TKpd5H5qlSOrqGPDbj8tKNOkRpO9sHApjdBlpMXmyEkkxeSIeYyHSIPpk7th+QObLZL6FPQZ7zMPsMSLok0QIHKti4QCbMjblLW3Kgn0NV6cCVMrYkh9YSyY5GY8H8KyXWu8iRRmOcsu2euxwZvhr5IB7EdTf2OofWKvnzbyVM7pL4d/NXFfYdwMAl4CD19C5dkJ7yC6dKn2DYafATvEAOkVuttpW3ZQpdoncyU1lO8JUFsTE+tDQvXZ8uMROPSfhKIId5mF2mI6Ra+/CvMCIN9q4o1l+N3bsgC/ZC8eO7xklcXMLpx1hlILF5UVJQth/sP88uP7TzrdcwY1qs0tyc7DjUSSedngy1pKzn6wLaHEXuL15ri1tf2GHGvivsFPSMb2LZZGWCluoPnmjwR1LfthsDt6s3GhB2rCnjfdHw8krCEHvqd7ehJYbapGrwZW1aE7d+Sz925td34aP0lNKMzKIUe81xPZzo9j/iMDMJhl/re3PtSDuq+Sb4GFsefv/hunNAXZbhIyIzHMpKQUvDEmO5JURFn16yGXp3P4dPBB8BjdM7FL5Nfnz12jHIpn7e6AF6dWTpeVDbjiAgPtqeP3Hc1978yf+X0u78u2Ld89fv5l50+CNF4XOPNi1A3/XaOidN9T7/JuTg/5geEbkf7af3t8LztKb8q+Xs4vnWmj5l3b47vnZ+dxyfyF/g2fZd88PHCJSUqO279rP/zR0tcAC3xHach4Ff3xj/el52cajXvWSdQaqZt/D4X174BG5k8mpK5B8eGsT75mTlM5V4e+zPQq+Ux07Gn6xp1Fy178hZCBnX3VyuwFdESLRht3GYMz71kfbOzLkt6TtFr2FXV8bJiTMGWP2tcFYMQcsv3q+6HAo58i6di5Jv6G3m8mjRvft88/WtcVu6L9desvT5dgOx85f7Rc7jjuzv7xeXi3/eWld2u9IJ1pE7mv4wJdEIgO4cfADCOnLux9infFu59VOt9VYfoF/G+zfr3aw8zJ2nwx+UtSl5DLTa1qpcGTNI/32KAqDTOPoisJpJru8I/siDkw1L5E5EQZ5Ku37T61AegTSnVL39RcnvuNOhTc6zwJ5NbbFF3B8xr5wXNIgZzaN5SwZWDfZ+mViF75UeZAh7hp73sIiSQfxXJUWSNiz7QpmCxzxmLHwcfB0i78hJygGaMWthjTZ+HuyTPl5Df5SLjsHtJ/DSWAhAAM3kfehH7nya4XVI4wd7F47Tzz0nODEc9wwAAX2PpqnPi3QA8C2O5SAqqDsrTjLEgdNwgtTtBZtI8EqjYQfReOsIs9X9pYnfEaqPkUr8ZSTE+FBrLdErSNMeIy2qHMz6mVr9a+ovP4cWfPEzU9OwYpHYExg/gmB4RU99JN4/vlz9jX1/ANjuqAHWSC5CCWaLrEncomn2ZoXpGwmGiP7SsaZ5Chp5ShC1Lir0UtYu6bwwt3zuUcdqHwbHQwoStKg0tpnNmIzVbhyw+DGR6JSc20O1WkNdQopUn3Qv/SZp7WsWmlLCsrKBUuwXXEKiTKBQguTC+kLqSFIySdzW4xdw+RqiIpofbX9sOyWKs3IGkLcfRJ1vaKoO+ncKFeFqHsfWqu5s5bw0+PGg5NPaffvCsy1/gVRwFcclNpRmNBbZ14FAj2YRDu0PiemUlywwfKa1nQn08UKuw519ADsHBBT2k8NKZL5FAYlJl1KX00Iuhn5ZAB7pAR/DIjY+dLeThxwR6MZf7czBYd7u45Lx317509/dBaXWuBP3+1cheEyePvmzW+//faab2Pscuexry+96Pr1hf8mvCL6K3hDmdfBm6UzDb1dlqLrhbveL68vnYsdzZqH5MtX1mKpeST+pUW9p+/AauYbyNyfxGo1sVofV7WCuKfL60SDiNObJn1F9I7nUu/7GVuGvOv7555P3ZSqVnu/P4KNfO5TZLPdAvuO7M1mFmbeY2LhaW5fodus0JZUN/coujDVbn4jhHlLoIfXWIdmRGZi+I3pkMdHbL4Q0ojfeZE/tXMhSpNmtT2HQXTAFyH0eAVVcTNJag1beCkpSInd/bnts3XmtjD9yV246IoXJyke9XJuE9Pr+JyyxitOmugb2Ck3+EV1cbd8C/zgJaueVmwVMgkTWQ3mHtzDtnOyrT4M1Ii3WUjv9m24ayoNn6RfNeQ6Ty4i03DCXllPz0x1e3U7Fw9uZuM3a+aeB2/JXRK2jw8dOLp0APvH+RvKRsbOBL2qN9402m+Mht6Ln3xLTWvyDu25pH5dZgZAKw6i5dJ3FrY/JOPq1H4RXjkB7LUFKuA4NKEX2ufPbxaLNxb5n/aSmyKJ/jZ7VGfY2txZOCF15eHTC8Z0h3w5dyid04ed73vRpHttpbpKp6NkrkL+vrNFQmwJYjb4YI18t9rq0Noief2FVQcsp8Mm64BnjtbVq5k3jWCi//rSDodzOud//3V/9mIHJnNng+OjyfDo9Oyk/3F41p9dw84259qfqVrxzstXO7MZ1O9X8r+dVzs7rDEA0mSeNGWWA99hrSJrtIhv71TFsHEg3FCv7mmP34oXdAmQyQ9sjZ294FQgqErNDkItNsufi9pLPOrQzWmv7LX/At2lG49ed7UrdZcd3ltxdZ+e4mptVnGRBrwdaqtVpLaUXQyVlkppSfvGshRJBU05gET0TdMTpmdtPj0T3Z10chqPJ1TPbGvobxqtR6/6Glz1kZqhGi85GnlPay2uEP3JaT3Yw9ik1hMb8VaoP7NZpP7K+xvqQbXxFncd4SwijDDqZlDTiHv8mgzUvVqT3cd2i+vCfHpqzPgmamxbbLi4osWLz0aFXkPrrrZWq1iti6Xfylt0GeeCv9hff/N83qbeeyEp4BbuZrQNUEZuAPLwb2/Ygu/UfsW4804YzW9vNC8if3rnn+1YcjIt6E7NODmHeOiFwWBuB5KI2EBC3yf0tA9zL7LTUM34/PaF/HzLBAyfS4qgWUugy1xR7+f36v3iggnQD79Y03BrZQB7DakMaF7vW/5u+lTYdXkfEQm4yUABz8+9KIQ9AtINS89ZgG04vLggYzm7uDT1nZTR7MeZl+2eXj5F5UUbejCN9Cp/lKZTkhXoGgc0PO2V4rf5ESqB7nlCEg2YzWrwE8d0s6+RU1DiBmBTf1uGCIXNuFFMFbGTnYk4IdHe677NEEPzccvuKAnZE6+iEml4/iVlaaZpsbzmB7JqTKgFLX79e2VNg+2Vpfgk1/6rFoElB7qTaE5qUwHc4RE6eG63gVhpLc55J4mdrVBQ+c6+OyODOrFztBeDwT6lb3dblLjcH+2PY0x5p5KrOzcWm91ZXPut1RbpEkPwNuMEQcwbtrUxRZSDbA5vb2bO1NtN6OXtXqMBAPeDvxwfVcrjS2e6aXms2Z800ZM5gRAxkP5tuU6wiG+hAvoETFYIoJEpF7FEHDvyX3GTw0/HD07/cVzXu2Ye+CnLulWbZX1hXG1ahmumMHRIXg7pJV0Yk/i13UvfIcY97H0nMiFmLPVREUvFbPaSLlcPeH/edDctm3WzsKDDZYUDnAY2HmnsQimZdmmJ/frZJko6COiTbMx7tsOOacTtMFZyreoG2bjctNDXDILotPMyH0a+t+RyCbQ+MaYcdls89C33khlTIKFGI5HQtK6Ept2N4+fXzPHS4WNZETmLhXPJ7tm/ilsPUCCkR1bg8MYawd37a3vuLZe8PXpwQQWu9lOV18kIslE9nv7SmG9akGsGhYGVlZXjPkiN9ksqCL2+7r9sb7z8m2CIyeW3tAFX/FB608xqnGqF0/q8aSmsmyLWyg+BlvYzuJUgJieVQzOreRvVgpiZFxsXxJrtbh1W2nKSmBAdy0k8tzewPndJ5x22qDN2s4MdaUceUItiitHE9sm012PY157RaNXvV7NmuHFBrtlQb3Yycgw0y19Q/yikObUavexspXqYuuxs3LJcN+UsN05FGuX2OHM+HA0Xy7nnxPbPh9ihinbiexfEiKKz3nkyYCXoSuZ4ECTZa9zBJLK7/sYluW7ubEPqmHxec/u/mKPFxDD64LiwuBNw8faZ8xv+ErIKgCi+GUMbYien341K8VmbN+PXjVjr9GTpJfPjgE6dX2lh3CxF8z1Yen4Y0PUFs5UalvUmO1fdzQ+Daza8W+T5MUxKLHCSFKQgXCKBTrPZBCVv+a69O7d2P1nzOVFnvNVUN5rAmG1cHGu2smHr68SLwEOT1GkajW7toes388vGi71um7iZQrpA657AMUDb17gjr+z0oHr0NjY+vdLXbBZ3SLZP3dhy+eh70TJebSM69coiv5wk7eG+PSPsfd2sfNj5lPj26EnkkxIENl0Azy+om+JivLgPDOMmrDx/rUhg1fVzhoGC5Nml14rk+fZrnENhrxQE6NsXqjV9ea9C2EyFvWBrOlXvBMixOuUnTIr2Vb79Fll8yV04x9Cke+lkUudq17YPvvf8PTCt7GTTKC3/C3buQNpjK9w0Wn2breo6WHyZuT8YeP4sGFgzfnmxKTbMbr5+8ucgjWSfqmoTLfYtMegfj+zQi+LtvDQp0VeB2Ul8dNkuC38ILYiLJfhgXXtkqmOnxxqg883rBo4/aQhSk+6nN5X31TM+FZjO4U5HSNsZR4uF5X/NN+LHf1QDSvEpEQTV5uxwTyIfqmz4pVodvEj4pJvQa8PyiY+MgGmxeUWyFOp6YQus6Nq+tPyZrd62jfvwAO4GC0r+e++2w4Ca7rYnpbj7mQPB44DYjM10n70tSiu9+ihfZP/+olJoXbAinIDna3zl/bbnXLPzY9XnxMDDEAiNFXgnr5NJbmNhrCz40uNHhlmpNdObu3I207PpcVqCAQHdjWi/4MAOfgZvo1XtXd+G9g5lnJBM02Mbn1m273O2xGjJ+jmkyGYikrFd5KPv+8ugR2UQo6WDJKf3EoR4og3ulNqBBR7ubMVJmu8uAbgbvHd7E+dQI4br0vNh2ZN5J7qXIITTVvGjriwbdrDwp3ChuKS+jX2GIqzpWUiW57ud4dPjgsMAqhhav385jaScPIt3Nodzhthj9UKVGk5rsRFZUtwb6ltwZkXHyfWYjcoxT7LdPsUu/Z6iVS1OfjuJ25zEb07ee812OtAJrOsCGzx3dJKIbej69iVdBfNZEt142eJnoX2nvqbtCysCFyTZFYzVnZtVTWH15EgznJ5OLFJQUj59lK5rGK16E0jBNhc437qhHIWkriLLDebLf5w513ELOA9djfzswsUXMiXdgcPy1m7oXV5Cywk9bx46S/6U3oCBUfbdzty+CHfi9nXozegyhrYQFiSgMcUfubC0C2s3oIUmz9845Ifk4U/i1XBdcaEl7x3wYRSlWd52wTPFujN+RPok3Z2rzPUuAA6yWa+YXggTQbE5NrMPWtkHomWZuMajrKUD+9J2Z3a2QQsp8Cc5/pneUfTcogGCfvHI4xeDPniRm84QeXLdyuQa8nIYTyxIUmNe/nhyNQ+Ls9BGoyq06DzGyLl2Mgx5JHc9OKbLM1jSCuG0giGmLDrap+7Kg9A+IXodGAoTb1l2vJtKc/3+ieCWYOlyPV3Eudda/bc+SU2iGeVlSkN8C86NuCRgxB5533v0csVHL/SgAfDKF/qoNG9uUNiHEDDbFoVXBW1x9pY3RRAbH6XZBQ8hSTa8Q2I+f5odOIv1LRwsYxZRqnDrKtnQWyY6tk/0q6/d/g0W3pewEUfPUQBVJK9rLZekuzvziMlk71JNzTVuzhVkuVtQNlisO/Mky5QZNL29mZGQhfmHjGuFOZeNj2Yu560N5TyInGurJNe+c3mVlTezyuDqguXO2Powc3+beJtM3p5YPnkfsmudsZKB1zBsBenidarLVT49c+JomusXx8z23dqtcBWxHFjBfcXCxNBRm6SgHz44fhBS9bDNHZWZ7SAPOatbWpsGa9JSXretI8JRDt++lvO4bWqOugGELCauH8koxVt85IO3RHi5D3Cp2C8kG7Upf9qKRpmJnm5kxyvqVbAX+0knGa+/Y78NGypdvtp56tJ/SP7Zfoq2I85xzxTi2CFhLNclidnMzT/Jh2JXBQoJ6/1v+tE0uuv145Vm1rldr+rJTmYdNK9MocF4/uWZCD0TB1G1E9icrb3qAtb6F/eaib/6t8D4+GVtq3upLLqCtSfUkxlPi3zJfawsoE/MhHbeNo2u3mXdsUNFXxUlPRIXG6HZxrmNC94kz+J11Lt6mahjQJNP9Y+Pn8uT4PekDQys0L70fMcWs5jMU0sX7z5QdiRcbQj4feda1jClgBZZoIp8H1pOsdv1bPgmTL5g/p5xV50QS+bepXccX2fMa+uM/xWwZvgSKWld8TIppLGb3IncXdqkSl9/Xl5+t9NJ0uaaXnDoND5tzLyflAS737aLAu7ILU3lgkxHaIv7zIuFLSi/pvD6GLxaZJupIegbQxdC70nKWo5kCpESDMQBMSQitmMswBuz8+vQt2bRNL48HzvWSGPJnZEdxHNm75lnA+qqyGcfGkh+OdITcLmurPQLpN/FL9DF3LrcvfBfL93v11SFFkg0yChrMPCH9zsI9y3GWppVBjwuHXKNdMgN/che34CbU7db2NZsd3va2vBI0dbg4UNoa1yDVu3cxi3twpoH62xqd/biz1eV2Y9Of37Pe/U3flyDV3+jbAtEXONR2PylWwUwmCSo4czGg9FKOl+tZUxSf9oLs/FS+2j51Fitt2vTzi7ftrMjmmLHItONYU959W7sTInod6cpyHrXZF06BkIsGAfm/aC04HD4wZDY6Y4L+7xEF7wWt93Y6R1BwGbmzHGRiaE3SOdjdOj0K69TjPi+y7aViQLJsbx/4HOSWEO+Y9OPH4iF15/6nvt1Yb8j1t8PTppG3pyR9ni62VLo9UrRKy9Esq9+5yJ8PByqLTF56ipmvZqNIHaubrKsz6MLc3D+pJl7ktswNNsrfbSX6Q1mR2WOCX8XrxTU+p5igqOkx8N02w4tZ05VL7tkaQdn07y9WLwLW5ohYb+6gCEjVkW3vIuSHmqIfdFU6BQhPAn0c+TMpe7bLFd/0uSMxyifLJJAxIICB8yFgy4e7aq5+tNL7YO1rvsUH2P5NrYRDKpT2w+ZV7MqM2kTUsiNtd3OXcZaouWJZrB3Yclql56z2rWuya8BaWgWLLMwT67Z8zaCsm4a2QeK1ZK8GsmsfmaHVjDF65SmZOTao2owEIeoukMWMB/fkcB0rAtKD7qIpzL0ZlYUuQe1DMEq2dSs6U3K5tfIDvgiRF3htLKyaGcfKJawVxaOUdN78WaEMyPdaZUGkxOB6lDRtvYPm7nBDiT1oLhimcv/kdIclA9WVSRRusAnnqhKfaCyK4npwrxgvDV7tT5JPavUkboD23uvl1fLnPjhsHk0h2sHI6Z5RXEfnoyhBgSpv+NX7otL2VIspij7Rc0VlY00mPKpS0uBVlixzbQUlsCqSSgOkt6l2bUUpzhLQisGhLueO5fJ0Oyu4qrri8Qizxwrpb8Hi3Ibp/JIOvi0/+paC2da27prtTPL4i2Fbskf9f0uq36kPFe+zVxFkfefrWuLzdvfXnvOjF/HljfCjR7ApeGvT441s70XO+/13m5D7+i7h3s7r3Z+trX4jiaFN1l+aLvMY96STMW1qR2Cu2h7rs3+fce7uPBt7a02GvYP9sf9yf7xkbY31A7++z//6/RofzKEPz7034/2B/zdqfZxeHxyfPCXw+FoqL04OJ6QT95PtaWKQr4H+UN/PgcdUVNX7Lz8kV9zpyuKP96LVdVSzD/VZ5OOacXBc3mXhTSSVpc2Ezi8+U/PzjjhPtnDlNe5EwURB3ejxYEXioGoJ65r22erpbFjX4OHD0igKJATTeaN/wTdjh4iOQ3AW7Q1O3bnX4V5oxwgPVzFgjHB0X5KAi7gzPpP1rkzd0LhBBYDUEAAVvX0lTIYT4faPMoATRaA29F2WZjYnizLT3xctiCYacb5mToBvS3vXDtjO5or02ynmY8cOPxT9uX4ivm/OOHV4MqBzsYOSvMvN/LBvChUhIwT9O2ZRXXMHqdQBCN4krRtLlrqDj7VPDzy3AvFlkOHBKHcBx5X8XEZUwcCSrHpcZZYi+u71vxroKysZq8yKLMR5ObLy8RzP/VAHUA325vawrln6dXoUhhT5EhEvh8ccRFEeg36ID7FTkO0siFguAhturgui0lIJO7biSibRjbMvkvUhiv1/qTBsThxeyyNw4PmZCNQjRW6gNeDdX7u29cO0ypfl/aJ7xGtSybtia1DE11eS2Jmmn1kUw8J/PuseJ/BW2Lsp/3Ic4ni9sLYKTNPg4UMAIQ08JaJUuHPfZbtqXiiJnnppUcgRHdSvBKTqGekh8+dgJ4RiI99CEPj8zTBaXqOJD7jwYaKf3pGgb2COu7xbwTRQroEwBKCTjuziWqJ13ZgyXC30dw1G/ETHurCSfymsTCdXcPQ6HohXTJM2jkEvhYOtzLxszit3YahNXpvm2KEkFTg2TKtwTSP7D3MuZTv+cjAV3LO6OEjMpyCe0cuF/15WvtkPBJTcdJrAf/0zJmdZXPB3nMVE78nFnEAr8w0d3PSn87sL/Y0iotLPvfqldE2XyUrXryMwDQ6s90zyjRKvtBNpAbvbZe/ZaJukP/t0v9rVM6xqFmRnOCM9BM/4PlKNSN7HUmGu94U6/IssGZVH+nEHwm+kk9Mfz2bfXVjLjjX/GnbFcknZ47rhOKnWRmTpbUzarWJdcTFFMtZbD9sx1Jn7Zgo2ZANDudp84qHg24aJNMA46+wBjOFu5VnAK8iGb2Ojeg0t2ba7IgKOdN552oaDfo/VRgj7i1/aDbberfVaPxBKJZgT5zZ1Be0lG+haELIWNI802D6wvFhIr2E9J0E6RUGOYtIVcR6Z6//l+fp0OCe+2chdJory7+kmu5sxvSX3kiLGDBnamfOjFZFs2uk5YrfkTmUN3WyYbiGYXsyU7WxyBOCRnkGrVIoWmnbNHlj5kYMHJk9W4LjrdyHEjOzzWtMaKah0iDlTVsKaYHftLMYWiGFbsZ97aw0hmDKwozGseYg7bOZ95s793hfjBVWk2tyHyqQzCaTtpC+Ic1IesObABWkpGfIVI6jN0XlnSriuLOz+NSdGOkQYNZAGop2mgki6R2eP2hZXCtL5kpOzZ6RkTFWhFzsyVhZqlG43uYiItNEtld8kZ+HUImk6uAMLjL7C3vmwIySD+2qgl34ikkKFMA/kwKWJDH9a6X4ZlFlkKA6iCXllYegJozCXCbjoO17rLdQY0VQjKLEwSuE78pTtY66rSdBBVHkj3ATJdpkE01iWVk+sd7otYMf/tAwez8OXdu/dGytH5IY3D8z+DjlzwOtP4dvWLAeEahcm8eaxGMOXs6ouxPmmiyr2UktWswHv2Ss8dJRn22kA7HJW0B6KBG0He8t8s9MLTrcQ0smA4iXNCUuyDgNOkNko10c30itBriB7NPxEADVUk9Z+s6XM+vXyGGeq2lXDyRj9teItOmLZKwQTFTrkk29ArlpzmZndN2a2Jjz1KBObbYzhs5WBOK9GlIgesdNn7fSyPILlpFrqGRXrgKuhlUCos1YmLooZJQE4cqiqK7SpLjGdWzSX+LXZ9ZskUyk2oUBqAqoCmPXSCep+5Iw0MurwsxqhAGdUxUmEY6hChPacP9GqAfF+1Q0xUESyZQECSqDJHIpDjKrDpJIpTiIVVoi0vYdOrzQIN2SIKloSkMl0ikPFdQJlcioNNSsVqhEUqWhYmH1Yn3qLy1JfAqrL9tmVEEybUYZJKgMkmkzqiCz6iCZNqMKEouBK8hEc2bmJfFUiWtI4Q3rpNd0YSY4m7OlmdxgH+tRyZSTjdVCRXhRU2HGkq8MF9QLF9dBVbhZzXAZvaYcPzL6Whkmo6/VYYLqMBl9rQwzqxEm08pUY95F9bCY6TfKIEFlkEy/UQWZVQdJSmQo7Y60OAXvk7IUvQ/K3yelKHg/q3hflf+k03eS97xrJ0VTvIpLpXoVFL6Ky6J4NSt+FZdA8SqT+QVdWiPT9/x+i6zoXJfoHdWqhTwRcaT1+bJ2L8/miluTFK5KSciJllhAqq+rjAI5XOmQqEpSNWY4YXnzkhfO8jUov0+XoYIzNwrlKQI1zOPNETEWX2xLLoDB2hkZwu6xh6qZL7W9/tFYOxhqg/4eeUoCnYyOfx5OIPZoeDLaHw819tfg+GhvH1I6Gh4Oj+DRmPwMfhpOxvS7fe3D8ZiEbtC/dvqT4cH+cKTtHWofdUh3zPN02B8Njk8PhuJiJZ2gpyVz3nYbRrvR+jFeHvfYtTaAR7UAfB2Av4OA3yvTpt6C/OnCtip1FwozVfpiRmRxexNGsCENv9tfbm/Yim/wihOi+b4W3bOOWyRc9Atu/y9pqIA8hc1rFtaiy/wstAakAkrwiL7QtC6Sm56Ue29BLgCcqv1//zu65jie2xsyN0zc+tvahXXu87kgUA8ub2+8pTf/ugDnCDTRS7bLyHJH8n5lk4gkmHt7Q/JDd9lpRjx2S1EsmJjZQIMbCE6gRYHjMswI6ffa5yj4NQIR/Y3Hmdr05D5Ndm5rg2E/n21WOKG0AMlhgqMc7dCPpiEFme0dD1/GHJiYXh9oLnwGckNZevBl4cMkLbrdc3vzWliYYs2DtwHeNpppP2Jr/KDRxOU4vp1FZvwe7PEnS1uFGw5GvCV0OVcs+gtro2A400CK9e6m1EfToSXz3HYLngfq58mIknk+K3iejCWZ51ZBfmL1xnUMFXWaefFhknPpYaB4mORZfDhTPUxyKz60VF9P8sn3mKiLtbOA3RrglWmI68YsgO1eOz5cH6cw0bm62lnrEHw3Sou7QlMjqXlOflVzmm6f53dK4l0O95pMB9wZNBtxv8pI3vKY0j4WPLdcOHFihZn9BdIW6Q6EvH4uBZE2e1x7Gu9TyhtMkAA7zCFv1icKOt6+obGldW3ykjnWZG/ajZ7Z+zG325DpLGy3IU1h6ttJ2nEK8dKntxBe022yQzK34pMyPrz7tiLY3nDyU/9IsALSvSxgtpOavLQu+W6xYACxz4Iecq7J29BKVqq9jJWbBll4YTCd2xfy6h1778zO5975X52ltBgZD/FndGMBzuhImeilDS4JaRHFw3GuvqTrWqrA6fGTM7aHLkVRpU9+IeqYT8ctaBAR34aKozUbFdEc9wIGgCidE7PPdRTxgtjxxFl6LEuUQVOvKBZR+p5P2g6YlorpdSbijNsFySaqvEuQCR2FmR6c7CnzWo3iOXpPbIHwQ9oU0QGZTbp0sdhMdk1LAjri+ZulFV4VpyeuBhd/tFXQG1VbK0ZGH1Gzmg+YjniYiugW2Aw6S0/75c1gmi8mM7pDmM1yKnKYrohTmhbXTAuqth3otdkgsSUNlLmzcw92iPm2piRrxQZb4EVXcMpLLnJqLrA9Xdr85S3/7Npibh+NnRrgPU6Ky7fRYss1c5yBZZJ6Zp0mmy/i5p+ha0SmJDSc/07nC/bccmIlolin4kcH5t7USvcTy08owJSO5JKodDc+8xRvTzHmQm6ZjHrrymzdxjvJ6W4SEZq1ZPHbcHrx+PBwfzzuj/b7k6Pjg4bZGx4NRx/3h/3J8eH+n0+Hw0n/9F/5s3H/YDIcHZG5zKfhmG4kUZaloNzzm7nXZJpJ6jE/9eLZPZvJG0AUNkVa2exzNIM6IMa5XFjhaIu3tP38kSjZIoHszBy+2SacNcnszGUiOUTF+9mgvP9mgpKqndM5NBmbQ/tS3tblFZCJEjiWPNDoqlB2ICUVb64n5tRZsLSnQaoX5A93s6FBDjkR6MI4J4aFK890sY4ODwp5Ec05oyips2lyfC7eF+x2202z+yM/Jg01rQiosPLEkZ+ur6QrGsmeuJDrxG6Bgw9UHWQ0U2LZZNqJIRwbseloBh7bik6+8HNO7oUXnIE3qoBEmFtU2CT9xh/+IK7YMJXDFRepZ1JicPJcoBLowY3Idb7EvVhvtc1Ot9fmmoA1Hr7tbE3zfQiMNvAhLtikDb7UQsq1dM6Cy4W1PFss5WGPV0iiXuW6EDcoyadJB4Vzz9LxMWG0Tk8kzOg5OenQEReH0Jukc7dxgyftOFG44jgntArB9r9I7sPwT+aUAxlXfDLASpvr6Xk11dvYWGQnKQXHS0LU+GXcmDL72sk5zD5z6SVvQOVfn7CzLEWJEL15Ep0XJsJfS4m0eplQ4qHUYETGqNCevf+alE48JTqKzeZ2+16JyBlq1EpLmY9W5y5x5c/nkkg0UG1pNO+RhJSZZrYKVSmpJdFaPab86Vw1CIF/cgIwt0h/A69avL0185IrjCGLPCuvvf5k+GH/qLa8s/2gdny5xFmR5ZJRS9pYMVp5a98bDOuWO9dUa8WVy5wTnZiE8qu5NlkWRdZXWVUTX0oYMq0ajzdZZZINJiVqZhvPT8Sy8oIT2DY4iceqoKC0yrC1kr/OeEgrS14OKydvKqOMrCXgpE9gCSjpXtmWogpaLm8WY8I8nvb9Inlng8k5LghNbD56vjNew+eZVpcvF1r6RG40UdxnqNtHOtkM3Cmt8hGiNMl6I8QqSUiZ6WSVVtl9kbpC62ZV2v0SXblyFWnfuXKr05IrN6vdjmEd5YSYxWS2Vnc8ytbJSmnI+jnbhZVJqUeHrKDrRJVlkRUvTSGoLYaswqobXZZAtqtkU1EXPquKK2LJKi6b8ROSTdtP753GijOrCHLh5JLkgtOtbWn4HMlXVwvso+qI5fWoiK+si4J6rBtdHoyyneLP/CYmpMPlmVU7YpDybjH6WNtoynaLOlHLLWMxBbXJlK35khiyssx9il1xXVEf5T6/WipyY8r2rILE1MLPatd6keWGlE1jIi2dy8vmhaFkMZcn2adbeHZtk6P807VTK5+mVyRab5q+WiKykiyb7cUW++AiMb3zc2J18HLre8IXQupY38qw5TppMrDmtjvz0wsIOZ0kBpETywl3kG4Yx6llNbEURk4uK9/JXkSbCMCakvRy8pECybLMFRY8ktKFkXxGm7liKwOXt4gJdXvAz5ikDmmKWkRR8PLpx4T7txmSlpz/Rk7e6tCyfs9GEm5ySwNufANZOY8vjyT37aywlXETNZH9clYz1IwtZYGvL1tz37ZmX/fdsZV6A4h32OJ3WZRO0gyTEAMycfT783nyrWDPtpdJ6LidiAub4+mVPYvm9ozUyh7MDnMbNNISZnHwTksIvtoyZHGiieKsvaZYkkFTTGuFlcGS7DUrkqydUksqaPH6XUnxGkIKtRfiSsqml6V3txqstU5WIqROUVq1k2iKrTS7vlXyZVG4ysWr1eLKK1Ml2e3l4srrTjVLml1ZKsmtkY+WXTQqjt0VY99pxadm/11l3aY4yZ5enuQKay13l0n1QkmJTEQFtNJqR0mPbVWlWT+pXjapGqsQJck1SpKrrwJEjZRbOSj5utj+qlcCakql7py+pDxiIxZn7jWruM48vObAVTK9LmnDUgqrzZBLitipTrV+kxG7WWZCW9Lxi2PVnovWtLdWm0+W1KZZYIaIM8WaQ51yGlizGYuTvZIohhwlnR+VxBFLKM/aSsol5U05I6sp06K5Vs0BuWAaVRJb1DXlU6OSvtStSiQ/yylOjZ+eSA/wfLCcOZsBx95cuWMr1/4t6zBtBkkJOGOWM3YG0J4NvHm0cEWf3yyla8cPI2ueeV0GypBceNZzHtsjoes4T/zJW9g/XHqh9+4fzQ8Q7B/NPbWXxek0sp15Pwqv4JwdP5lU7Ga1XckMb5T5LZZgE42Ma852CgSCos8d95dPjv0bGWYyDo7bCvRalv1zJ0fmS2caervsqlkQOy2vcKLcMCVXnUtvebp8sZOpJGjWkfuavuQecPdqwy92Xu18tQMKqRS8UrbNVFpw/YpIK3aAo5CYwp9sFrPSuIfE5rv8BG49oYFJmZHQBTjRfL0Xl2Bqkz793gpsLibW4fMCesfZiRW4CYmfsTLSsW0i0rGmx9QHh3TsINJxba0KkY4pWAORjoh0RKTjFrU1RDoi0hGRjoLtY1Kko/lS+wjXsOE6x0OkOup3ojqSsvWjaQRuF5KpcEJ2fL2VYEcyCXnoYEdVERDs+E3Ajo2HCXYE3tf2gR07b00EOyLYsdBCQrBj0sUfOthRNWQh2LFKNgh2VArnEYIdi9sAgh03B3bkUn+IYEdFgymfuiDYEcGOTxDsCAabBHa05tPdc3A5ZJhm485oR8kF5b/vwArIwppegQPKma0xxB6cwSFpOwweMbO/C9RRoScQ6ohQR3V+EOrIWyBCHcUFEIQ6Uq2ylVBHOFMT3BfqSDfVhGFR8JG1aaxja7fRiZ88K8Q66i2NrhauhHXsQBy9I0R4nFhHvYNYx8eFdTS7FVhH0qX+8KBpjmnBsjDHTictVhHMkYWJXXAizHErYY5c/SLM8YnDHE1BHYoSf0IwR2akIcwRYY4Ic0SYo1pwCHNEmCPCHBHmiDDHiiBThDkizBFhjkVjxpbCHDukOuVd03qbptoU9h8DC+h6HpmBwzYWRfiFZBICG6ScYWhr57f/J3Ro9MCb717ac+3FDECAz/f2++8Pjp9rb7U9JyBpgTjJ5/thaME33lsB/RZN5wBWqgLtmH/ppbg6qeA4NhstWIrKcBwfdGELqISkpO20QSCVEKmEd6ISGlK1b4pKKC3SPwEqYavZUy2bZ6mENIyaSkhSaJdQCaFF9Of2Fwv8vPA1uzI24aB/8Gl4IAxqyCbUkU2IbEJkEz5INmGylwVX3AJkEyKb0HrIbEJxnFsjm7BjGPS8BrIJV2cTGo0msgmRTYhswrvERTah6tPIJkQ2oeqryCZENqEidNZxPM80sglrjRDIJlzNTX79ykU2IbIJq2MhmxDZhMgmRDYhsgmRTYhsQmQTSpGQTSi9QzYhsgmRTahMq3YSyCZENmGaJLIJkU2IbEJkE1Y2GWQTIpswiYRsQmQTIpvwCbIJVT4ZkU0osAllAa3IJjTvxCY0uy1kE24vm5BUy8QnPRAuN6yCJTQQS7i2BoVYwhQLgVhCxBIilnCL2hpiCRFLiFhCwfZpUCxh46X20fIrzOdtZRKad2ISkr6mPxwcIZl3PHQcoaoIiCP8JjhC/YHiCI1txBEa9C4u4ggRR4g4wkeOI1QNWYgjrJIN4giVwnmEOMLiNoA4ws3hCLnUHyKOUNFgyqcuiCNEHOFTxBH2MjjC9+BhSO+Yu4d7d4YRDvsH++P+ZP/4SNsbjrXh5JT9o01G/U/903/V9v77P/9reDAcTEb7g/3JUHtxcDzRmi+/C5BQoSkQSIhAQnV+EEjIWyACCcUlEAQSUq2ylUBCOEgT3BdISNqAMDAKbrGyOMKYmPZsfThCI37yrBhHaGh0tXAlHCGJ03rbagoRHieOsG0ijvBx4QibLVN/1DjCTlqwLI6wa6TFKsIRsjCx103EEW4ljpCrX8QRIo7wqeMImYmGOELEESKOEHGEasEhjhBxhIgjRBwh4ggrgkwRR4g4QsQRFo0Z24wjvOu+qbbXPxprB0Nt0N8bDbW9U+1kdPwzibc31EbDk9H+eKixvwbHR3v78IGj4eHwaEI/szcc/DScwL/aQV/7cDwmoRv0r53+ZHiwPxxpe4faRx3SHcNXyavD/mhwfHowFFcnFThC02zC0kIGR9gETyUHsIWcAHACbeotAJwE+6janLyLffJoM1Lm25swgv1n+N3+cnvDlniDV9rc0vzbG76RRbeo48YIt/qC2/9L2ujtDacU0rACA9HWSEMC8iFc43yleJ+81GY77J6qD06Rbm9o4Eu2Xci+SvJ0ZYeBdnl7497ekHQYJxE+4LGrhmKG5Y/ANQIn0KIAWIxk3g3WiPY5Cn6NoOh/43GmNj2AT5Od29pg2KeRb2/ILHXJJpwQmQS+IKV1HejD2iyKBaJBLwiByGUTWbzYOx6+hOBMZHwDUHPhM5Ab2ODPfZkkRjdubm9eC4tNOTIjqfRG2jeQzIhkxjuRGeVq3xSZURf3K54AmbHd6JnAVawgM3YNKQWRzBinUEBmbMOtdT+eaJUxGfeGk5/6R8LIjkxGHZmMyGREJuODZDIaOjAZSZnhSkGATEZkMloPmcmoC+PcGpmM7a4OxgMyGVdnMho9o4VMRmQyIpPxLnGRyaj6NDIZkcmo+ioyGZHJqAiddZjPM41MxlojBDIZV8MD1K9cZDIik7E6FjIZkcmITEZkMiKTEZmMyGREJqMUCZmM0jtkMiKTEZmMyrRqJ4FMRmQypkkikxGZjMhkRCZjZZNBJiMyGZNIyGREJiMyGZ8gk1HllhKZjAKTURbQikzG5l2YjO1mc2Um42XnCpmMW85k7D1EJiNpWA+DyUiyfOiQnIW3f/eZF2HLX9zesEPJAqasJBxSGZHKiFRGpDIilXFr2hpSGR8HlbFHqYz6S+0nizTq3Y8WvYn9MPGM+p3wjKRs/WgageuFB8JohHmINDcktuADYzQqi/CdGI2lYw3MUFdvm9QihytIu2TCx8LaWzLwgFbeOz0cagGpmxlFseZngJkA96BfFEEvy4V+pyWIVOhbJXBQMFSes9ubCzLBYQ5cdizwF8OdIUg2QFXw+1RGNV1U1O1bRxdtPFC6qL6NdNE20kWRLlpi4SNdNOniD5wuqjS2kC5aJRukiyqF8/jooiVtAOmiG6OLxlJ/R7fIHhRdVNVgyifdSBdFuuhTpIvqGbqo3tXbzbtyRfvUodwudSgH3knPPZe6eYVDFeCHhrt5/fed1EUspP49qKIqDYFUUaSKqvODVFHeApEqKi59IFWUapVtpIrSo2BBEVWUm4NVVFFYhYPxUPDo9g1wor34ybNinKiu0eXBlXCiPY0MXGBhBo8bJ9ppI070seFEjbbee8Q4UTMtmIwTbbfSQqlhoixE7CwWUaJbiRLlqhdRok8cJdoWVKEo8Q2gROkitPq8rrarDUPSjWP7CrghXLlosx1wQW8xEAkFlNgAGYG9bvCdD3++J3N8G5grJJnx/l769wvTbLS75PH749HesH/6r/fljnIjDrmjyB1F7ihyR9WCQ+5oJXeUDPo9IxUhskeFgRrZo8geLQozRfYoskcLXyF7FNmjmRrcDHu0B/dw622tZnZWUzwmhIwrD7CStzdw8v/q9gZomeKaYw4S2um1G5Qnl4GE6uDsZaPZ0mZkbkVZo85iYUfnjDoK+z10H5rMSZe2S8KEdJK6cwqTUfLbxIvmXsTXmZQITFqkblpFiMBEBOadEJiGVO3fCYEpbII9DgRmS2+ZnR9z695yZ6Hr3moAZhy/AIBJD+eQcmiAwbQF2SoomCTzH/un74ejiTDIVGEwdWGUQQwmYjDFXpzsaiIGM3gQGEx6rs40O6wBCltoD56Hme5KGcJ6x8PkYeoNEu5w/2h/PDkaDfeG4/7o8Gg4Hk767w/2x+Ph4fBosnc6Ho4+7Q+Ge/tHH0b98WR0OpicQuiD/t7Rh+HRmPwab13Azkb8O93WiP8QxgEkZj4sYqY4JK6RmGmwxVUkZt6BmNltmVtLzMxM6xCZKW6zIjITkZlldB5EZiIyE5GZiMxUdUhEZiIyE5GZ908LkZlFskBk5kANtJDXDhCZichMRGYiMhORmSqySYEaLwqOyExpVwaRmYjMLMLBFGgGRGYmwRGZuSqrCpGZNYSEyExEZtZIu3aSiMxEZCYiMxGZWdlkEJmJyMwkEiIzEZmJyMynh8xU+lxEZGaKzMwI6B1nU9ZEZrbugsxsGWZnVWTmReszIjO/ETITGtgHL/Jdhy9yrsLNbD1EbiZpXQ+Dm9nM+NcBPzkDou64qzwBMFMaEtmZyM5EdiayM5GduTVtDdmZj4OdqQM7s9N6qZ1YvhMgM3ObmZkwEZEmh8QOfGDMTGURkJmJzExkZiIzsyJzD4uZma4ttLYKmdl5ayIyE5GZhYb9FiIzO8Z6kJmPnZGpNK6QkVklG2RkKoXz+BiZJW0AGZkbY2TGUn9H98QeFCNT1WDKJ9nIyERG5tNjZJqNToaRSR1oDYzR4Yfd0XDw0+7+4GT3cEz+f1du5iH0cfB3+u9kSpywJeDPyNXAX0zoe4vbv4ds83BB2oCtiR/95vRMle5AeibSM9X5QXomb4FIzxRXQZCeSbXKNtIz6amwoIieyQ3FKnomVGHhUCm4fMsiNak3yvUiNc34ybNCpGajpdHlw5WQmibE4cDOx4zUbDXXgdRkYmtTsbUFUT8ipKbo4XKrkZpmt2ma+qNFah6lxZKBml09LZIaqMlCxN5mEai5lUDNlty5Eaj5RIGauqBdJJFvgqjZKDnHq+1qg+HRZDTU9oYamDrDEfnRhhP293jSPx31J/vHR+TvsXZ4Oh6Sf8irD6P+0WAIp5QaDZ0kctIf7Y81/b7kTG7AITkTyZlIzkRyplpwSM6sJGcK4kNqpjAaIzUTqZlFYaZIzURqZuErpGYiNTNTg5uhZuqQ42RnVd5YrbOvGqMrLbZZok2vyGTi18jWbm/mtzcx2YXu8i6s8PaGDGjRF81x2RpAgqgpxmrqekOF1dyObBdwM0mevzU3s5mGQG5m7uHD5GbK1Y7czDVxMw2zKWzwq7mZdHm7gJvZ7XWbaeGzLEwiyA+27xIheoJgFdBM0gLGp/3R8N+EIQaZmToyM5GZ+ZSYmZ0e3Rh/XMxMuuNPvmKIu/EPk5nZ1SVk5kF/cHoAPEy2j7E3THYxhhP4I93C2BuO2QbG3pBtX9DNC7pzoQsqH/GYDwuPqQuj3/rwmJ1ut0fPWKyGxxTtgaeLxyT2HOIxEY+JeEzEYyIeE/GYiMdEPKZK3ojHRDxmHaEhHhPxmD8iHhPxmOp6RDxmcQpqkwnxmAXaFfGYiMdEPOYM8ZglwRGPiXjMJDjiMRGPiXjM4sUrxGNKCxh3XvFBPCbiMRGPiXhMxGMiHhPxmNkBGfGYNuIxEY/5ffCYSqeKiMdM8ZgZAb3jHMqaeMz2XfCYbdPQV8VjXrbniMf8RnhMksSYHaxfEY3Ze4hoTNKyHgYas51zlLNDZqZw2ZBfERJIMhVhEY+JeEzEYyIeE/GYW9PWEI/5DfGY+sbwmLABp70w+i+1gecH9u4s2h1Hs1caefb+pfaTRRr6Ln2D4MxtBmfCFEWaNhIr8YGBM5VFQHAmgjMRnPnQwZnVfJ3CKXU+K7XyicDNbwrcbCBwE4GbhVMFBG4+WOCm0ihD4GaVbBC4qRTO4wNulrQBBG5uDLgZS/0d3WV7UMBNVYMpn5wjcBOBm08PuGn0mhng5uHJ0XAyOf5L/+MQ5ih3xmyeaEk6GiSkvdEyW4//Luw9arvwiK6Afg/GpkpdIGMTGZvq/CBjk7dAZGyKCx/I2KRaZRsZm/RoWVDE2OS2YRVjE9p3ZnQUvMVlyZomPzL1bH1kzV785FkFWTNLYoTAJWTNHsSBeW/wWMmaILRXr4ymQX7MdeA1yx2KIl5TGhc2jNfsNXo6iEoV5jHjNc1uNy2Tmq/Jg8SuaRGwWR+wKbk43ixgk+tfBGw+AcCm6GU9B9hsC7pQFPkGAJvg27L4KLAwHddeGNDzyZP+z/3BYP/4vrhMboshLhNxmYjLRFymWnCIy0RcJuIyEZeJuMyKIFPEZSIuE3GZRWPGluIyO7CSnNkgFZf2FATLVrPT+TFPsKRJxQRLW3PtMPS+kpk4/GH0tPPb/xM6dHqi0bog8z1gaFwEmh1qKcGGbuzyJST66vYmtM7n8ZI7vP8f8x3wWqbZrsYvqgQC2k4mV5K8tlN5fRNypZGGQHJl7uHDJFcaUrVvilwprVHnyZXCvpFErhTgjQ+LXGl2TB02S0rJlWzhuABd2WrQlaoCdGUb7gm6MZytEFxJXhycwnrS0b6g7hFdqSO6EtGVTwVdSd6aTbpTvg3kSjONfF90JdvSab81e8LKw8NEV4JGEdGV+0fkl/3h6WhvODgejYd0e4DvDQiafKNESl1ovkikXAuRUhzU1kek7Oo92q2QSHkXIiWxwpBIiURKJFLeJS4SKVWfRiIlEilVX0UiJRIpFaGzuACeaSRS1hohkEi5GhyhfuUikRKJlNWxkEiJREokUiKREomUSKREIiUSKaVISKSU3iGREomUSKRUplU7CSRSIpEyTRKJlEikRCIlEikrmwwSKZFImURCIiUSKZFI+fSIlEoXhEikTImUGQG94+jHmkTKzl2IlN2O0VmVSBkYMyRSfiMiJTSwD17kuw5f5FyFStl+iFRK0roeBpWSCPh4eXvj0/s+AfMvA2tMXJwxoaU4GLIokUWJLEpkUSKLcmvaGrIovyGLsrExFiVoA+2F2X2p7Qfg/A2Rk9uMnIQ5iDQvJCbgA0NOKovwnZCTEg+xgJAoPakG94kfvRu4T+TdmSt9rxLA10AAX9UErVUXwGcggA8BfCX2EQL4HiyATzlEIYCvSjYI4FMK5/EB+EraAAL4Ngbgi6X+jm4qPCgAn6rBlE9VEMCHAL6nCODTMwC+/vHxrt5rGK3m7uDDXfF76a4YPI1c7fbm18hZ0gM8sMsyu72Bo+Xsb8q5J2X0yZvvQd9T6Qqk7yF9T50fpO/xFoj0PXHVA+l7VKtsI32PHqMJiuh73DCspO+xwyPp0Cj4xcqy96jj2PWy99rxk2el7D1jVfZeW2v03sIqffBY2XttQO+ZjRZi9x4Xds/UO7rxBLF7RrPTSgul5u7FYWLHmgjeqw/e04XeuWHwHle+CN57AuA90UjOgfcEZShKfAPcPbi2A7YdsaeAMxRqt3/T5j/8oWH2fhy6tn/p2Fo/JDFIhwEOgxV90fjzQOvP4RsWrEIE2otOq6G3tF2NzF6d4L5QPm6iIZQPoXwI5UMon1pwCOVDKB9C+RDKh1C+iiBThPIhlA+hfEVjxnZC+bpd8TKhNltl11SbehHsk8KvH60j7cUBfXHgXITa8cXFS3H9MMf26zaazaaC7bfxDCnpfTQ3SO+LkN73IOh9urjUXZ/eJ2xmPCx6X7vRawOar5Tex5ef1fi+OIUCfB8Z0wZXlj/3Qo6PKiT4deCW5P5kPPhJGBqqAH4NYWxAgB8C/MRenGwuIsAveBAAP0HWil2XbwfuS+Pei9vX5jtCZDgyu8LixMPk9oFfpcHx4eH+eNwf7fcnR8cHDbM3PBqOPu4P+5Pjw/0/nw6Hk/7pv/Jn4/7BZDg66k/2Pw3HdHfhhEQcC8p9o0w/cUREpt82M/06zSaY4ysy/cSR/wkz/YzG1jL9YotbGK0R6cc7LyL9EOlXRg9BpB8i/RDph0g/VYdEpB8i/RDpd/+0EOlXJAtE+g3UDvflpQNE+iHSD5F+iPRDpJ+KvFCgxouCI9JP2mlBpB8i/YpwFQWaAZF+SXBE+q3K0kGkXw0hIdIPkX410q6dJCL9EOmHSD9E+lU2GUT6IdIviYRIP0T6IdLv6SH9lH4NEemXIv0yAnrH2Xk1kX7dOyH9up2VkX4XxhUi/b4R0o8kMWYn6J8Czo+0rAeB8+uRQg5DiygP7gYwAOc0SW45uC++OUNvIwYRcP3gmPsr+tbS/JgZA3FpBIf0pevkTG4KyvoGn0O8IOIFES+IeEHEC25NW0O84OPAC5oUL2i+1D7CtXC4WoJ8wS3mC5IJkTRJJTbpQ+MLqorwnfiCpaMMTJVXb5t0ZgB3oXbJzJOFtbdkyIG+vnd6ONQCUjczy59lhx9FgHtwHIqAjeVCv9NaSCr0rRI4KBgqz9ntzYXjOszpy45FShS7ZZBG/6rg96mMajKmqNuRjLkeMmYTyZhIxlzNtEUypoKM2b3TpkKejLlrXZNfE7c3jx6UqbK1EJRZJRsEZSqF8whBmcVtAEGZmwNlcqm/o1t1DwuUqWgw5XNuBGUiKPMpgjIbGVAmc9rVbN6Vkdk/PGZv55a29G9vltzD3iv6IPYITP6yNeAJOfS3pTP3QtLlvwsnU6EqkJOJnEx1fpCTyVsgcjLFNRDkZFKtspWcTDibFhRxMrldWMXJ7MbeLGEyHRQRMk0e9Nm3JWQ270TIJH/C5CR41IRMQ+8gIbMWIVMXxLTVhMyOboKkHishkzdHFSNTN1tmWiw1IzMOEzuxRUbmljIyqfpFRuYTZ2SagjoUJb4BRiZdsd/o+V9tVzs92v80HI33J0Ntb6i9Px7tDfun/woHl4gqIe/jJ/cmazKjDsmaSNZEsiaSNdWCQ7ImkjWRrIlkTSRrVgSZIlkTyZpI1iwaM7aTrKn34FrEfXZb+ZRuafuAB4HdUrrPO2V0FDIf/O///C/7y3Lu8X0VPuWD2RpsBMfhHTcIyYSX3WaEAJe3N65ja1MyOyEPf43Yh0hiRByw4OOEtzfw6L3nz2yLj5/FHM+W3gDuXobjacIYfcBKHQCLRhPrTLOYWGhwRu50L0jda7d/0yJX4ywrmE/SjPVJwwjoRQgIcGjd/j8hvWMCEjiOrmGdQ3tBZP1yJWGDvLZQ3K+FxaIcppQIu5m2UsSUIqb04WJKhT3Bx4EpbfV6BkCESzGlfAdAjSmNUyjAlJI4I3vhCGJVEEpJ1MP+aPIXYYCt4pPqwgiLfFLkk4rdN9naRT5pgHzS78InNTXSCkmvaPWEJZ6HySfVYVAfTvrvD/bH4+Hh8GgyHk6ORx/7R/vjw+F4b3gwPBoP9z8esXenJ0ej/eHpiDzvj4aDn4Yj8jOckD/3j46OP/Un+8dH6Z7O3jDev6H7OfEfwkCALFNkmQLLtNOEKRuyTO/AMjXMZnNbWaaZCR3CTMX9ZYSZIsy0jJuEMFOEmSLMFGGmqg6JMFOEmSLM9P5pIcy0SBYIMx2oUSPy2gHCTBFmijBThJkizFTFnClQ40XBEWYqbcsgzBRhpkWgngLNgDDTJDjCTFeliCHMtIaQEGaKMNMaaddOEmGmCDNFmCnCTCubDMJMEWaaREKYKcJMEWb6BGGmKueTCDMVYKaygN5xamhNmGnvLjDTjtFqrwoznTVDhJl+I5gpqZaJT3og3IRYhWWqP0SWKWlYD4NlSmrl0CE5C2//7sdXHamXWXr2Wru9mXqkAPR6oh1qgQcjNVy/3NVy0Qbe1e0NrArQ+44T2/cdejQ14FUT80y/zSeRaYpMU2SaItMUmaZb09aQaYpM0x+RafqNmaYwL5LmqsQ2fWBMU2URvhPTVAJuFiA4pSfVZEjxo3cjQ1YrgsLpUL7qa/XkSqKk/jCJkjoSJZEouZphgURJJEquC4CmHOmQKFklGyRKKoXz+IiSJW0AiZIbI0rGUn9H90seFFFS1WDKZzxIlESi5FMkSjZVRMnx3v5kd29/dPR+t9G7M1uy3W5o//2f/1sbHRktwEn0F7c3c8dj7jj4Vs4MHM2S1LRZpJ2T+nfc70GVVKkLpEoiVVKdH6RK8haIVElxHQSpklSrbCNVkh4SCoqoktw2rKJK6jFWUhodBddfWcBkzMB7tibAZHPX6MVPnhUDJvVVAZOQrkaGMZj3BQiYrAZMJmA4HVxRImDyewImmy3DNPUywCQJ9AcSygCB/oEFbzbZL4bR3Dr2JGkQPx2f0sMl1fBJwfu7TJ80SBnTgqnpk3GY2EUp0ie3kj6ZUTJIn3yi9Ena2b8RftJoNL/BcU0SeC/ed9RGtzeX5F9rbmtDEVPwiq4buNxFPXzswOO/s+SPwPEsmTnt9n+NiPXvuLb2ottmBMuT4/3J/nA0vi/BkhuQSLBEgiUSLJFgqRYcEiyRYIkESyRYIsGyIsgUCZZIsESCZdGYsaUES9hHuOOeLqUl9v2pNb0iocbRTCNRB/7tTRJtRsY1mFTSwzyRH2gBkBt36Pdu/6Z9jD5b7u6hHZDqDMRl0ByBstPr6S0FgdIAL3x3/qRmuxq0PZh+kolWDIO0fN++gNU87dLxLT6ptb9QOmVIcY8DMvO1yFfpLPnA0n4ik/r/wS/EwnsyHSfJB3ym6zF6JaNLWj55Ah0P5tNxCJLbKAhIesErkncy+1uyiRydhMNKLt2xiI0IJTaSSuhbYyP1NARiI3MPtwkbqYuLsKXYSLnaERu5Jmxkw+gJxxrU2Ei+dF+AjWw3DTMtfpYISbL0Ye75Me+xjBx5cHwy/DdhYENypI7kSCRHPg1yJN2KNJsNuov6uBCSLdhSI0WCw5zBA0dIgiu/w/2j/fHkaDQEMuRk1D8a7wML8mhwfHD8cf/Pp8PhZHx8sL/X3x8NpbCD459I2OOjveF4MhyN9ifHJMR4j/xnAAmMjj6S//YPhsOjT/sjkiDlUPYPj476H+mvwwn5AP0NUjs6Pv00PDgY9v98uj/p7x8N6TZMvAcjjCMPCDxJMpwc3Iy12e/CFhuiKKtRlLogr3IUZdNAFOUdUZR6r9MythVFGZv6gqmAJMrYpySSKJFEWQK9QRIlkiiRRIkkSlWHRBIlkiiRRHn/tJBEWSQLJFEO1JwIeTEBSZRIokQSJZIokUSpAoYUqPGi4EiilLZ5kESJJMoiykqBZkASZRIcSZSrIqCQRFlDSEiiRBJljbRrJ4kkSiRRIokSSZSVTQZJlEiiTCIhiRJJlEiifHokSqXDSiRRpiTKjIDeceRjJYlSZEqsiqLs9rq9lVGU5gWiKL8RipIkMWbn94PVWJSdB8miNC8eBIsSFuBzLoN+Bg+QU1vjIo1JaOVBkf2I7EdkPyL7EdmPW9PWkP34KNiPPUA/NtsvNeqIGrGP24t9hDmIPDE0Lx4Y9lFZhI1gH8XGqsY+lg4wMD1dvW1SYxzuH+2SyR4La2/JaAOqeO/0cKiB/5KZ5c+yI48iwD2gGEUczXKh32n9IRX6VgkcFAyV5+z25oJMbICsos13LFIiwQtDOvBXBb9PZVQDS8Xucjdgqcj5NCu/Jw5+leDRBoJHq5YXWgge5e/WZN8eVZi2CB7dlBTyk72aKMJi8OhjZ4wqzSpkjFbJBhmjSuE8PsZoSRtAxujGGKOx1N/RjbAHxRhVNZjy6TUyRpEx+gQZowCFlxijg37/4/DIgJnJXeGiR3YYel9JD6ZxwCdX9AV8oLI9wWvArfkOe/k58p2ZQwsafA+4qEpPIFwU4aLq/CBclLdAhIuKax0IF6VaZRvhovTYV1AIF2VGYSVclLQBYVgUvLhlqaImP8r0bE1U0dZuoxM/ebZGqiikq5FEzZ4Q4XFSRTvddVBFy12JPgqqqOi0cqupor1GTwdRqcIY/AzGH74VOrSZasN1oUOP0nLJ3FCzk5ZJTQ1lIWKftMgM3UpmKNe8yAx94sxQU9CEosQ3gAztqoih8eFcwL30tf6l7ZLf+CFsjYJ2HOCrkMrV/Jj/qb1odnp6G+AyxBy6L7mTW2dI7kRyJ5I7kdypFhySO5HcieROJHciubMiyBTJnUjuRHJn0ZixneTOHnl1x/1SjeJ1HLYbO4vg4DHdn4UpCPx9wHdpimic3Uar1YAFowyN8xtkScm+pPlpp8L9JuxLIw2B7Mvcw21iXxriYmIp+9KQqr2IfSmuHyP7spp9aTT0TiX7ki5Aq8mXcfwC8iXI6pDY2/Zu33X56ZRS/uVetGR1gfxL5F8i//Jp8S8FWSt2Xh4c9rK1a+haowfnW/SHj70ED4gSyfLn0/FkfzBkZyjGw9En8kd/jwUZ9Sf7H2KWJd1cgECCXn9APEp5twbpk9X0SVFe5fTJbqMDcwOkT8r0SfFkTbKZn6NPdnvtbaVPZqZpW4Wf1AXtgPjJ/NFExE8ifhLxk4ifFL+K+EnETypCZ9kAPNOIn6w1QiB+cjUSQv3KRfwk4ierYyF+EvGTiJ9E/CTiJxE/ifhJxE9KkRA/Kb1D/CTiJxE/qUyrdhKIn0T8ZJok4icRP4n4ScRPVjYZxE8ifjKJhPhJxE8ifvLp4SeVLg0RP5niJzMCescxjxVoAfa2Gftv/MT6qkQL0nU4pkZU2QnJTvoUHq5GONIbecQRnIFjPyb/afKfFv9p858O/+nynx79USCS9MaPa2Ak6aISMtqCVutBDySDEIhj7PzVfu+FobdQupqFFAMB7nQMn6HZgjfc7SxdaUoepu4Yk2tKbfl9GKOC3j7X04O+QoDrGPYZH4Hsye9tF6aiM6Z2+Jk94TU7yZq+LxEb1OC6y2SUlykNcfdSVbo5zTCWONKwHVOuvIjUuPuRjCLQBtL6F8FTXaHFEEXoRgs5LH9F0nS9xblvy28LWBkz6Ha/80a4Z19YREG+p5mRE+ZeuSFNP33Tqsve0sG2+2NACqtR78Xvds5Dd0cjBoO1G3qXl3P73U7oefPQWfKny7k1pdYvvCAPKT/m3U4fHIBqt3+D25VL31441E8PqLWdP/3RiRO/sLQLa9dySbq79Ky7vTu3L0IS5I1DfiAffxJppRXwVLjdsvbMw53RpX97M729mcEZ6KL8Q8a1wpzLw3Mzl/PWhnIO/tusklz7zuVVVt6sCcHiOjG0j6xF7O7tKM6t0t1vqnTgNZxZDrIr50Wc2pw4YLawbnHMbN+t3QpXEcuBFdxXLEwMHZVxyJTIB8cPQujUW91R2XF8kIec1S2tTYM1aSmv29YRSR5PfPtazuO2qbkCG5G2eOY7G17ug3XL7mDUZDp2MrMhQ2BPqMGHhVNKksH3oTBW5rCDwghuKrAH+W4pohuJXjhkc2FfW1iaHxMIJd6WgoSQ97O/cqqKWYyoSYmdfgRXhEhVywmUVkB9iKEox1bNuoC52dw6t+c/J3PE0kIw5gI3pzhMk3n3V7ii/96EcTjycMjyqL3Qmw1tSsaCKXVTGJBa/OIsosVL3mXIn3PbvQyvaERi1APq6B6Ee6F7kFJF/vzwZJg+6tFH2yI4PSu4TiK3O8oAWge4PPhq+0WFFHsGVVl+CIYyUc6+FsehSTXA4L+0D8i8GwbxDHmvgMXHnpCMszb987hmq26mMQCkc23XicdeUo4CnWXQqcQwdtQez92hq/k27NhcnfGVFT4N66QvHDJN4sr5iIs5fjWnZGz+XEjL8y/TtEBRxS/CvNN08XXiX1yI3RBeZw5jVmoiKAbz9Gv7h/G2kjAXEvpFLzMHEmmMevZBMUCymugoEYnFBx1ZPxaMAHBIC9bGfQ92Jn+NHN9xL0+8IHxvgROJuMHT/kyk+cU7Da2rdN2rmSCZpnYQvzLjRSdYlftk+8yNjzhL5Wp16PvUV0C83Grw1JKeGX/G4CpceMHXBe66BprpXln6ugDTVNeWwJc2V85ObaGL/R1WtbalSQmLFHRhMaJ96DCaO3OHdeGUcAud2rq0s4zSxnNBVECm319cSprIzGgi0gY+UP/f3wYECZeZqT4ied+dXnng1Reyae+yXNyDo1QbBKmYARIpgAOi3/OrFKsIknkI+N6C5H4KvpsgwWlTsjQdN9cV1pmN/DKzYpmYL2rfb5XYqGdDF0weauobZsix7YVC9bG6GhOyVhmDAf3aTO81/mFwfDQZHp2enfQ/Dv+BUWsYk+ofQnbKaSQ+o1HY+iuZbMaF7xh3TUz/B4ttbo9jbzz3T9PYQJrmBtJsbiDN1gbSbG8gzc4G0uxuIM1ecZrmndt8oyBRPk8ZUD8kjvXJo5O2HkzjiFV4Ju6ingmBSCQ294KNmtKgqUc2+ZJUsqTZvmMSpIihb4MvJyGxZnVizixzFpweXqmKAxynfsqxoUgjerKoPKLrhftS3Dif7ZrfPBHJQolPsl6N2HDkuEDk9DZ6eXyL75MLDjqS6K3qzOeiQzmgxsRcNGqUgpMXxGhm/WijhbXaB2Hm2c8616zRyKUpqULireoaA0dL9NNJlatbeJ3WOiBpDZbX+h3jGXeMZ94xXnO16p1ZlDU3BthZ4tCn8abRfhODyGp8nCcydGdCErqRJNGqkX/uUPTuvYz6+SyKXqfJJI7i+NnkFVUhLwB3QFs/ouQecpUqh/7FRiWxc1RX+S/213/xfKK5+eLzyFo6M0knVWeapyFTUMXsdyqTsBU6NXatWqPBzNiW+9jzw/dfk2U2Rge2EohdjWElyKZQv+oC2w2O/Zntv//Ktx73huMBXTuuLr93cRHYiQsss3ocoBCX5FRNu0bJ7KnnEr3AXckdu2XDWPX3A1rJh0TNDL9Y04T3WSMmwx8MRTdu9ft14nx2BgAhYgIIN0KqYlpzOHjpe4sTUsmWk0x4aijGsyX5xZtxJ46r9MszevKLLRuvZGeIcLhhQpGLTbvqAZc69D1J05h479nFnOAqPWxTw+DIppNPo0atKfJyuoSemaZS3YKzqWRTqO6iZ8zpZACN4C/EBrD9mfU1aQbVvVRoQ0O4A5S0+hrVCU5w2d923B5Y5HaNRiSuvPfpAQXbh8sxEmu3XaMdSwn1wRczOCjMJUX9dVUk5XoTe7H0fMv/OjgW1QiXSY0kbPcAHKju2cuITwBIu27obxqt1OCo00JZMj9Hwa+RFYmGi5BOjdolXXVPIpkd1SwHDL4qe77ORznIrM8xZmLsGl/O0MFW0kwCP2wVI/HsgjT/PnNcnFR2rTZMjJujhF1QY0Q9c2aBYppSrSn8FCb8yRNW5mrk0pntu2CChat9kcQbCo6neS+q8b0Lz8/etkqEWqMurGt7yu/xJNFq5BaixZeiVqlDiLcHeI9V8zgE9MXlSkUDhXmHokG0uxQN4q1cNIiUKVqd6c2sT8ch2fpipl+rWmVYuciDuQfXbRKX2jXKS60L+ye6H8svwleP47854RWFmHMJVccAp7P+gu12cTfWPG6NuRAZ3SfOgtHPORC+zhhPmhu8jIUSjEklnSSw3H5Knj2vK3H7yxTu4oivh7Hr1dp2b7wRvhf7t3bq2g6i3+s+d3t9VK87MPe045hs4dQdmi3mEvqEOYRO5iXVH6Qun3no6s8wN9I8V9U2LSvN0L1eIUOHS8mrdK3JP3seG7tpF2nVyKJoYMU0pdpN3pkdy46e6yxIurRx7tnB1HZnljtNtrFqWJbJobM+nbvb/Sj0mMsGcXm8zjqdZFgy68BfQVGcQwclPYzqe1aIVZYXr6nff7oqWXuoSAjEE7p8Wtvy5d7NYX+UiM4e/ho5dHNAcO1QK4H92KV0bPv5QyJF0tM5Rxp2GP9/NyPOHw==");
		params.put("PRADO_POSTBACK_TARGET", "ctl0$CONTENU_PAGE$AdvancedSearch$lancerRecherche");

//		params.put("ctl0$CONTENU_PAGE$resultSearch$listePageSizeTop", "10");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$listePageSizeBottom", "10");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$numPageTop", "1");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$numPageBottom", "1");
//		params.put("PRADO_POSTBACK_TARGET", "ctl0$CONTENU_PAGE$resultSearch$PagerTop$ctl2");
//		
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$refCons", "417111");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "b4n");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$refCons", "433203");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "x7c");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$refCons", "428572");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "f5j");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$refCons", "419157");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "f5j");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$refCons", "428522");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "f5j");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "422806");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "x7c");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "431305");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "f0g");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "431500");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "f0g");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "431579");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "f0g");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "431731");
//		params.put("ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons", "f0g");
		

		/**
		 * ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$refCons: 417111
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl1$orgCons: b4n
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl2$refCons: 433203
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl2$orgCons: x7c
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl3$refCons: 428572
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl3$orgCons: f5j
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl4$refCons: 419157
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl4$orgCons: f5j
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl5$refCons: 428522
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl5$orgCons: f5j
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl6$refCons: 422806
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl6$orgCons: x7c
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl7$refCons: 431305
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl7$orgCons: f0g
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl8$refCons: 431500
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl8$orgCons: f0g
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl9$refCons: 431579
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl9$orgCons: f0g
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl10$refCons: 431731
ctl0$CONTENU_PAGE$resultSearch$tableauResultSearch$ctl10$orgCons: f0g
		 */
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
		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();
		ArrayList<String> listLocation = new ArrayList<String>();

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
		// define the url of the site
		String urlAuvergner = "https://auvergnerhonealpes.achatpublic.com/sdm/ent/gen/ent_recherche.do";

		ArrayList<String> listLinks = new ArrayList<String>();
		ArrayList<String> listTitre = new ArrayList<String>();
		ArrayList<String> listDate = new ArrayList<String>();

		// for the different page
		for (int j = 0; j < pageNum; j++) {
			Map<String, String> params = new HashMap<String, String>();
			/*
			 * debug: jg orderby: 0 page: -1 nbAffiche: 10 objetRecherche: -1
			 * personnepublique: typeLieu: passation region: R01 departement:
			 * 02!;!59!;!60!;!62!;!80 procedure: -1 marche: -1 intitule: informatique
			 * reference: codeCPV: jour: 07 mois: 06 annee: 2019 precisionDate: apres page:
			 * 1
			 */
			params.put("departement", departement);
			params.put("intitule", intitule);
			params.put("page", Integer.toString(j));
			// params.put("jour", "07");
			// params.put("mois", "06");
			// params.put("annee", "2019");
			// params.put("debug", "jg");
			// params.put("", "");
			// params.put("", "");
			// params.put("", "");

			String result = null;
			// send POST request to the site to get the HTML data
			try {
				result = HTTPRequest.sendPost(urlAuvergner, params);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(result);

			// parse the HTML data
			Document doc = Jsoup.parse(result);

			// get the links from the HTML data
			Elements eles_link1 = doc.getElementsByClass("lineTable03 fixed_grey arial_11");
			Elements eles_link2 = doc.getElementsByClass("lineTable02 fixed_grey arial_11");
			ArrayList<String> listHTML = new ArrayList<String>();
			for (int i = 0; i < eles_link1.size(); i += 2) {

				Element ele = eles_link1.get(i);
				// System.out.println(ele.attr("onclick"));
				listHTML.add(ele.attr("onclick"));
				if (i < eles_link2.size()) {
					Element ele2 = eles_link2.get(i);
					listHTML.add(ele2.attr("onclick"));
				}
			}

			// split the String to get the links
			String[] ListString;
			for (String link : listHTML) {
				// System.out.println(link);
				ListString = link.split("'");
				listLinks.add("https://auvergnerhonealpes.achatpublic.com/sdm/ent/gen/ent_detail.do?selected=0&PCSLID="
						+ ListString[3]);
			}

			// get the titre from the HTML data
			Elements eles_titre1 = doc.getElementsByClass("lineTable03 fixed_grey arial_11 align_left");
			Elements eles_titre2 = doc.getElementsByClass("lineTable02 fixed_grey arial_11 align_left");

			for (int i = 0; i < eles_titre1.size(); i += 2) {
				Element ele = eles_titre1.get(i);
				listTitre.add(ele.text());
				if (i < eles_titre2.size()) {
					Element ele2 = eles_titre2.get(i);
					listTitre.add(ele2.text());
				}
			}

			// get the date from the HTML data (attention: the date is the limited date)
			for (int i = 1; i < eles_titre1.size(); i += 2) {
				Element ele = eles_titre1.get(i);
				listDate.add(ele.text());
				if (i < eles_titre2.size()) {
					Element ele2 = eles_titre2.get(i);
					listDate.add(ele2.text());
				}
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

		return avisList;

	}

	/*****************************************************/

	/**
	 * Crawler for Ted.europa!! Attention : we can't solve the problem because of the pid and 302 307
	 * @throws Exception 
	 */
	public void tedEuropaCrawler()   {
		// define the url of the site
		String urlAuvergner = "https://ted.europa.eu/TED/search/expertSearch.do";
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
	 * the location :the number of the area, for example : Ville de Paris : "75" +1 (76)
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
