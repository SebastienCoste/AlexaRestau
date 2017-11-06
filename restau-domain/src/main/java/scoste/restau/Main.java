package scoste.restau;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import scoste.restau.event.Event;
import scoste.restau.event.EventBuilder;
import scoste.restau.event.EventServiceManager;

public class Main {

	private static List<Event> events = new ArrayList<>();
	private static EventServiceManager factory = new EventServiceManager();
	private static Service service = new Service();
	
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("precisez les entrees/sorties de client");
		System.out.println("'+' suivi du nombre de clients pour une entr�e");
		System.out.println("'-' suivi du nombre de clients pour une sortie");
		
		while(true) {
			System.out.println("nombre de clients dans le restaurant+4:" + service.clientsATable);
            Event event = new EventBuilder().withInput(sc.nextLine()).build();
			events.add(event);
			service = factory.getService(event).applyEvent(event, service);
			
		}
		
		
		
	}

}
