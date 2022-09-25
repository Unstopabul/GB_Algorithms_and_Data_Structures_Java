package ru.gb.hw3.task2;

public class Deque<T> {
    private int maxSize;
    private Object[] queue;
    private int front;
    private int rear;
    private int items;

    public Deque(int size){
        maxSize = size;
        queue = new Object[maxSize];
        front = 0;
        rear = -1;
        items = 0;
    }

    public boolean addLast(T i){
        if (isFull()) {
            return false;
        }

        if (rear == maxSize - 1)
            rear = -1;
        queue[++rear] = i;

        items++;
        return true;
    }

    public boolean addFirst(T i){
        if (isFull()) {
            return false;
        }

        if (front == 0)
            front = maxSize;
        queue[--front] = i;

        items++;
        return true;
    }

    public T pollFirst(){
        T temp = peekFirst();
        if (++front == maxSize)
            front = 0;
        items--;
        return temp;
    }

    public T pollLast(){
        T temp = peekLast();
        if (--rear < 0)
            rear = maxSize - 1;
        items--;
        return temp;
    }

    public T peekFirst(){
        if (isEmpty()) {
            return null;
        }

        return (T) queue[front];
    }

    public T peekLast(){
        if (isEmpty()) {
            return null;
        }

        return (T) queue[rear];
    }

    public boolean isEmpty(){
        return (items == 0);
    }
    public boolean isFull(){
        return (items == maxSize);
    }
    public int size(){
        return items;
    }
}
