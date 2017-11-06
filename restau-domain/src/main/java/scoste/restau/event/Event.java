package scoste.restau.event;

public class Event {

	public EventType type;
	public String valeur;
	
	public Event (EventType type, String valeur) {
		this.type = type;
		this.valeur = valeur;
	}
	
}
