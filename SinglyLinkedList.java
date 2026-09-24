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
        Node<E> current = head;

        while (current != null) {
            sortedList.add(current);
            emulatedList.add(current);
            current = current.getNext();
        }

        sortedList.sort((v1, v2) -> v1.getElement().compareTo(v2.getElement()));

        int total = this.size / 2;
        for (int i = 0; i < total; i++) {
            Node<E> min = sortedList.get(0);
            Node<E> max = sortedList.get(sortedList.size() - 1);

            int minOldIdx = emulatedList.indexOf(min);
            int maxOldIdx = emulatedList.indexOf(max);

            emulatedList.set(minOldIdx, max);
            emulatedList.set(maxOldIdx, min);

            sortedList.remove(0);
            sortedList.remove(sortedList.size() - 1);
        }

        for (int i = 0; i < this.size - 1; i++) {
            emulatedList.get(i).setNext(emulatedList.get(i + 1));
        }

        this.head = emulatedList.get(0);
        this.tail = emulatedList.get(emulatedList.size() - 1);
        emulatedList.getLast().setNext(null);
    }
}
