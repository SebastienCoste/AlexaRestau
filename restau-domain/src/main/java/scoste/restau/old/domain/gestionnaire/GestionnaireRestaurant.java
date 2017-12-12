package scoste.restau.old.domain.gestionnaire;

import scoste.obs.Subscriber;
import scoste.restau.old.event.Event;
import scoste.restau.old.event.EventType;

public class GestionnaireRestaurant extends Subscriber<Event> {
    @Override
    public void apply(Event event) {
        if (event.type == EventType.ERROR){
            System.out.println("ALERTE: " + event.valeur.toString());
        }
    }
}
