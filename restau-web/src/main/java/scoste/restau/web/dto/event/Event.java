package scoste.restau.web.dto.event;

import scoste.restau.web.dto.event.data.Next;
import scoste.restau.web.dto.event.data.Previous;

public abstract class Event<P,N> {

    protected EventId eventId;
    protected EventTime eventTime;
    protected Previous<P> previous;
    protected Next<N> next;
    protected String comment;

    public EventId getId() {
        return eventId;
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
