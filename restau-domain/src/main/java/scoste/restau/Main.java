package scoste.restau;

import scoste.obs.Publisher;
import scoste.restau.domain.Service;
import scoste.restau.event.Event;
import scoste.restau.event.EventBuilder;

import java.util.Scanner;

public class Main {



	public static void main(String[] args) {

		Publisher<Event> restaurant = new RestauPublisher();
		Service service = new Service();
		restaurant.subscribe(service);

		Scanner sc = new Scanner(System.in);
		System.out.println("precisez les entrees/sorties de client");
		System.out.println("'+' suivi du numeros de repas puis du nombre de clients pour une entree");
		System.out.println("'-' suivi du numeros de repas puis du nombre de clients pour une sortie");
		System.out.println("'r+' suivi du numéro pour faire avancer le repas dans le status");
		System.out.println("'r-' suivi du numéro pour faire reculer le repas dans le status");


		while(true) {
			System.out.println("nombre de clients dans le restaurant:" + service.repasLst.values().stream().map(r -> r.nombreClient).reduce(Integer::sum).orElse(0));
			service.repasLst.entrySet()
					.stream()
					.forEach(es -> System.out.println("repas " + es.getKey() + " " + es.getValue().status));
            Event event = new EventBuilder().withInput(sc.nextLine()).build();

            restaurant.publish(event);


		}
		
		
		
	}

}
