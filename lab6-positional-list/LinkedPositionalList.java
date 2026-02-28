import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPositionalList<E> implements Iterable<E> {

    //Nested Node Class to implement Position
    private static class Node<E> implements Position<E> {
        private E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() {
           //if next is null, treat as if it's removed
            if (next == null) {
                throw new IllegalStateException("Position is no longer valid.");
            }
            return element;
        }

        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> prev) { this.prev = prev; }
        public void setNext(Node<E> next) { this.next = next; }
        public void setElement(E element) { this.element = element; }
    }

   //set two sentinel nodes
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    //constructor for sentinel nodes
    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
        size = 0;
    }

    //makes sure Position p is actually a Node in the list
    private Node<E> validate(Position<E> p) {
        if (p == null) {
            throw new IllegalArgumentException("Position is null.");
        }
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Position is not a valid node type.");
        }

        Node<E> node = (Node<E>) p;

        //if node.next is null, mark as removed from list
        if (node.getNext() == null) {
            throw new IllegalArgumentException("Position is no longer in the list.");
        }
        return node;
    }

    //turns Node into a Position but returns sentinels as null
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) {
            return null;
        }
        return node;
    }

    //addBetween inserts a new node between two existing nodes
    private Position<E> addBetween(E e, Node<E> prev, Node<E> next) {
        Node<E> newest = new Node<>(e, prev, next);

        prev.setNext(newest);
        next.setPrev(newest);
        size++;

        return newest;
    }

    // Positional methods
    public Position<E> first() {
        return position(header.getNext());
    }
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    public Position<E> before(Position<E> p) {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    public Position<E> after(Position<E> p) {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    // Add methods
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    public Position<E> addBefore(Position<E> p, E e) {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    public Position<E> addAfter(Position<E> p, E e) {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    //Remove methods
    public E set(Position<E> p, E e) {
        Node<E> node = validate(p);

        //set old value to new value
        E old = node.getElement();
        node.setElement(e);
        return old;
    }

    public E remove(Position<E> p) {
        Node<E> node = validate(p);
        Node<E> prev = node.getPrev();
        Node<E> next = node.getNext();

        prev.setNext(next);
        next.setPrev(prev);
        size--;

        E removed = node.getElement();

        //make sure node can't be used again
        node.setElement(null);
        node.setPrev(null);
        node.setNext(null);

        return removed;
    }

    //Nested Iterator class
    private class ElementIterator implements Iterator<E> {

        //starts at first element
        private Position<E> cursor = first();

        public boolean hasNext() {
            return cursor != null;
        }

        public E next() {
            if (cursor == null) {
                throw new NoSuchElementException("No more elements.");
            }

            //gets current element
            E answer = cursor.getElement();

            //moves cursor forward to next position
            cursor = after(cursor);

            return answer;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
}
