package scoste.restau.event;

public class EventBuilder {

    public String input;

    public EventBuilder(){

    }

    public EventBuilder withInput(String input){
        this.input = input;
        return this;
    }

    public Event build(){
        EventType type = EventType.ERROR;
        String valeur = null;
        String firstChar = input.substring(0, 1);
        if ("+".equals(firstChar)){
            type = EventType.AJOUTE_CLIENT;
            valeur = input.substring(1).trim();
        } else if ("-".equals(firstChar)) {
            type = EventType.RETIRE_CLIENT;
            valeur = input.substring(1).trim();
        } else {
            try {
                Integer.valueOf(input.trim());
                valeur = input.trim();
                type = EventType.AJOUTE_CLIENT;
            } catch (Exception e) {
                type = EventType.ERROR;
                valeur = null;
            }
        }

        return new Event(type, valeur);
    }
}
