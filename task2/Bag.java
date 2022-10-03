package ru.gb.hw5.task2;

import java.util.ArrayList;

public class Bag {
    private int capacity;
    private int freeSpace;

    private ArrayList<Item> items;

    private int totalPrice;

    public Bag(int capacity) {
        this.capacity = capacity;
        this.freeSpace = capacity;
        this.items = new ArrayList<>();
        this.totalPrice = 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean addItem(Item item) {
        if (item.getWeight() <= this.freeSpace) {
            items.add(item);
            this.freeSpace -= item.getWeight();
            this.totalPrice += item.getPrice();
            return true;
        } else {
            return false;
        }
    }

    public boolean addFromBag(Bag bag) {
        // Либо добавляем все предметы, либо ничего
        if (bag == null || bag.getItems().size() == 0) {
            return true;
        }

        if (bag.getCapacity() - bag.getFreeSpace() > this.freeSpace) {
            return false;
        }

        for (Item item: bag.getItems()) {
            addItem(item);
        }
        return true;
    }
}
