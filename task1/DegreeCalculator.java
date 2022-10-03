package ru.gb.hw5.task1;

public class DegreeCalculator {
    public static int degree(int x, int n) {
        // TODO: 26.09.2022 Возвести число x в степень n.
        if (n < 0) {
            throw new UnsupportedOperationException();
        }

        if (n == 0) {
            return 1;
        }
        if (x == 0) {
            return 0;
        }

        if (n == 1 || x == 1) {
            return x;
        } else {
            return x * degree(x, n - 1);
        }
    }
}
