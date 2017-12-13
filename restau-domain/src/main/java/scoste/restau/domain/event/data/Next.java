package scoste.restau.domain.event.data;

import java.io.Serializable;
import java.util.function.Function;

public class Next<N extends Serializable> implements Data<N> {

    public N data;

    public Next (N data){
        this.data = data;
    }

    public <P extends Serializable> Previous<P> toPrevious(Function<N, P> function){
        return new Previous<>(function.apply(data));
    }

    public Previous<N> toPrevious(){
        return new Previous<>(data);
    }

    @Override
    public String toRawString() {
        return data.toString();
    }

    @Override
    public String toString() {
        return "Next{" +
                "data=" + data +
                '}';
    }


    @Override
    public N getData() {
        return data;
    }
}
