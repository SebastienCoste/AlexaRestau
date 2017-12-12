package scoste.restau.web.repository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import scoste.restau.web.dto.AckType;
import scoste.restau.web.dto.event.Event;
import scoste.restau.web.dto.event.EventId;

@Service
public class ActionRepository {
    public Mono<AckType> saveEvent(Event event) {

        EventId id = event.getId();
        System.out.println(id.id + ": " + event.toString());
        return Mono.just(AckType.OK);
    }
}
