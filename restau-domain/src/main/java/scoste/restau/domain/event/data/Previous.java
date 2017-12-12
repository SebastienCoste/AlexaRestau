package scoste.restau.domain.event.data;

public class Previous<P> implements Data<P> {

    public P data;

    public Previous (P data){
        this.data = data;
    }


    @Override
    public P getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Previous{" +
                "data=" + data +
                '}';
    }
}
