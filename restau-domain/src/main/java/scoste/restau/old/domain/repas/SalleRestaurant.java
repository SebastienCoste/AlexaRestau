package scoste.restau.old.domain.repas;


import scoste.obs.Subscriber;
import scoste.restau.old.domain.table.BoyRestaurant;
import scoste.restau.old.event.EventType;
import scoste.restau.old.event.value.ChangeCLientEventValue;
import scoste.restau.old.event.Event;
import scoste.restau.old.event.value.EventErrorValue;
import scoste.restau.old.event.value.EventTableValue;
import scoste.restau.old.event.value.RepasEventValue;

import java.util.HashMap;
import java.util.Map;

import static scoste.restau.old.event.EventType.LIBERE_TABLE;

public class SalleRestaurant extends Subscriber<Event>{

    public Map<Integer, Repas> repasLst = new HashMap<>();

    public BoyRestaurant boys;

    public SalleRestaurant() {
    }

    public SalleRestaurant(Map<Integer, Repas> repas) {
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
            source.publish(new Event(EventType.ERROR, new EventErrorValue("incrementer une table non existante")));
        } else {
            Repas nextRepas = repas.prochaineEtapeStatusRepas(avance);
            repasLst.put(idRepas, nextRepas);
            if (nextRepas.status == StatusRepas.ADDITION_PAYEE && repas.status != StatusRepas.ADDITION_PAYEE){
                source.publish(new Event(LIBERE_TABLE, new EventTableValue(nextRepas.idTable)));
            }
        }

    }

    private void creeRepas(Integer idRepas, Integer nbCLients) {
        Integer table = boys.getAFreeTable();

        if (table > -1){
            repasLst.put(idRepas, new Repas(idRepas, nbCLients, StatusRepas.CHOIX_EN_COURS, table));
        } else {
            source.publish(new Event(EventType.ERROR, new EventErrorValue("No free table available")));
        }
    }


    private void ajouteClient(Event event){

        ChangeCLientEventValue eventClient = (ChangeCLientEventValue) event.valeur;
        int nbr = Integer.valueOf(eventClient.nbChange);
        Integer idRepas = Integer.valueOf(eventClient.idRepas);
        Repas repas = this.repasLst.get(idRepas);
        if (repas == null){
            creeRepas(idRepas, nbr);
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
