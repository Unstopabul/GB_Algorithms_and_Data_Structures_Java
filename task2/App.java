package ru.gb.hw3.task2;

public class App {
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque(3);
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);

        System.out.println(deque.peekLast());       // 2
        System.out.println(deque.peekFirst());      // 3

        deque.pollLast();
        System.out.println(deque.peekLast());       // 1

        deque.addLast(5);
        System.out.println(deque.peekLast());       // 5
    }
}
