package com.gorecki.controller;

import com.gorecki.tree.BstTree;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ShowTreeTest {
    @Test
    public void feedTree() {
        ShowTree subject = new ShowTree();
        BstTree<Integer> tree = subject.feedTree("10,32,12,43,5");

        assertThat(tree.getNodes().size(), is(5));
        assertThat(tree.getRoot().getValue(), is(10));
        assertThat(tree.getRoot().getLeftSon().getValue(), is(5));
    }
}
