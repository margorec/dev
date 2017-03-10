package com.gorecki;

import com.gorecki.controller.ShowTree;
import com.gorecki.tree.BstTree;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ShowTreeTest {
    @Test
    public void testView() {
        ShowTree subject = new ShowTree();
        assertThat(subject.showSomething(), is("<h3>Tree inorder printout<h3>1 2 3 10 21 43 "));
    }
}
