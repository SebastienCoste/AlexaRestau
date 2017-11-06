package scoste.restau.event;

import scoste.restau.Repas;
import scoste.restau.Service;

import java.util.List;


public class EventServiceRetireClient implements EventService {

	@Override
	public Service applyEvent(Event event, Service service) {
		
		int nbr = Integer.valueOf(event.valeur);
		int clientsATable =service.clientsATable - nbr;
		List<Repas> repas = service.listRepas;
		if (clientsATable >= 0) {
			return new Service(clientsATable, repas);
		}
		
		return service;
	}

}
