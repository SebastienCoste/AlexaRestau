package scoste.restau.web.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import scoste.restau.web.dto.Ack;
import scoste.restau.web.dto.AckType;
import scoste.restau.web.dto.event.Event;
import scoste.restau.web.dto.event.EventMeal;
import scoste.restau.web.dto.event.EventType;
import scoste.restau.web.dto.event.data.Next;
import scoste.restau.web.dto.event.data.Previous;
import scoste.restau.web.repository.ActionRepository;

@Service
public class ActionService {

    private final ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public Mono<Ack> addClient(String idRestau, String idTable, Integer previous, Integer next) {

        Event<Integer, Integer> event = new EventMeal.Builder(idRestau, idTable)
                .addClient()
                .from(new Previous(previous))
                .to(new Next(next))
                .build();
        return treatEvent(event);
    }

    public Mono<Ack> removeClient(String idRestau, String idTable, Integer previous, Integer next) {
        Event<Integer, Integer> event = new EventMeal.Builder(idRestau, idTable)
                .removeClient()
                .from(new Previous(previous))
                .to(new Next(next))
                .build();
        return treatEvent(event);
    }

    public Mono<Ack> treatFailedMealRequest(String idRestau, String idTable, String comment) {

        EventMeal event = new EventMeal.Builder(idRestau, idTable).withComment(comment).withType(EventType.FUNCTIONNAL_ALERT).build();
        return treatEvent(event);
    }

    public Mono<Ack> treatNoChangeMealRequest(String idRestau, String idTable) {

        Event event = new EventMeal.Builder(idRestau, idTable).withType(EventType.FUNCTIONNAL_WARNING).build();
        actionRepository.saveEvent(event);
        return Mono.just(new Ack(AckType.NO_CHANGE, event));
    }

    private Mono<Ack> treatEvent(Event event){

        return actionRepository.saveEvent(event).map(e -> new Ack(e, event));
    }

}
