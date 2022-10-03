package ru.gb.hw5.task2;

import java.util.Random;

public class App {
    private static final int BAG_CAPACITY = 16;
    private static final int NUMBER_OF_THINGS = 10;
    private static Bag[][] bags = new Bag[NUMBER_OF_THINGS][BAG_CAPACITY];
    private static Item[] items;

    public static void main(String[] args) {
        items = generateItems();

        System.out.println("Вместимость мешка: " + BAG_CAPACITY + " кг");
        System.out.println("Список вещей: ");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%d. Стоимость: %d, Вес: %d\n",
                    (i + 1), items[i].getPrice(), items[i].getWeight());
        }

        Bag bestBag = collectBag(NUMBER_OF_THINGS - 1, BAG_CAPACITY - 1);

        System.out.println("\nСамый дорогой набор:");
        int j = 0;
        for (Item item: bestBag.getItems()) {
            System.out.printf("%d. Стоимость: %d, Вес: %d\n",
                    ++j, item.getPrice(), item.getWeight());
        }

        System.out.println("\nСтоимость набора: " + bestBag.getTotalPrice());
        System.out.println("Вес набора: " + (bestBag.getCapacity() - bestBag.getFreeSpace()));

        System.out.println("\nНепоместившиеся вещи: ");
        for (int i = 0; i < items.length; i++) {
            boolean exists = false;
            for (Item item: bestBag.getItems()) {
                if (item == items[i])
                    exists = true;

            }
            if (exists) continue;

            System.out.printf("%d. Стоимость: %d, Вес: %d\n",
                    (i + 1), items[i].getPrice(), items[i].getWeight());
        }
    }

    private static Bag collectBag(int itemIdx, int bagIdx) {
        // каждый вызов метода - решение подзадачи для i-го предмета и j-й вместимости рюкзака
        if (itemIdx < 0 || bagIdx < 0)
            return null;

        if (bags[itemIdx][bagIdx] != null)  // если уже обработан, просто вернуть
            return bags[itemIdx][bagIdx];
        
        Item item = items[itemIdx];
        Bag bag = new Bag(bagIdx + 1);  // bagIdx = 0 => рюкзак 1 кг и т.д.
        bag.addItem(item);

        // Добавить предметы по оставшемуся свободному месту (решение подзадачи)
        Bag bagPrev;
        int freeSpace = bag.getFreeSpace();
        if (freeSpace > 0) {
            bagPrev = collectBag(itemIdx - 1, freeSpace - 1);
            bag.addFromBag(bagPrev);
        }

        // если предыдущий максимум для этой вместимости больше, берём его (подзадача)
        bagPrev = collectBag(itemIdx - 1, bagIdx);
        if (bagPrev != null && (bag.getTotalPrice() < bagPrev.getTotalPrice())) {
            bag = bagPrev;
        }

        bags[itemIdx][bagIdx] = bag;
        return bag;
    }

    private static Item[] generateItems() {
        Item[] items = new Item[NUMBER_OF_THINGS];
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_THINGS; i++) {
            int weight = random.nextInt(5) + 1;     // от 1 до 5 кг
            int cost = (random.nextInt(5) + 1) * 100;   // от 100 до 500
            items[i] = (new Item(weight, cost));
        }
        return items;
    }
}
