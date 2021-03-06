package scoste.restau.web.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import scoste.restau.domain.event.Event;
import scoste.restau.domain.event.EventId;
import scoste.restau.domain.event.EventTime;
import scoste.restau.domain.event.EventType;
import scoste.restau.domain.event.data.Next;
import scoste.restau.domain.event.data.Previous;
import scoste.restau.domain.event.impl.EventMeal;
import scoste.restau.web.dto.Ack;
import scoste.restau.web.dto.AckType;
import scoste.restau.web.repository.EventRepository;

@Service
public class ActionService {

    private final EventRepository eventRepository;

    public ActionService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Mono<Ack> addClient(EventId eventId, EventTime eventTime, String idTable, Integer previous, Integer next) {

        Event<Integer, Integer> event = new EventMeal.Builder(eventId, eventTime, idTable)
                .addClient()
                .from(new Previous(previous))
                .to(new Next(next))
                .build();
        return treatEvent(event);
    }

    public Mono<Ack> removeClient(EventId eventId, EventTime eventTime, String idTable, Integer previous, Integer next) {
        Event<Integer, Integer> event = new EventMeal.Builder(eventId, eventTime, idTable)
                .removeClient()
                .from(new Previous(previous))
                .to(new Next(next))
                .build();
        return treatEvent(event);
    }

    public Mono<Ack> treatFailedMealRequest(EventId eventId, EventTime eventTime, String idTable, String comment) {

        EventMeal event = new EventMeal.Builder(eventId, eventTime, idTable)
                .withComment(comment)
                .withType(EventType.FUNCTIONNAL_ALERT)
                .build();
        return treatEvent(event);
    }

    public Mono<Ack> treatNoChangeMealRequest(EventId eventId, EventTime eventTime, String idTable) {

        Event event = new EventMeal.Builder(eventId, eventTime, idTable)
                .withType(EventType.FUNCTIONNAL_WARNING)
                .build();
        eventRepository.saveEvent(event);
        return Mono.just(new Ack(AckType.NO_CHANGE, event));
    }

    private Mono<Ack> treatEvent(Event event){

        return eventRepository.saveEvent(event).map(e -> new Ack(e, event));
    }

}
