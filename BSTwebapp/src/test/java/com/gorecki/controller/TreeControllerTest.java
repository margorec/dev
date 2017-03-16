package com.gorecki.controller;

import com.gorecki.tree.BstTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TreeControllerTest {

    @Mock
    Model model;

    @Test
    public void tree() throws Exception {
        TreeController subject = new TreeController();
        subject.tree("1,2,3", model);
        verify(model).addAttribute(anyString(), anyString());
    }

    @Test
    public void processRequestParams() throws Exception {
        String testString = " 5 ,4,  3,7,8,   33 ";
        TreeController subject = new TreeController();
        Collection output = subject.processRequestParams(testString);

        Collection<Integer> expected = new LinkedList<>();
        expected.addAll(Arrays.asList(5, 4, 3, 7, 8, 33));
        assertThat(output, is(expected));
    }

    @Test
    public void addToTree() throws Exception {
        TreeController subject = new TreeController();
        Operation operation = new Operation(OperationType.ADD, 13);
        subject.addToTree(operation);

    }



}
