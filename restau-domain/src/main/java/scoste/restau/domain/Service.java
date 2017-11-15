package scoste.restau.domain;


import scoste.obs.Subscriber;
import scoste.restau.event.value.ChangeCLientEventValue;
import scoste.restau.event.Event;
import scoste.restau.event.value.RepasEventValue;

import java.util.HashMap;
import java.util.Map;

public class Service extends Subscriber<Event>{

	public Map<Integer, Repas> repasLst = new HashMap<>();

	public Service() {
	}

	public Service(Map<Integer, Repas> repas) {
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
                incrementeRepas(event, true);
                return;
			case DECREMENTE_STATUS_REPAS:
				incrementeRepas(event, false);
				return;
		}
	}

    private void incrementeRepas(Event event, boolean avance) {

	    RepasEventValue eventValue = (RepasEventValue) event.valeur;
	    Integer idRepas = eventValue.idRepas;
	    Repas repas = this.repasLst.get(idRepas);
	    if (repas == null){
	        repasLst.put(idRepas, new Repas(idRepas, 0, StatusRepas.CHOIX_EN_COURS));
        } else {
	        repasLst.put(idRepas, repas.prochaineEtapeStatusRepas(avance));
        }

    }


    private void ajouteClient(Event event){

        ChangeCLientEventValue eventClient = (ChangeCLientEventValue) event.valeur;
		int nbr = Integer.valueOf(eventClient.nbChange);
		Integer idRepas = Integer.valueOf(eventClient.idRepas);
		Repas repas = this.repasLst.get(idRepas);
		if (repas == null){
			repasLst.put(idRepas, new Repas(idRepas, nbr, StatusRepas.CHOIX_EN_COURS));
		} else {
			repas.nombreClient += nbr;
		}
	}

	private void retireClient(Event event){
        ChangeCLientEventValue eventClient = (ChangeCLientEventValue) event.valeur;
        int nbr = Integer.valueOf(eventClient.nbChange);
		Integer idRepas = Integer.valueOf(eventClient.idRepas);
		Repas repas = this.repasLst.get(idRepas);
		if (repas != null && repas.nombreClient - nbr >= 0){
			repas.nombreClient -= nbr;
		}
	}
}
