package scoste.restau.event;

import scoste.restau.Service;

public interface EventService {
	
	Service applyEvent(Event event, Service service);
}
