package scoste.restau.web.repository.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.springframework.data.annotation.Id;
import scoste.restau.domain.event.Event;

import java.util.Objects;

@DynamoDBTable(tableName = "RestauEvent")
public class EventDto<P,N> {


    @Id
    private EventIdDto id;
    private P previous;
    private N next;
    private String comment;


    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return id.getId();
    }

    public void setId(String id){
        if (this.id == null){
            this.id = new EventIdDto();
        }
        this.id.setId(id);
    }
    @DynamoDBRangeKey(attributeName = "Time")
    public String getEventTime() {
        return id.getEventTime();
    }

    public void setTime(String time){
        if (this.id == null){
            this.id = new EventIdDto();
        }
        this.id.setEventTime(time);
    }

    @DynamoDBAttribute(attributeName = "Comment")
    public String getComment() {
        return comment;
    }

    @DynamoDBAttribute(attributeName = "Next")
    public N getNext() {
        return next;
    }

    @DynamoDBAttribute(attributeName = "Previous")
    public P getPrevious() {
        return previous;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventDto)) return false;
        EventDto eventDto = (EventDto) o;
        return Objects.equals(id, eventDto.id) &&
                Objects.equals(previous, eventDto.previous) &&
                Objects.equals(next, eventDto.next) &&
                Objects.equals(comment, eventDto.comment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, previous, next, comment);
    }

    public static EventDto fromDomain(Event event){

        EventIdDto id = new EventIdDto();
        id.setEventTime(event.getEventTime().time);
        id.setId(event.getId().id);
        EventDto dto = new EventDto();
        dto.id = id;
        dto.comment = event.getComment();
        dto.previous = event.getPrevious().data;
        dto.next = event.getNext().data;

        return dto;
    }

    public Event toDomain(){

        return null;
    }

    public void setId(EventIdDto id) {
        this.id = id;
    }

    public void setPrevious(P previous) {
        this.previous = previous;
    }

    public void setNext(N next) {
        this.next = next;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
