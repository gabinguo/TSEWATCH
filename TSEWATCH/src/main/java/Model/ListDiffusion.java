package Model;

import java.util.ArrayList;

public class ListDiffusion {
	private String name;
	private ArrayList<Client> listClient;


	public ListDiffusion(String name,ArrayList<Client> listClient) {
		super();
		this.name = name;
		this.listClient = listClient;
	}
	
	public ListDiffusion(String name) {
		this.name = name;
	}

	public ListDiffusion() {
	}

	public ArrayList<Client> getListClient() {
		return listClient;
	}

	public void setListClient(ArrayList<Client> listClient) {
		this.listClient = listClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
