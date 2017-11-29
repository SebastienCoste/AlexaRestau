package scoste.restau;

import scoste.obs.Publisher;
import scoste.restau.domain.gestionnaire.GestionnaireRestaurant;
import scoste.restau.domain.repas.SalleRestaurant;
import scoste.restau.domain.table.BoyRestaurant;
import scoste.restau.domain.table.StatusTable;
import scoste.restau.domain.table.Table;
import scoste.restau.event.Event;
import scoste.restau.event.EventBuilder;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {



	public static void main(String[] args) {

		Publisher<Event> restaurant = new RestauPublisher();
		SalleRestaurant salleRestaurant = new SalleRestaurant();
		restaurant.subscribe(salleRestaurant);

		BoyRestaurant boys = new BoyRestaurant();
		restaurant.subscribe(boys);
		salleRestaurant.boys = boys;
		boys.tables = Arrays.asList(1,2,3)
                .stream()
                .map(t -> new Table(t, StatusTable.FREE))
                .collect(Collectors.toList());

        GestionnaireRestaurant manager = new GestionnaireRestaurant();
        restaurant.subscribe(manager);


		Scanner sc = new Scanner(System.in);
		System.out.println("precisez les entrees/sorties de client");
		System.out.println("'+' suivi du numeros de repas puis du nombre de clients pour une entree");
		System.out.println("'-' suivi du numeros de repas puis du nombre de clients pour une sortie");
		System.out.println("'r+' suivi du numéro pour faire avancer le repas dans le status");
        System.out.println("'r-' suivi du numéro pour faire reculer le repas dans le status");
        System.out.println("'bc' suivi du numéro de table pour la nettoyer");


		while(true) {
			System.out.println("nombre de clients dans le restaurant:" +
                    salleRestaurant.repasLst
                            .values()
                            .stream()
                            .map(r -> r.nombreClient)
                            .reduce(Integer::sum)
                            .orElse(0));

			salleRestaurant.repasLst.entrySet()
					.stream()
					.forEach(es -> System.out.println("repas " + es.getKey() + " " + es.getValue().status));

			boys.tables.stream()
                    .forEach(t -> System.out.println("table: " + t.numero + " - " + t.status.name()));

			Event event = new EventBuilder().withInput(sc.nextLine()).build();

            restaurant.publish(event);


		}
		
		
		
	}

}
