package labs.lab1;

public class QueueHeadTail<T> {
    private Node head;
    private Node tail;

    public QueueHeadTail() {

    }

    public QueueHeadTail(T[] arr) {

    }

    public void push(T item) {
        Node temp = new Node(item);
        if (head == null) {
            head = temp;
            tail = head;
            head.next = tail;
        } else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
    }

    public T put() {
        if (head == null) {
            return null;
        }
        T result = head.value;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        return result;
    }

    private class Node {
        private Node next;
        private Node prev;
        private T value;

        private Node(T value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("[");
        Node temp = head;
        while (temp != null) {
            string.append(temp.value);
            string.append(", ");
            temp = temp.next;
        }
        if (temp != head) {
            string.delete(string.length() - 2, string.length());
        }
        string.append("]");
        return string.toString();
    }
}
