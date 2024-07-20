import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {

    private int size = 0;
    private static final int INITIAL_CAPACITY = 4;
    private T[] data;

    public MyArrayList() {
        data = (T[]) new Object[INITIAL_CAPACITY];
    }

    public MyArrayList(int capacity) {
        data = (T[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T t) {
        if (size() == 0)
            return false;
        if (t == null)
            throw new NullPointerException("Please enter the correct value");
        for (int i = 0; i < size(); i++) {
            if (data[i].equals(t))
                return true;
        }
        return false;
    }

    @Override
    public void set(int index, T t) {
        if (index < 0 || index > (size() - 1))
            throw new IndexOutOfBoundsException("Index must be from 0 to " + (size() - 1));
        if (t == null)
            throw new NullPointerException("Please enter the correct value");
        data[index] = t;
    }

    @Override
    public void add(T t) {
        if (isItFull())
            increaseCapacity();
        data[size++] = t;
    }

    @Override
    public void add(int index, T t) {
        if (index < 0 || index > (size() - 1))
            throw new IndexOutOfBoundsException("Index must be from 0 to " + (size() - 1));
        if (t == null)
            throw new NullPointerException("Please enter the correct value");
        if (isItFull())
            increaseCapacity();
        size++;
        for (int i = size() - 2; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = t;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > (size() - 1))
            throw new IndexOutOfBoundsException("Index must be from 0 to " + (size() - 1));
        if (index == size() - 1)
            size--;
        else {
            for (int i = index + 1; i < size(); i++) {
                data[i - 1] = data[i];
            }
            size--;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int pointer = 0;
            @Override
            public boolean hasNext() {
                return pointer<size();
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return get(pointer++);
            }
        };
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > (size() - 1))
            throw new IndexOutOfBoundsException("Index must be from 0 to " + (size() - 1));
        return data[index];
    }

    private void increaseCapacity() {
        T[] tmp = (T[]) new Object[data.length * 2];
        for (int i = 0; i < size(); i++) {
            tmp[i] = data[i];
        }
        data = tmp;
    }

    private boolean isItFull() {
        return size() >= (data.length * 0.75);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            if (i == size() - 1)
                stringBuilder.append(data[i] + "]");
            else
                stringBuilder.append(data[i] + ",");
        }
        return stringBuilder.toString();
    }


}
