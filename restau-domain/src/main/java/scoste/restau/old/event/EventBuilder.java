package scoste.restau.old.event;

import scoste.restau.old.event.value.ChangeCLientEventValue;
import scoste.restau.old.event.value.EventTableValue;
import scoste.restau.old.event.value.EventValue;
import scoste.restau.old.event.value.RepasEventValue;

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
        String firstChar = input.split(" ")[0];

        try {
        if ("+".equals(firstChar)){
            type = EventType.AJOUTE_CLIENT;
            ChangeCLientEventValue valeur = new ChangeCLientEventValue();
            String[] split = input.substring(1).trim().split(" ");
            valeur.nbChange = split[1];
            valeur.idRepas = split [0];
            return new Event(type, valeur);
        } else if ("-".equals(firstChar)) {
            type = EventType.RETIRE_CLIENT;
            ChangeCLientEventValue valeur = new ChangeCLientEventValue();
            String[] split = input.substring(1).trim().split(" ");
            valeur.nbChange = split[1];
            valeur.idRepas = split [0];
            return new Event(type, valeur);
        }   else if ("r+".equals(firstChar)) {
            type = EventType.INCREMENTE_STATUS_REPAS;
            RepasEventValue value = new RepasEventValue();
            value.idRepas = Integer.valueOf(input.substring(2).trim());
            return new Event(type, value);

        }   else if ("r-".equals(firstChar)) {
            type = EventType.DECREMENTE_STATUS_REPAS;
            RepasEventValue value = new RepasEventValue();
            value.idRepas = Integer.valueOf(input.substring(2).trim());
            return new Event(type, value);

        } else if ("bc".equals(firstChar)){
            type = EventType.CLEAN_TABLE;
            EventTableValue value = new EventTableValue(Integer.valueOf(input.substring(2).trim()));
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
