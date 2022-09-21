package ru.geekbrains.homework2;

import jdk.nashorn.internal.ir.IfNode;

import java.util.Comparator;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Notebook[] notebooks = generateNotebooks();
        long start = System.currentTimeMillis();
        sortArray(notebooks, new NotebookComparator());
        long end = System.currentTimeMillis();

        System.out.println("Sorting time: " + (end - start) + "ms");
    }

    private static Notebook[] generateNotebooks() {
        Notebook[] notebooks = new Notebook[10000];
        Random random = new Random();
        for (int i = 0; i < notebooks.length; i++) {
            Brand brand = Brand.getBrandByPriority(random.nextInt(5) + 1);
            double price = ( random.nextInt(7) + 1 ) * 100.00;
            int ram = ( random.nextInt(5) + 1 ) * 4;    // 4, 8, 12, 16, 20
            if (ram >= 12) ram += 4;    // 4, 8, 16, 20, 24
            notebooks[i] = new Notebook(brand, price, ram);
        }
        return notebooks;
    }

    private static <T> void sortArray(T[] array, Comparator<T> comparator) {
        if (array.length == 0) return;

        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            T elem = array[i];

            while (j >= 0 && comparator.compare(array[j], elem) > 0) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = elem;
        }
    }
}
