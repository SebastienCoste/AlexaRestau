package scoste.obs;

public abstract class Subscriber<T extends Observable> {

    public Publisher source;

    abstract public void apply (T event);

}
