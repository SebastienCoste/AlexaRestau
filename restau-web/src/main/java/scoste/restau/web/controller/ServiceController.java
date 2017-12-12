package scoste.restau.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import scoste.restau.web.dto.Ack;
import scoste.restau.domain.event.EventId;
import scoste.restau.domain.event.EventTime;
import scoste.restau.web.service.ActionService;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private final ActionService actionService;

    public ServiceController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping(value = "/add/{idTable}/{variation}/{previous}")
    public Mono<Ack> addClient (EventId eventId, EventTime eventTime, @PathVariable String idTable, @PathVariable(required = false) Integer previous, @PathVariable Integer variation){

        if(variation > 0){
            return actionService.addClient(eventId, eventTime, idTable, previous == null ? 0 : previous, previous + variation);
        } else if(variation < 0){
            return actionService.treatFailedMealRequest(eventId, eventTime, idTable, "invalid client variation: " + variation);
        } else {
            return actionService.treatNoChangeMealRequest(eventId, eventTime, idTable);
        }
    }

    @GetMapping(value = "/remove/{idTable}/{variation}/{previous}")
    public Mono<Ack> removeClient (EventId eventId, EventTime eventTime, @PathVariable String idTable, @PathVariable Integer previous, @PathVariable Integer variation){

        if(variation > 0){
            return actionService.removeClient(eventId, eventTime, idTable, previous, previous - variation);
        } else if(variation < 0){
            return actionService.treatFailedMealRequest(eventId, eventTime, idTable, "invalid client variation: " + variation);
        } else {
            return actionService.treatNoChangeMealRequest(eventId, eventTime, idTable);
        }
    }

}
