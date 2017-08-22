package com.gorecki.controller;

import com.gorecki.service.TreeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class TreeControllerTest {

    @Mock
    private TreeService treeService;

    @Test
    public void processRequestParams() throws Exception {
        // given
        String testString = " 5 ,4,  3,7,8,   33 ";
        TreeController subject = new TreeController();
        Collection<Integer> expected = new LinkedList<>();
        expected.addAll(Arrays.asList(5, 4, 3, 7, 8, 33));

        // when
        Collection output = subject.processRequestParams(testString);

        // then
        assertThat(output, is(expected));
    }


}
