package scoste.restau.event;

import scoste.restau.Repas;
import scoste.restau.Service;

import java.util.List;


public class EventServiceAjouteClient implements EventService{

	@Override
	public Service applyEvent(Event event, Service service) {
		
		int nbr = Integer.valueOf(event.valeur);
		int clientsATable = nbr + service.clientsATable;
		List<Repas> repas = service.listRepas;
		
		return new Service(clientsATable, repas);
	}

}
