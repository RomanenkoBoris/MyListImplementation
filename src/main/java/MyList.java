public interface MyList <T> extends Iterable<T> {
    int size();
    boolean contains(T t);
    void set(int index, T t);
    void  add(T t); // добавление в конец
    void add(int index, T t);
    void remove(int index);
    T get(int index);
}
