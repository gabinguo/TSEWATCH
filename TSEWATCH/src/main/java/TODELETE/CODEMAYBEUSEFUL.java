package TODELETE;

public class CODEMAYBEUSEFUL {

	




////get links from https://www.saint-etienne.fr/node/5659
//	 private ArrayList<String> getLinksST(String dateParution/*params add here*/) throws Exception{
//		String url = "https://cg42.marches-publics.info/avis/index.cfm?fuseaction=pub.affResultats&IDs=11";
//		Map<String,String> params = new HashMap<String, String>();
//		params.put("dateParution", dateParution);
//		
//		// Get result
//		String result = sendPost(url, params);
//		Document doc = Jsoup.parse(result);
//		
//		ArrayList<String> listLinks = new ArrayList<String>();
//		
//		Elements eles = doc.getElementsByAttributeValueStarting("href", "index.cfm?fuseaction=pub.affPublication&refPub=");
//		for(Element ele : eles) {
//			listLinks.add("https://cg42.marches-publics.info/avis/" + ele.attr("href"));
//		}
//		return listLinks;
//}	
	
//  private static AvisST getInfoAvisSt(String url) throws Exception {
//	incrementCrawlUrlNumByOne();
//	Document doc = Jsoup.parse(getHTML(url));
//	/**
//	 * find data one by one
//	 */
//	Elements elesTop = doc.getElementsByAttributeValue("valign", "top");
////		private String title;
//	Elements topBTags = elesTop.get(0).getElementsByTag("b");
//	String title = topBTags.get(0).text();		
//	String[] elesBrTop = elesTop.get(0).html().toString().split("<br>");
////		private String nameOfPoster;
//	String nameOfPoster = elesBrTop[1].trim();
////		private String address;
//	String address = elesBrTop[2].trim() + " " + elesBrTop[3].trim();
////		private String phoneNum;
//	
//	String phoneNum = Regex.telNumber(elesBrTop[4]).trim();
//	if(null == phoneNum) phoneNum = "unknown";
//	
////		private String reference;
//	Elements elesTable = doc.getElementsByClass("AW_TableM_Bloc1_Clair");
//	String reference = elesTable.get(1).text();
////		private String typeOfMarket;
//	String typeOfMarket = elesTable.get(3).text();
////		private String description;
//	String description = elesTable.get(9).text();
////		private String deadline;
//	String deadline = Regex.deadline(getHTML(url)).replaceAll("</b>", "").replaceAll("<br />", "").replaceAll("</td>", "");
//	return new AvisST(title,nameOfPoster,address,phoneNum,reference,typeOfMarket,description,deadline);
//}
	

//Download Function
//Param : Url to download
//	private static void download(String url,String nameOfFile)throws MalformedURLException {
//		URL site = new URL(url);
//		Calendar calendar = Calendar.getInstance();
//		Date time = calendar.getTime();
//		int lengthFolderName = time.toString().replaceAll(" ", "").length();
//		String folderName = time.toString().replaceAll(" ", "").substring(3, lengthFolderName-9);
//		folderName = folderName.substring(0, 5) + "_" + folderName.substring(5).replaceAll(":", "-");
//		try {
//			File directory = new File(String.valueOf("./Boamp/" + folderName + "/"));
//			if(!directory.exists()) {
//				directory.mkdir();
//			}
//			FileOutputStream fos = new FileOutputStream(new File("./Boamp/" + folderName + "/" +  nameOfFile + ".pdf"));
//			InputStream in = site.openStream();
//			//System.out.println("reading from resource and writing to file...");
//			int length = -1;
//			byte[] buffer = new byte[1024];
//			while((length=in.read(buffer)) > -1) {
//				fos.write(buffer,0,length);
//			}
//			
//			fos.close();
//			in.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
//		
//		incrementDownloadNumByOne();
//	}
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
}
