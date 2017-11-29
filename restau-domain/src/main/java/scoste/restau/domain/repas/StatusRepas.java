package scoste.restau.domain.repas;

import java.util.Arrays;

public enum StatusRepas {

    CHOIX_EN_COURS(1),
    COMMANDE_PASSEE(2),
    SERVI(3),
    REPAS_FINI(4),
    ADDITION_PAYEE(5);

    int ordre;


    StatusRepas(int ordre) {
        this.ordre = ordre;
    }

    public StatusRepas getNextStatus(){
        return Arrays.asList(StatusRepas.values())
                .stream()
                .filter( sr -> sr.ordre == this.ordre+1)
                .findFirst()
                .orElse(this);
    }

    public StatusRepas getPreviousStatus(){
        return Arrays.asList(StatusRepas.values())
                .stream()
                .filter( sr -> sr.ordre == this.ordre-1)
                .findFirst()
                .orElse(this);
    }


}
