package scoste.obs;

public abstract class Subscriber<T extends Observable> {


    abstract public void apply (T event);

}
