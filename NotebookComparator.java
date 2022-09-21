package ru.geekbrains.homework2;

import java.util.Comparator;

public class NotebookComparator implements Comparator<Notebook> {

    @Override
    public int compare(Notebook o1, Notebook o2) {
        if (o1.getPrice() > o2.getPrice())
            return 1;
        else if (o1.getPrice() < o2.getPrice())
            return -1;

        if (o1.getRam() > o2.getRam())
            return 1;
        else if (o1.getRam() < o2.getRam())
            return -1;

        if (o1.getBrand().getPriority() > o2.getBrand().getPriority())
            return 1;
        else if (o1.getBrand().getPriority() < o2.getBrand().getPriority())
            return -1;

        return 0;
    }

}
