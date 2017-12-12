package scoste.restau.old.domain.repas;

public class Repas {

   public Integer nombreClient;
   public Integer numero;
   public StatusRepas status;
   public Integer idTable;
   
   public Repas(Integer numero, Integer nombreClient, StatusRepas status, Integer idTable) {
       this.nombreClient= nombreClient;
	   this.numero = numero;
	   this.status = status;
	   this.idTable = idTable;
   }

   public Repas prochaineEtapeStatusRepas(boolean avance){
       return new Repas(this.numero,
               this.nombreClient,
               avance ? this.status.getNextStatus() : this.status.getPreviousStatus(),
               this.idTable);
   }


   
}
