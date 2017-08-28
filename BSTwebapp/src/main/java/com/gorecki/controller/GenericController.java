package com.gorecki.controller;

import com.gorecki.service.TreeService;
import com.gorecki.tree.BSTTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class GenericController {

    private static final Logger LOG = LoggerFactory.getLogger(GenericController.class);
    private static final String TREE_SESSION_OBJECT_NAME = "Tree";

    @Autowired
    HttpSession httpSession;

    @Autowired
    TreeService treeService;

    BSTTree<Integer> getCurrentTree() {
        Optional<BSTTree> sessionTree = Optional.ofNullable(getTreeFromActiveSession());
        if (sessionTree.isPresent()) {
            return sessionTree.get();
        }
        BSTTree newTree = treeService.createOf(Collections.EMPTY_LIST);
        writeTreeToActiveSession(newTree);
        return newTree;
    }

    BSTTree<Integer> getTreeFromActiveSession() {
        Optional<BSTTree> tree = Optional.ofNullable((BSTTree<Integer>)httpSession.getAttribute(TREE_SESSION_OBJECT_NAME));
        LOG.info("Getting tree from session : {}, {}", httpSession.getId(), tree.isPresent());
        return (BSTTree<Integer>)httpSession.getAttribute(TREE_SESSION_OBJECT_NAME);
    }

    void writeTreeToActiveSession(BSTTree tree) {
        LOG.info("Writing tree to session : {}", httpSession.getId());
        httpSession.setAttribute(TREE_SESSION_OBJECT_NAME, tree);
    }

    List<Integer> processRequestParams(String params) {
        return Arrays.asList(params.split(",")).stream()
                .map(s->s.trim())
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }


}
