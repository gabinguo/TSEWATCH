package Model;

import java.util.ArrayList;

public class AxeDeVeille {
	
	private String name;
	private ArrayList<String> keywords;
	private ArrayList<Avis> listAvis;
	
	
	
	public AxeDeVeille(String name, ArrayList<String> keywords) {
		super();
		this.name = name;
		this.keywords = keywords;
	}
	
	
	
	public AxeDeVeille(String name, ArrayList<String> keywords, ArrayList<Avis> listAvis) {
		super();
		this.name = name;
		this.keywords = keywords;
		this.listAvis = listAvis;
	}



	public AxeDeVeille() {
		keywords = new ArrayList<String>();
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<String> getKeywords() {
		return keywords;
	}


	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	
	public void addKeyword(String keyword) {
		keywords.add(keyword);
	}


	public ArrayList<Avis> getListAvis() {
		return listAvis;
	}


	public void setListAvis(ArrayList<Avis> listAvis) {
		this.listAvis = listAvis;
	}
	
	public String getStr2File() {
		String result = "";
		for(String str : this.keywords) {
			result += str + ",";
		}
		
		return result.substring(0, result.length()-1);
	}
	public static void main(String[] args) {
		
	}
}
