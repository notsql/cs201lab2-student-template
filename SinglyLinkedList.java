import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {

        private E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    public SinglyLinkedList() {}

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);

        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()) {
            tail = null;
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    void reposition(List<Node<E>> emulatedList, Node<E> min, Node<E> max) {}

    // write your codes here
    public void swap() {
        List<Node<E>> sortedList = new ArrayList<>();
        List<Node<E>> emulatedList = new ArrayList<>();
        Map<Node<E>, Integer> position = new HashMap<>();
        Node<E> current = head;

        int idx = 0;
        while (current != null) {
            sortedList.add(current);
            emulatedList.add(current);
            position.put(current, idx++);
            current = current.getNext();
        }

        sortedList.sort((v1, v2) -> v1.getElement().compareTo(v2.getElement()));

        int n = this.size / 2;
        for (int i = 0; i < n; i++) {
            Node<E> min = sortedList.get(i);
            Node<E> max = sortedList.get(this.size - i - 1);

            emulatedList.set(position.get(min), max);
            emulatedList.set(position.get(max), min);
        }

        for (int i = 0; i < this.size - 1; i++) {
            emulatedList.get(i).setNext(emulatedList.get(i + 1));
        }

        this.head = emulatedList.get(0);
        this.tail = emulatedList.get(emulatedList.size() - 1);
        this.tail.setNext(null);
    }
}
