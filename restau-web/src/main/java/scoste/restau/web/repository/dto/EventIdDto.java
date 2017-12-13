package scoste.restau.web.repository.dto;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Objects;

public class EventIdDto implements Serializable{


    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String eventTime;

    @DynamoDBHashKey
    public String getId() {
        return id;
    }

    @DynamoDBRangeKey
    public String getEventTime() {
        return eventTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventIdDto)) return false;
        EventIdDto that = (EventIdDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(eventTime, that.eventTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, eventTime);
    }
}
