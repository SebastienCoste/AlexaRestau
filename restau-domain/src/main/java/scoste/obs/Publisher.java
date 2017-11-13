package scoste.obs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Publisher<T extends Observable> {

    Set<Subscriber<T>> observers = new HashSet<>();
    List<T> events = new ArrayList<>();

    public void subscribe(Subscriber<T> sub){
        observers.add(sub);
    }

    public void unsubscribe(Subscriber<T> sub){
        observers.remove(sub);
    }

    public void publish(T event){
        events.add(event);
        observers.stream().forEach(s -> s.apply(event));
    }
}
