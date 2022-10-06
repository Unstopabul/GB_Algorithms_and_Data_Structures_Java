package ru.gb.hw6;

import java.util.function.Consumer;

public interface Tree<T> {

    void add(T item);

    boolean isBalanced();

}
