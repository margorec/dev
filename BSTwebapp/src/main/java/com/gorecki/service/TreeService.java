package com.gorecki.service;

import com.gorecki.controller.TreeController;
import com.gorecki.tree.BSTTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TreeService {

    private static final Logger LOG = LoggerFactory.getLogger(TreeController.class);

    public BSTTree<Integer> createOf(List<Integer> integers) {
        LOG.info("Creating new tree of : {}", integers.toString());
        BSTTree<Integer> tree = new BSTTree<>();
        tree.addAll(integers);
        return tree;
    }
}
