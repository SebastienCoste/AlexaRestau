package scoste.restau.old.domain.table;

public class Table {

    public int numero;
    public StatusTable status;

    public Table(int numero, StatusTable status) {
        this.numero = numero;
        this.status = status;
    }
}
