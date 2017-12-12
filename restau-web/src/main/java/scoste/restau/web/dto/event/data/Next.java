package scoste.restau.web.dto.event.data;

public class Next<N> implements Data<N> {

    public N data;

    public Next (N data){
        this.data = data;
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
