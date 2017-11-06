package scoste.restau;


import java.util.ArrayList;
import java.util.List;

public class Service {

	public Integer clientsATable;
	public List<Repas> listRepas;

	public Service() {
		this.clientsATable = 0;
        listRepas= new ArrayList<>();
	}
	
	public Service(int clients, List<Repas> repas) {
		this.clientsATable = clients;
		this.listRepas = repas;
	}

	
}
