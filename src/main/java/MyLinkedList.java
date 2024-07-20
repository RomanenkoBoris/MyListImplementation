import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private int size;
    private Node<E> tail;

    public MyLinkedList() {
        head = null;
        size = 0;
        tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E e) {
        trowNullPointerException(e);
        if (size() == 0)
            return false;
        Node<E> leftPointer = getHead();
        Node<E> rightPointer = getTail();
        while (!leftPointer.equals(rightPointer)) {
            if (leftPointer.getValue().equals(e) || rightPointer.getValue().equals(e))
                return true;
            leftPointer = leftPointer.getNext();
            rightPointer = rightPointer.getPrevious();
        }
        return leftPointer.getValue().equals(e);
    }

    @Override
    public void set(int index, E e) {
        trowNullPointerException(e);
        trowIndexOutOfBoundsException(index);
        Node<E> node = findNodeThruTheIndex(index);
        node.setValue(e);
    }

    @Override
    public void add(E e) {
        trowNullPointerException(e);
        size++;
        if (head == null) {
            head = new Node<>(e);
            tail = head;
            return;
        }
        Node<E> node = new Node<>(e);
        tail.setNext(node);
        node.setPrevious(tail);
        tail = node;
        tail.setNext(null);
    }

    @Override
    public void add(int index, E e) {
        trowNullPointerException(e);
        trowIndexOutOfBoundsException(index);
        size++;
        Node<E> tmp = new Node<>(e);
        Node<E> node = findNodeThruTheIndex(index);
        tmp.setNext(node);
        tmp.setPrevious(node.getPrevious());
        node.getPrevious().setNext(tmp);
        node.setPrevious(tmp);
    }

    @Override
    public void remove(int index) {
        trowIndexOutOfBoundsException(index);
        Node<E> node = findNodeThruTheIndex(index);
        size--;
        if (index == 0) {
            head = node.next;
            head.setPrevious(null);
        } else if (index == size()) {
            tail = node.getPrevious();
            tail.setNext(null);
        } else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
            node.setNext(null);
            node.setPrevious(null);
        }
    }

    @Override
    public E get(int index) {
        trowIndexOutOfBoundsException(index);
        return findNodeThruTheIndex(index).getValue();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> node = getHead();

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E e = node.getValue();
                node = node.getNext();
                return e;
            }
        };
    }

    public void addFirst(E e) {
        if (size() == 0)
            add(e);
        else {
            head.setPrevious(new Node<>(e));
            head.getPrevious().setNext(head);
            head = head.getPrevious();
            size++;
        }
    }

    public void addLast(E e) {
        if (size() == 0)
            add(e);
        else {
            tail.setNext(new Node<>(e));
            tail.getNext().setPrevious(tail);
            tail = tail.getNext();
            size++;
        }
    }

    public E getFirst() {
        if (head != null)
            return getHead().getValue();
        else
            throw new NoSuchElementException("List is empty");
    }

    public E getLast() {
        if (tail != null)
            return getTail().getValue();
        else
            throw new NoSuchElementException("List is empty");
    }

    public E removeFirst() {
        if (head != null) {
            E e = getHead().getValue();
            remove(0);
            return e;
        } else
            throw new NoSuchElementException("List is empty");
    }

    public E removeLast() {
        if (tail != null) {
            E e = getTail().getValue();
            remove(size()-1);
            return e;
        } else
            throw new NoSuchElementException("List is empty");
    }

    public Node<E> getHead() {
        return head;
    }

    public Node<E> getTail() {
        return tail;
    }

    @Override
    public String toString() {
        Node<E> node = this.head;
        StringBuilder stringBuilder = new StringBuilder("[");
        while (node != null) {
            stringBuilder.append(node.getValue());
            if (node.getNext() != null)
                stringBuilder.append(", ");
            node = node.getNext();
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void trowNullPointerException(E e) {
        if (e == null)
            throw new NullPointerException("Please enter a valid data");
    }

    private void trowIndexOutOfBoundsException(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("There is no such an index in the list");
    }

    private Node<E> findNodeThruTheIndex(int index) {
        if (index == 0)
            return getHead();
        if (index == size() - 1)
            return getTail();
        Node<E> node;
        if (index <= size() / 2) {
            node = getHead();
            for (int i = 0; i < index; i++) {
                node = node.getNext();
            }
        } else {
            node = getTail();
            for (int i = size() - 1; i > index; i--) {
                node = node.getPrevious();
            }
        }
        return node;
    }

    public class Node<T> {
        private Node<T> previous;
        private T Value;
        private Node<T> next;

        public Node(T value) {
            Value = value;
        }

        public Node(Node<T> previous, T value, Node<T> next) {
            this.previous = previous;
            Value = value;
            this.next = next;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        public T getValue() {
            return Value;
        }

        public void setValue(T value) {
            Value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}