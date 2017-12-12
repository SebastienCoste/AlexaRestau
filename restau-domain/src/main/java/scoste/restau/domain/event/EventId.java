package scoste.restau.domain.event;

public class EventId {

    public String id;

    public EventId (String id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "EventId{" +
                "id='" + id + '\'' +
                '}';
    }
}
