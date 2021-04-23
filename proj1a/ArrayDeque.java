public class ArrayDeque<T> {
    private T[] data;
    private int front;
    private int rear;
    private int maxSize;

    public ArrayDeque() {
        data = (T[]) new Object[8];
        maxSize = 8;
        front = rear = 0;
    }
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }
    public boolean isEmpty() {
        return front == rear;
    }
    private boolean isFull() {
        return plusOne(rear) == front;
    }
    private int minusOne(int index){
        return (index -1 + maxSize) % maxSize;
    }
    private int plusOne(int index){
        return (index + 1) % maxSize;
    }
    private int getProperSize(int dir){
        if(dir == 1){
            return maxSize << 1;
        }
        // goes down
//         int sentinalSize = size() << 2;
//         int newSize = maxSize;
//         for(; newSize > sentinalSize; newSize >>= 1)
//             ;
//         return newSize;
        return maxSize >> 1;
    }
    private void reshape(int newSize) {
        int size = size();
        T[] tmp = (T[]) new Object[newSize];
        
        int start = 1;
        int end = plusOne(rear);
        int oldFront = plusOne(front);
        for(;oldFront != end;oldFront = plusOne(oldFront)){
            tmp[start] = data[oldFront];
            ++start;
        }
        data = tmp;
        front = 0;
        rear = size;
        maxSize = newSize;
    }
    private void cheakUsage() {
        int size = size();
        if(size >= 16 && ((size<<2)< maxSize)){
            reshape(getProperSize(0));
        }
    }
    public void addLast(T item) {
        if (isFull()) {
            reshape((getProperSize(1)));
        }
        rear = plusOne(rear);
        data[rear] = item;
    }
    public void addFirst(T item) {
        if (isFull()) {
            reshape((getProperSize(1)));
        }
        data[front] = item;
        front = minusOne(front);
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        front = plusOne(front);
        T tmp = data[front];
        data[front] = null;
        cheakUsage();
        return tmp;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T tmp = data[rear];
        data[rear] = null;
        rear = minusOne(rear);
        cheakUsage();
        return tmp;
    }
    public void printDeque(){
        if(isEmpty())
            return;
        int ptr = plusOne(front);
        int end = plusOne(rear);
        for(; ptr != end; ptr = plusOne(ptr)){
            System.out.print(data[ptr] + " ");
        }
        System.out.print('\n');
    }
    public T get(int index){
        if((index + 1) > size()){
            return null;
        }
        int start = plusOne(front);
        return data[(start + index) % maxSize];
    }
}
