package scoste.restau.web.dto.event;

import scoste.restau.web.dto.event.data.Next;
import scoste.restau.web.dto.event.data.Previous;

public abstract class Event<P,N> {

    String restaurant;
    Previous<P> previous;
    Next<N> next;
    String comment;

    public String getId() {
        return restaurant;
    }

    public abstract EventType getType();

    public abstract EventScope getScope();

    public Previous getPrevious(){
        return previous;
    }

    public Next getNext() {
        return next;
    }

    public String getComment() {
        return comment;
    }
}
