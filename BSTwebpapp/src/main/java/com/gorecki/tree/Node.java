package com.gorecki.tree;

public class Node<T extends Comparable> {
    private Node parent;
    private Node leftSon;
    private Node rightSon;
    private T value;

    public Node(Node parent, Node leftSon, Node rightSon, T value) {
        this.parent = parent;
        this.leftSon = leftSon;
        this.rightSon = rightSon;
        this.value = value;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setLeftSon(Node leftSon) {
        this.leftSon = leftSon;
    }

    public void setRightSon(Node rightSon) {
        this.rightSon = rightSon;
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeftSon() {
        return leftSon;
    }

    public Node getRightSon() {
        return rightSon;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
