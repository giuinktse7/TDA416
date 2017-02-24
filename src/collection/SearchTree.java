package collection;


public interface SearchTree<E extends Comparable<E>> {
    boolean add(E item);

    E find(E target);

    boolean contains(E target);

    E delete(E target);
    boolean remove(E target);
}
