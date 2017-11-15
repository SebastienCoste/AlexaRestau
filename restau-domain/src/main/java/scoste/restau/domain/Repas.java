package scoste.restau.domain;

import scoste.restau.domain.StatusRepas;

public class Repas {

   public Integer nombreClient;
   public Integer numero;
   public StatusRepas status;
   
   public Repas(Integer numero, Integer nombreClient, StatusRepas status) {
       this.nombreClient= nombreClient;
	   this.numero = numero;
	   this.status = status;
   }

   public Repas prochaineEtapeStatusRepas(boolean avance){
       return new Repas(this.numero, this.nombreClient, avance ? this.status.getNextStatus() : this.status.getPreviousStatus());
   }


   
}
