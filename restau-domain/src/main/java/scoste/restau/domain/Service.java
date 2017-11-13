package scoste.restau.domain;


import scoste.obs.Subscriber;
import scoste.restau.event.value.ChangeCLientEventValue;
import scoste.restau.event.Event;
import scoste.restau.event.value.RepasEventValue;

import java.util.HashMap;
import java.util.Map;

public class Service extends Subscriber<Event>{

	public Integer clientsATable;
	public Map<Integer, Repas> repasLst = new HashMap<>();

	public Service() {
		this.clientsATable = 0;
	}

	public Service(int clients, Map<Integer, Repas> repas) {
		this.clientsATable = clients;
		this.repasLst = repas;
	}


	@Override
	public void apply(Event event) {

		switch (event.type){
			case AJOUTE_CLIENT:
				ajouteClient(event);
				return;
			case RETIRE_CLIENT:
				retireClient(event);
				return;

            case INCREMENTE_STATUS_REPAS:
                incrementeRepas(event);
                return;
		}
	}

    private void incrementeRepas(Event event) {

	    RepasEventValue eventValue = (RepasEventValue) event.valeur;
	    Integer idRepas = eventValue.idRepas;
	    Repas repas = this.repasLst.get(idRepas);
	    if (repas == null){
	        repasLst.put(idRepas, new Repas(idRepas, 0, StatusRepas.CHOIX_EN_COURS));
        } else {
	        repasLst.put(idRepas, repas.prochaineEtapeStatusRepas());
        }

    }


    private void ajouteClient(Event event){

        ChangeCLientEventValue eventClient = (ChangeCLientEventValue) event.valeur;
		int nbr = Integer.valueOf(eventClient.nbChange);
		this.clientsATable += nbr;
	}

	private void retireClient(Event event){
        ChangeCLientEventValue eventClient = (ChangeCLientEventValue) event.valeur;
        int nbr = Integer.valueOf(eventClient.nbChange);
		int clientsATable =this.clientsATable - nbr;
		if (clientsATable >= 0) {
			this.clientsATable = clientsATable;
		}
	}
}
