package ru.gb.hw5.task1;

public class App {
    public static void main(String[] args) {
        int result;
        result = DegreeCalculator.degree(5, 3);
        System.out.println("5^3 = " + result);

        result = DegreeCalculator.degree(0, 2);
        System.out.println("0^2 = " + result);

        result = DegreeCalculator.degree(0, 0);
        System.out.println("0^0 = " + result);

        result = DegreeCalculator.degree(1, 50);
        System.out.println("1^50 = " + result);
    }
}
