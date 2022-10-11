package ru.gb.hw8;

import java.util.LinkedList;

public class HashTableImpl <K, V> implements HashTable<K, V>{
    private final Object[] data;
    private int size;

    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("key: %s -> value: %s", key, value);
        }
    }

    public HashTableImpl(int initialCapacity) {
        this.data = new Object[initialCapacity];
    }

    public HashTableImpl() {
        this(16);
    }

    @Override
    public boolean put(K key, V value) {
        LinkedList<Item<K, V>> list;

        int indexFromHashFunc = hashFunc(key);

        if (data[indexFromHashFunc] == null) {
            data[indexFromHashFunc] = new Item<>(key, value);
            size++;
            return true;

        } else if (data[indexFromHashFunc] instanceof Item) {
            Item item = (Item) data[indexFromHashFunc];
            if (isKeysEquals(item, key)) {
                item.setValue(value);
            } else {    // create LinkedList
                list = new LinkedList<>();
                list.add(item);
                list.add(new Item<>(key, value));
                data[indexFromHashFunc] = list;
                size++;
            }
            return true;

        } else if (data[indexFromHashFunc] instanceof LinkedList) {
            list = (LinkedList) data[indexFromHashFunc];
            for (Item item : list) {
                if (isKeysEquals(item, key)) {
                    item.setValue(value);
                    return true;
                }
            }

            list.add(new Item<>(key, value));
            size++;
            return true;
        }

        return false;
    }

    private boolean isKeysEquals(Item<K, V> item, K key) {
        return (item.getKey() == null) ? (key == null) : (item.getKey().equals(key));
    }

    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    @Override
    public V get(K key) {
        Item<K, V> item = null;
        int indexFromHashFunc = hashFunc(key);

        if (data[indexFromHashFunc] instanceof Item) {
            if (isKeysEquals((Item) data[indexFromHashFunc], key)) {
                item = (Item) data[indexFromHashFunc];
            }
        } else if (data[indexFromHashFunc] instanceof LinkedList) {
            for (Item itemTmp : (LinkedList<Item>) data[indexFromHashFunc]) {
                if (isKeysEquals(itemTmp, key)) {
                    item = itemTmp;
                }
            }
        }

        return item != null ? item.getValue() : null;
    }

    @Override
    public V remove(K key) {
        Item<K, V> item = null;
        int indexFromHashFunc = hashFunc(key);

        if (data[indexFromHashFunc] instanceof Item) {
            if (isKeysEquals((Item) data[indexFromHashFunc], key)) {
                item = (Item) data[indexFromHashFunc];
                data[indexFromHashFunc] = null;
                size--;
            }
        } else if (data[indexFromHashFunc] instanceof LinkedList) {
            LinkedList<Item> list = (LinkedList) data[indexFromHashFunc];
            for (Item itemTmp : list) {
                if (isKeysEquals(itemTmp, key)) {
                    item = itemTmp;
                }
            }
            if (item != null) {
                list.remove(item);
                if (list.size() == 1) { // Convert back to Item
                    data[indexFromHashFunc] = list.pop();
                }
                size--;
            }
        }

        return item != null ? item.getValue() : null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%s = [%s]%n", i, data[i]));
        }
        return sb.toString();
    }
}
