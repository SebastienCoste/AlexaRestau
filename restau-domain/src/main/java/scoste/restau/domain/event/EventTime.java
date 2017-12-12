package scoste.restau.domain.event;

public class EventTime {

    public String time;

    public EventTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "EventTime{" +
                "time='" + time + '\'' +
                '}';
    }
}
