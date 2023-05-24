public interface MyCustomQueue extends MyCustomCollection {
    boolean offer(Object e);

    Object remove();

    Object poll();

    Object element();

    Object peek();

    boolean add(Object e);
}
