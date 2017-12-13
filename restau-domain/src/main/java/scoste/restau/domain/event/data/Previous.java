package scoste.restau.domain.event.data;

import java.io.Serializable;
import java.util.function.Function;

public class Previous<P extends Serializable> implements Data<P> {

    public P data;

    public Previous (P data){
        this.data = data;
    }

    public <N extends Serializable> Next<N> toNext(Function<P,N> function){
        return new Next<>(function.apply(data));
    }

    public Next<P> toNext(){
        return new Next<>(data);
    }

    @Override
    public P getData() {
        return data;
    }

    @Override
    public String toRawString() {
        return data.toString();
    }

    @Override
    public String toString() {
        return "Previous{" +
                "data=" + data +
                '}';
    }
}
