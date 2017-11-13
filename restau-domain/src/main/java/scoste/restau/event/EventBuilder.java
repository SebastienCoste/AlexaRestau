package scoste.restau.event;

import scoste.restau.event.value.ChangeCLientEventValue;
import scoste.restau.event.value.EventValue;
import scoste.restau.event.value.RepasEventValue;

public class EventBuilder {

    public String input;

    public EventBuilder(){

    }

    public EventBuilder withInput(String input){
        this.input = input;
        return this;
    }

    public Event build(){
        EventType type;
        String firstChar = input.substring(0, 1);

        try {
        if ("+".equals(firstChar)){
            type = EventType.AJOUTE_CLIENT;
            ChangeCLientEventValue valeur = new ChangeCLientEventValue();
            valeur.nbChange = input.substring(1).trim();
            return new Event(type, valeur);
        } else if ("-".equals(firstChar)) {
            type = EventType.RETIRE_CLIENT;
            ChangeCLientEventValue valeur = new ChangeCLientEventValue();
            valeur.nbChange = input.substring(1).trim();
            return new Event(type, valeur);
        }   else if ("r".equals(firstChar)) {
            type = EventType.INCREMENTE_STATUS_REPAS;
            RepasEventValue value = new RepasEventValue();
            value.idRepas = Integer.valueOf(input.substring(1).trim());
            return new Event(type, value);

        } else {
                ChangeCLientEventValue valeur = new ChangeCLientEventValue();
                valeur.nbChange = input.trim();
                type = EventType.AJOUTE_CLIENT;
                return new Event(type, valeur);

        }
        } catch (Exception e) {
            type = EventType.ERROR;
            EventValue valeur = null;
            return new Event(type, valeur);
        }
    }
}
