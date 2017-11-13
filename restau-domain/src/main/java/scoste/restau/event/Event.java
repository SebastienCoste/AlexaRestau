package scoste.restau.event;

import scoste.obs.Observable;
import scoste.restau.event.value.EventValue;

public class Event extends Observable{

	public EventType type;
	public EventValue valeur;
	
	public Event (EventType type, EventValue valeur) {
		this.type = type;
		this.valeur = valeur;
	}
	
}
