package scoste.restau.domain.table;

import scoste.obs.Subscriber;
import scoste.restau.event.Event;
import scoste.restau.event.value.EventTableValue;

import java.util.List;
import java.util.Optional;

public class BoyRestaurant  extends Subscriber<Event> {

    public List<Table> tables;

    @Override
    public void apply(Event event) {
        switch (event.type){
            case LIBERE_TABLE:
                setTableToClean((EventTableValue) event.valeur);
                break;
            case CLEAN_TABLE:
                cleanTable((EventTableValue) event.valeur);
                break;
            case OCCUPPE_TABLE:
                setTableOccuppied((EventTableValue) event.valeur);
                break;
        }
    }

    public synchronized Integer getAFreeTable() {

        Optional<Table> table = tables.stream()
                .filter(t -> t.status == StatusTable.FREE)
                .findFirst();

        table.ifPresent(t -> t.status = StatusTable.OCCUPIED);
        return table.map(t -> t.numero).orElse(-1);
    }

    private void setTableOccuppied(EventTableValue valeur) {
        tables.stream()
                .filter(t -> t.numero == valeur.idTable && t.status == StatusTable.FREE)
                .findFirst()
                .ifPresent(t -> t.status = StatusTable.OCCUPIED);
    }

    private void cleanTable(EventTableValue valeur) {
        tables.stream()
                .filter(t -> t.numero == valeur.idTable && t.status == StatusTable.TO_CLEAN)
                .findFirst()
                .ifPresent(t -> t.status = StatusTable.FREE);
    }

    private void setTableToClean(EventTableValue valeur) {
        tables.stream()
                .filter(t -> t.numero == valeur.idTable && t.status == StatusTable.OCCUPIED)
                .findFirst()
                .ifPresent(t -> t.status = StatusTable.TO_CLEAN);

    }
}
