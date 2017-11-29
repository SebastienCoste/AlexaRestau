package scoste.restau.domain.gestionnaire;

import scoste.obs.Subscriber;
import scoste.restau.event.Event;
import scoste.restau.event.EventType;

public class GestionnaireRestaurant extends Subscriber<Event> {
    @Override
    public void apply(Event event) {
        if (event.type == EventType.ERROR){
            System.out.println("ALERTE: " + event.valeur.toString());
        }
    }
}
