package com.gorecki.controller;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OperationTest {

    private static final int TEST_VALUE = 41;

    @Test
    public void creation() {
        Operation subject = new Operation(OperationType.ADD, TEST_VALUE);
        assertThat(subject.getOperationType(), is(OperationType.ADD));
        assertThat(subject.getValue(), is(TEST_VALUE));
    }

}