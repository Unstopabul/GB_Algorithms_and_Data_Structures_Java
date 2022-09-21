package ru.geekbrains.homework2;

import java.util.Comparator;

public class Notebook {
    private Brand brand;
    private double price;
    private int ram;

    public Notebook(Brand brand, double price, int ram) {
        this.brand = brand;
        this.price = price;
        this.ram = ram;
    }

    public Brand getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getRam() {
        return ram;
    }
}
