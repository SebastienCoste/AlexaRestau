package scoste.restau.domain.event.data;

import java.io.Serializable;

public interface Data<D extends Serializable> {

    D getData();

    String toRawString();

}
