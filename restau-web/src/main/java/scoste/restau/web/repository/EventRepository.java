package scoste.restau.web.repository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import scoste.restau.domain.event.Event;
import scoste.restau.domain.event.EventId;
import scoste.restau.web.repository.dto.EventDto;
import scoste.restau.web.dto.AckType;

@Service
public class EventRepository {

    private final EventDynamoRepository eventDynamoRepository;

    public EventRepository(EventDynamoRepository eventDynamoRepository) {
        this.eventDynamoRepository = eventDynamoRepository;
    }


    public Mono<AckType> saveEvent(Event event) {

        EventId id = event.getId();
        System.out.println(id.id + ": " + event.toString());

        eventDynamoRepository.save(EventDto.fromDomain(event));

        return Mono.just(AckType.OK);
    }
}
