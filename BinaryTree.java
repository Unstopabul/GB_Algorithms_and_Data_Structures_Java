package ru.gb.hw6;

import java.util.*;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node root;

    @Override
    public void add(T item) {
        Objects.requireNonNull(item);
        root = appendNode(root, item);
    }

    private Node appendNode(Node current, T value) {
        if (current == null) {
            return new Node(value);
        }

        int compare = value.compareTo(current.value);
        if (compare < 0) {
            current.left = appendNode(current.left, value);
        } else if (compare > 0) {
            current.right = appendNode(current.right, value);
        }

        return current;
    }

    @Override
    public boolean isBalanced() {
        // TODO: 29.09.2022 Проверить, является ли дерево сбалансированным.
        // Дерево является сбалансированным, если глубины от любого узла до любых двух листьев
        // отличаются не более, чем на 1
        return isBalanced(root).isBalanced;
    }

    private BalancedReturn isBalanced(Node node) {
        if (node == null)
            return new BalancedReturn(true, 0);
        else if (node.left == null && node.right == null)
            return new BalancedReturn(true, 1);

        BalancedReturn leftRet = isBalanced(node.left);
        if (!leftRet.isBalanced)    // дальше нет необходимости проверять
            return new BalancedReturn(false, 0);
        BalancedReturn rightRet = isBalanced(node.right);

        int maxHeight = Math.max(leftRet.height, rightRet.height);
        int heightDiff = Math.abs(leftRet.height - rightRet.height);
        boolean isBalanced = (heightDiff <= 1 && leftRet.isBalanced && rightRet.isBalanced);

        return new BalancedReturn(isBalanced, maxHeight + 1);
    }

    private class BalancedReturn{
        public boolean isBalanced;
        public int height;

        public BalancedReturn(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
}
