package scoste.restau;

import scoste.obs.Publisher;
import scoste.restau.domain.Service;
import scoste.restau.event.Event;
import scoste.restau.event.EventBuilder;

import java.util.Scanner;

public class Main {



	public static void main(String[] args) {

		Publisher<Event> servicePub = new RestauPublisher();
		Service service = new Service();
		servicePub.subscribe(service);

		Scanner sc = new Scanner(System.in);
		System.out.println("precisez les entrees/sorties de client");
		System.out.println("'+' suivi du nombre de clients pour une entr�e");
		System.out.println("'-' suivi du nombre de clients pour une sortie");


		while(true) {
			System.out.println("nombre de clients dans le restaurant:" + service.clientsATable);
			service.repasLst.entrySet()
					.stream()
					.forEach(es -> System.out.println("repas " + es.getKey() + " " + es.getValue().status));
            Event event = new EventBuilder().withInput(sc.nextLine()).build();
			servicePub.publish(event);

		}
		
		
		
	}

}
