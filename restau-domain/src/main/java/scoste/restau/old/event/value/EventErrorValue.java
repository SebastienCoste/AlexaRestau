package scoste.restau.old.event.value;

public class EventErrorValue extends EventValue {

    public String error;

    public EventErrorValue(String error) {
        this.error = error;
    }

    @Override
    public String toString(){
        return error;
    }
}
