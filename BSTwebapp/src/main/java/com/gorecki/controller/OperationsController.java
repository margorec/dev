package com.gorecki.controller;

import com.gorecki.tree.BSTTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
public class OperationsController extends GenericController{
    private static final Logger LOG = LoggerFactory.getLogger(OperationsController.class);

    @PostMapping("/add")
    public void addNode(@RequestParam("newNode") final String node) {
        BSTTree<Integer> tree = getCurrentTree();
        tree.add(Integer.valueOf(node));
        writeTreeToActiveSession(tree);
    }

    BSTTree<Integer> getCurrentTree() {
        LOG.info("Gettting tree for session: {}", httpSession.getId());
        Optional<BSTTree> currentTree = Optional.ofNullable(getTreeFromActiveSession());
        return currentTree.orElse(treeService.createOf(Collections.EMPTY_LIST));
    }

}
