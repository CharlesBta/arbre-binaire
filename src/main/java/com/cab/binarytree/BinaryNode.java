package com.cab.binarytree;

import lombok.Data;

@Data
class BinaryNode<E extends Comparable<E>> {
    private static final String GAP_START_LEFT = "╰";
    private static final String GAP_START_RIGHT = "╭";
    private static final String GAP = " ";
    private static final String GAP_END = "─";

    private E data;
    private int balanceFactorValue;
    private BinaryNode<E> left = null;
    private BinaryNode<E> right = null;

    public BinaryNode(E data) {
        this(data, null, null);
    }

    public BinaryNode(final E data, final BinaryNode<E> left, final BinaryNode<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.balanceFactorValue = 0;
    }

    static <E extends Comparable<E>> int getHeight(BinaryNode<E> child) {
        if (child == null) return 0;
        return child.getHeight();
    }

    public static <E extends Comparable<E>> boolean isAVL(final BinaryNode<E> node) {
        if (node == null) return true;
        return node.isAVL();
    }

    private boolean isAVL() {
        if (Math.abs(this.getBalanceFactor()) > 1) return false;
        return BinaryNode.isAVL(this.left) && BinaryNode.isAVL(this.right);
    }

    void add(final E value) {
        switch (value.compareTo(this.data)) {
            case 1 -> this.right = this.add(this.right, value);
            case -1 -> this.left = this.add(this.left, value);
        }
    }

    private BinaryNode<E> add(final BinaryNode<E> child, final E value) {
        if (child == null) return new BinaryNode<>(value);
        child.add(value);
        child.calculateBalanceFactor();
        return child;
    }

    @Override
    public String toString() {
        return "BinaryNode{" + "data=" + data + ", left=" + left + ", right=" + right + '}';
    }

    public String toPrettyString() {
        return "[" + this.data +
                (this.left == null && this.right == null ? "" : "(" +
                        (this.left == null ? "-" : this.left.toPrettyString()) + "," +
                        (this.right == null ? "-" : this.right.toPrettyString()) + ")") + "]";
    }

    public String toTreeString() {
        return this.toTreeString(0, true);
    }

    public String toTreeString(final int gap, final boolean isLeft) {
        StringBuilder stringBuilder = new StringBuilder();


        if (this.right != null) stringBuilder.append(this.right.toTreeString(gap + 1, false));
        if (gap >= 1) {
            stringBuilder.append(BinaryNode.GAP.repeat((gap - 1) * 2));
            stringBuilder.append(isLeft ? BinaryNode.GAP_START_LEFT : BinaryNode.GAP_START_RIGHT);
            stringBuilder.append(BinaryNode.GAP_END);
        }
        stringBuilder.append(this.data);
        stringBuilder.append("(").append(this.getBalanceFactor()).append(")");
        stringBuilder.append("\n");
        if (this.left != null) stringBuilder.append(this.left.toTreeString(gap + 1, true));
        return stringBuilder.toString();
    }

    public StringBuilder prefix(final StringBuilder sb) {
        sb.append(this.data).append("(").append(this.getBalanceFactor()).append(")").append(" ");
        if (this.left != null) this.left.prefix(sb);

        if (this.right != null) this.right.prefix(sb);

        return sb;
    }

    public StringBuilder infix(final StringBuilder sb) {
        if (this.right != null) this.right.infix(sb);

        sb.append(this.data).append(" ");

        if (this.left != null) this.left.infix(sb);

        return sb;
    }

    public StringBuilder postfix(final StringBuilder sb) {
        if (this.right != null) this.right.postfix(sb);

        if (this.left != null) this.left.postfix(sb);

        sb.append(this.data).append(" ");

        return sb;
    }

    public int getHeight() {
        return 1 + Math.max(BinaryNode.getHeight(this.left), BinaryNode.getHeight(this.right));
    }

    public int getBalanceFactor() {
        return BinaryNode.getHeight(this.left) - BinaryNode.getHeight(this.right);
    }

    private void calculateBalanceFactor() {
        this.balanceFactorValue = BinaryNode.getHeight(this.left) - BinaryNode.getHeight(this.right);
    }

    public void rotateRightLeftChild() {
        BinaryNode<E> pivot = this.left;
        BinaryNode<E> Child = BinaryNode.getLeft(pivot);
        BinaryNode<E> grandChild = BinaryNode.getRight(Child);

        this.left = Child;
        BinaryNode.setRight(Child, pivot);
        BinaryNode.setLeft(pivot, grandChild);
    }

    private static <E extends Comparable<E>> BinaryNode<E> getLeft(final BinaryNode<E> pivot) {
        return pivot == null ? null : pivot.left;
    }

    private static <E extends Comparable<E>> BinaryNode<E> getRight(final BinaryNode<E> node) {
        return node == null ? null : node.right;
    }

    private static <E extends Comparable<E>> void setRight(final BinaryNode<E> node, final BinaryNode<E> child) {
        if (node == null) throw new IllegalArgumentException("Node is null");
        node.right = child;
    }

    private static <E extends Comparable<E>> void setLeft(final BinaryNode<E> node, final BinaryNode<E> child) {
        if (node == null) throw new IllegalArgumentException("Node is null");
        node.left = child;
    }

    public void rotateLeftLeftChild() {
        BinaryNode<E> pivot = this.left;
        BinaryNode<E> Child = BinaryNode.getRight(pivot);
        BinaryNode<E> grandChild = BinaryNode.getLeft(Child);

        this.left = Child;
        BinaryNode.setLeft(Child, pivot);
        BinaryNode.setRight(pivot, grandChild);
    }
}