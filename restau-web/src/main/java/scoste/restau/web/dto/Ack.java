package scoste.restau.web.dto;

import scoste.restau.domain.event.Event;

public class Ack {

    public AckType ackType;
    public Event data;

    public Ack(AckType ackType, Event data) {
        this.ackType = ackType;
        this.data = data;
    }


}
