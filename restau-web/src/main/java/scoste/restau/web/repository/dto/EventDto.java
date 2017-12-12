package scoste.restau.web.repository.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import scoste.restau.domain.event.Event;

@DynamoDBTable(tableName = "RestauEvent")
public class EventDto<P,N> {

    private String eventId;
    private String eventTime;
    private P previous;
    private N next;
    private String comment;

    @DynamoDBHashKey
    public String getEventId() {
        return eventId;
    }

    @DynamoDBRangeKey
    public String getEventTime() {
        return eventTime;
    }

    @DynamoDBAttribute
    public String getComment() {
        return comment;
    }

    @DynamoDBAttribute
    public N getNext() {
        return next;
    }

    @DynamoDBAttribute
    public P getPrevious() {
        return previous;
    }

    public static EventDto from(Event event){

        EventDto dto = new EventDto();
        dto.eventId = event.getId().id;
        dto.eventTime = event.getEventTime().time;
        dto.comment = event.getComment();
        dto.previous = event.getPrevious().data;
        dto.next = event.getNext().data;

        return dto;
    }
}
