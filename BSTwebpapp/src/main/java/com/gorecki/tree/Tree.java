package com.gorecki.tree;

public interface Tree<T> {
    Node add(T value);
    Node delete(T value);
    boolean contains(T value);
}
