package scoste.restau.domain.event;

import scoste.restau.domain.event.data.Next;
import scoste.restau.domain.event.data.Previous;

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

    public EventTime getEventTime() {
        return eventTime;
    }
}
