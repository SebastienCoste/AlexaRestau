package scoste.restau.event;

import java.util.HashMap;
import java.util.Map;

public class EventServiceManager {
	
	public Map<EventType, EventService> mapEvents; 
	
	 public EventServiceManager() {
		 mapEvents= new HashMap<>();
		 mapEvents.put(EventType.AJOUTE_CLIENT, new EventServiceAjouteClient());
		 mapEvents.put(EventType.RETIRE_CLIENT, new EventServiceRetireClient());
	 }

	 public EventService getService(Event event) {
		return mapEvents.get(event.type);
	 }
	 
	 
}
