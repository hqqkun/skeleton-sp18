public class ArrayDeque<T> {
    private T[] data;
    private int front;
    private int rear;
    private int size;

    public ArrayDeque() {
        data = (T[]) new Object[8];
        front = 0;
        rear = 1;
        size = 0;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    private boolean isFull() {
        return size == data.length;
    }
    
    private int minusOne(int index){
        return (index -1 + data.length) % data.length;
    }
    
    private int plusOne(int index){
        return (index + 1) % data.length;
    }
    
    private int getProperSize(int dir){
        if (dir == 1) {
            return data.length << 1;
        }
        // goes down
        return data.length >> 1;
    }
    
    private void reshape(int newSize) {
        T[] tmp = (T[]) new Object[newSize];

        int start = 1;
        for (front = plusOne(front); start <= size; ++start) {
            tmp[start] = data[front];
            front = plusOne(front);
        }
        data = tmp;
        front = 0;
        rear = size + 1;
    }
    
    private void checkUsage() {
        if (data.length >= 16 && ((size << 2)< data.length)) {
            reshape(getProperSize(0));
        }
    }
    
    public void addLast(T item) {
        if (isFull()) {
            reshape((getProperSize(1)));
        }
        data[rear] = item;
        rear = plusOne(rear);
        ++size;
    }
    
    public void addFirst(T item) {
        if (isFull()) {
            reshape((getProperSize(1)));
        }
        data[front] = item;
        front = minusOne(front);
        ++size;
    }
    
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        --size;
        front = plusOne(front);
        T tmp = data[front];
        data[front] = null;
        checkUsage();
        return tmp;
    }
    
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        rear = minusOne(rear);
        T tmp = data[rear];
        data[rear] = null;
        --size;
        checkUsage();
        return tmp;
    }
    
    public void printDeque(){
        if (isEmpty()) {
            return;
        }
        int ptr = plusOne(front);
        for (int i = 0; i != size; ++i) {
            System.out.print(data[ptr] + " ");
            ptr = plusOne(ptr);
        }
        System.out.print('\n');
    }
    
    public T get(int index){
        if (index >= size) {
            return null;
        }
        int start = plusOne(front);
        return data[(start + index) % data.length];
    }
}
