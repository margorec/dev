package com.gorecki.tree;

import org.junit.Test;
import org.mockito.Mock;
import com.gorecki.tree.Node;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class NodeTest {
    @Mock
    Node<Integer> parent, left, right;

    @Test
    public void testNodeValue() {
        Node<Integer> testNode = new Node<>(parent, left, right, Integer.MAX_VALUE);
        assertThat(testNode.getValue() instanceof Integer, is(true));
    }

    @Test
    public void serializeToJs() throws Exception {
        String expected = "n_foo = {text:{name:foo}}";
        Node<String> subject = new Node<>(parent, left, right, "foo");
        assertThat(subject.serializeToJs(), is(expected));
    }

    @Test
    public void jsName() throws Exception {
        Node<String> subject = new Node<>(parent, left, right, "foo");
        assertThat(subject.jsName(), is("n_foo"));
    }



}
