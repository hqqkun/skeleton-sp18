public class LinkedListDeque<T> {

    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public T helper(int needIndex, int myIndex) {
            if (needIndex == myIndex) {
                return item;
            }
            return next.helper(needIndex, myIndex + 1);
        }
    }

    private final Node head;
    private int size;

    public LinkedListDeque() {
        size = 0;
        head = new Node(null, null, null);
        head.next = head;
        head.prev = head;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /**  Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return head.next == head;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        ++size;
        Node tmp = new Node(item, head, head.next);
        head.next.prev = tmp;
        head.next = tmp;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node tmp = head.next;
        head.next = tmp.next;
        tmp.next.prev = head;
        --size;
        return tmp.item;
    }

    /**  Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        ++size;
        Node tmp = new Node(item, head.prev, head);
        head.prev.next = tmp;
        head.prev = tmp;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        --size;
        Node tmp = head.prev;
        head.prev = tmp.prev;
        tmp.prev.next = head;
        return tmp.item;
    }

    public T get(int index) {
        if ( (index + 1) > size) {
            return null;
        }
        int i = 0;
        Node ptr = head.next;
        for ( ; i != index; ptr = ptr.next) {
            ++i;
        }
        return ptr.item;
    }
    
    public T getRecursive(int index) {
        if ((index + 1) > size) {
            return null;
        }
        return head.next.helper(index, 0);
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        if (size == 0) {
            return;
        }
        for (Node ptr = head.next; ptr != head; ptr = ptr.next) {
            System.out.print(ptr.item + " ");
        }
        System.out.print('\n');
    }
}
