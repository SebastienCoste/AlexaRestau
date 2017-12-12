package scoste.restau.web.repository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import scoste.restau.web.dto.AckType;
import scoste.restau.web.dto.event.Event;

@Service
public class ActionRepository {
    public Mono<AckType> saveEvent(Event event) {

        String id = event.getId();
        System.out.println(id + ": " + event.toString());
        return Mono.just(AckType.OK);
    }
}
