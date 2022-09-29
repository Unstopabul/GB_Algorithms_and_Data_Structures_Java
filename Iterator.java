package ru.gb.hw4;

public class Iterator {
    public static void main(String[] args) {
        LinkedList<Integer> customLinkedList = new LinkedListImpl<>();
        customLinkedList.addLast(1);
        customLinkedList.addLast(2);
        customLinkedList.addLast(3);
        customLinkedList.addLast(4);
        customLinkedList.addLast(5);
        // TODO: 22.09.2022 Реализовать итератор для связного списка.
        // TODO: 22.09.2022 Должно работать.
        for (Integer x : customLinkedList) {
            System.out.println(x);
        }
    }
}
