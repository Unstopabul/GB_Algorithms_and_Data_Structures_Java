package ru.geekbrains.homework2;

public enum Brand {
    LENUVO("Lenuvo", 1), ASOS("Asos", 2), MACNOTE("MacNote", 3),
    ESER("Eser", 4), XAMIOU("Xamiou", 5);
    private String name;

    private int priority;

    Brand(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public static Brand getBrandByPriority(int priority) {
        Brand brand = Brand.LENUVO;
        switch (priority) {
            case 1:
                brand = Brand.LENUVO;
                break;
            case 2:
                brand = Brand.ASOS;
                break;
            case 3:
                brand = Brand.MACNOTE;
                break;
            case 4:
                brand = Brand.ESER;
                break;
            case 5:
                brand = Brand.XAMIOU;
                break;
        }
        return brand;
    }

    public int getPriority() {
        return priority;
    }
}
