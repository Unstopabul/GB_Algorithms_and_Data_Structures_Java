package ru.gb.hw3.task1;

public class App {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 14, 15, 16};
        System.out.println(binarySearchMissedElement(array));

        array = new int[]{1, 2, 4, 5, 6};
        System.out.println(binarySearchMissedElement(array));

        array = new int[]{2, 3, 4, 5, 6, 7, 8};
        System.out.println(binarySearchMissedElement(array));

        array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0};
        System.out.println(binarySearchMissedElement(array));

        array = new int[]{0};
        System.out.println(binarySearchMissedElement(array));
    }

    private static int binarySearchMissedElement(int[] array) {
        if (array.length == 0) return -1;

        int low = 0;
        int high = array.length - 1;
        int mid;
        do {
            mid = (low + high) / 2;

            if (array[mid] > (mid + 1)) {
                if (mid == high) {
                    return array[mid] - 1;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }

            if (low >= high) {
                if (array[high] == high + 1) return high + 2;
                else return high + 1;
            }
        } while(low < high);
        return -1;
    }
}
