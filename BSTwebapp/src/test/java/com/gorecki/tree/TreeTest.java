package com.gorecki.tree;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TreeTest {
    private Integer value1 = 10;
    private Integer value2 = 2;
    private Integer value3 = 30;
    private Integer value4 = 100;

    private BstTree createTestTree() {
        BstTree<Integer> tree = new BstTree();
        tree.add(value1);
        tree.add(value2);
        tree.add(value3);
        tree.add(value4);
        return tree;
    }

    @Test
    public void addRoot() {
        BstTree<Integer> tree = new BstTree();
        Integer value = 10;
        tree.add(value);
        assertThat(tree.contains(value), is(true));
        assertThat(tree.getRoot().getValue(), is(value));
    }

    @Test
    public void addLeafs() {
        BstTree<Integer> tree = createTestTree();
        assertThat(tree.getRoot().getValue(), is(value1));
        assertThat(tree.getRoot().getLeftSon().getValue(), is(value2));
        assertThat(tree.getRoot().getRightSon().getValue(), is(value3));
        assertThat(tree.getRoot().getRightSon().getRightSon().getValue(), is(value4));
    }

    @Test
    public void contains() {
        BstTree<Integer> tree = new BstTree();
        assertThat(tree.contains(Integer.MAX_VALUE), is(false));

        tree.add(value1);
        tree.add(value2);

        assertThat(tree.contains(value3), is(false));
        assertThat(tree.contains(value2), is(true));
        assertThat(tree.contains(value1), is(true));
    }

    @Test
    public void getMinimumNode() {
        BstTree<Integer> tree = createTestTree();
        assertThat(tree.getMinNode(tree.getRoot()).getValue(), is(value2));
    }

    @Test
    public void delete() {
        BstTree<Integer> tree = createTestTree();
        tree.delete(value1);
        tree.delete(value2);

        assertThat(tree.contains(value1), is(false));
        assertThat(tree.contains(value2), is(false));
        assertThat(tree.contains(value3), is(true));
    }

    @Test
    public void deleteRootOnly() {
        BstTree<Integer> tree = new BstTree();

        tree.add(value2);
        tree.delete(value2);

        assertThat(tree.contains(value2), is(false));
        assertThat(tree.contains(value2), is(false));
    }

    @Test
    public void deleteEmptyTree() {
        BstTree<Integer> tree = new BstTree();
        Node result = tree.delete(value2);
        assertThat(tree.contains(value2), is(false));
        assertThat(result, IsNull.nullValue());
    }

    @Test
    public void depth() {
        BstTree<Integer> tree = createTestTree();
        assertThat(tree.getDepth(tree.getRoot()), is(3));
    }

    @Test
    public void testToString() {
        BstTree<Integer> tree = createTestTree();
        System.out.println(tree.toString());
    }

    @Test
    public void getNodesList() {
        BstTree<Integer> tree = createTestTree();
        assertThat(tree.getNodes().size() , is(4));
    }

    @Test
    public void getNodes_visitPreOrder() {
        BstTree<Integer> tree = createTestTree();
        String subject = tree.getNodes().stream().map(n -> n.getValue().toString()).collect(joining(","));

        assertThat(subject, is("10,2,30,100"));
    }

    @Test
    public void visitInOrder() {
        BstTree<Integer> tree = createTestTree();
        List<Node> subject = new LinkedList<>();
        tree.visitInOrder(tree.getRoot(), subject);
        assertThat(subject.stream().map(n -> n.getValue().toString()).collect(joining(",")), is("2,10,30,100"));
    }

    @Test
    public void testJs() {
        BstTree<Integer> tree = new BstTree<>();
        Arrays.asList(32,12,15,11,45,47,46,76,49).stream().forEachOrdered(tree::add);

        String expectedString = "n_32 = {text:{name:32}}, n_12 = {parent:n_32, text:{name:12}}, n_11 = {parent:n_12, text:{name:11}}, n_15 = {parent:n_12, text:{name:15}}, n_45 = {parent:n_32, text:{name:45}}, n_47 = {parent:n_45, text:{name:47}}, n_46 = {parent:n_47, text:{name:46}}, n_76 = {parent:n_47, text:{name:76}}, n_49 = {parent:n_76, text:{name:49}}, treeConfig=[config, n_32, n_12, n_11, n_15, n_45, n_47, n_46, n_76, n_49];";
        assertThat(tree.getJs(), is(expectedString));

    }
}
