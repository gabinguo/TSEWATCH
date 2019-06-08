package Model;

public class Avis {
	String date;
	String titre;
	String link;
	
	public Avis(String date,String titre,String link){
		this.date = date;
		this.titre = titre;
		this.link = link;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getStr2File() {
		return this.titre+"|"+this.link+"|"+this.date;
	}
	
	public void print() {
		System.out.println(this.date);
		System.out.println("titre: "+ titre);
		System.out.println("link: "+link);
		System.out.println("");
	}
}
