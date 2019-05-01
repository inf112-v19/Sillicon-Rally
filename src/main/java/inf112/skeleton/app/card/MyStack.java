package inf112.skeleton.app.card;

/**
 * Created by Martin on 01/02/2019.
 */

public class MyStack<E> {
    private Node head;
    public int size;

    public class Node {
        private Node next;
        private E payload;


        public Node(E item) {
            this.payload = item;
            this.next = null;
        }
    }


    public MyStack() {
        size = 0;
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public E peek() {
        return this.head.payload;
    }

    public E pop() {
        if (isEmpty())
            return null;
        Node newTop = head.next;
        E item = head.payload;
        head = newTop;
        size--;

        return item;
    }

    public void push(E item) {
        Node newTop = new Node(item);
        newTop.next = head;
        head = newTop;
        size++;
    }
}