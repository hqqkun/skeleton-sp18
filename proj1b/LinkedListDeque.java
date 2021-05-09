public class LinkedListDeque<Item> implements Deque<Item> {

    private class Node {
        private Node prev;
        private Item item;
        private Node next;

        public Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public Item helper(int needIndex, int myIndex) {
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
    @Override
    public int size() {
        return size;
    }

    /**  Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return head.next == head;
    }

    /** Adds an item of type T to the front of the deque. */
    @Override
    public void addFirst(Item item) {
        ++size;
        Node tmp = new Node(item, head, head.next);
        head.next.prev = tmp;
        head.next = tmp;
    }

    @Override
    public Item removeFirst() {
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
    @Override
    public void addLast(Item item) {
        ++size;
        Node tmp = new Node(item, head.prev, head);
        head.prev.next = tmp;
        head.prev = tmp;
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        --size;
        Node tmp = head.prev;
        head.prev = tmp.prev;
        tmp.prev.next = head;
        return tmp.item;
    }

    @Override
    public Item get(int index) {
        if (index >= size) {
            return null;
        }
        int i = 0;
        Node ptr = head.next;
        for ( ; i != index; ptr = ptr.next) {
            ++i;
        }
        return ptr.item;
    }

    public Item getRecursive(int index) {
        if ((index + 1) > size) {
            return null;
        }
        return head.next.helper(index, 0);
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
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