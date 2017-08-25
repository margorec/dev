package com.gorecki.controller;

import com.gorecki.service.TreeService;
import com.gorecki.tree.BSTTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class TreeController extends GenericController{

    private static final Logger LOG = LoggerFactory.getLogger(TreeController.class);
    private static final String TREE_VIEW_NAME = "Tree";
    private static final String QUICK_TREE_VIEW_PATH = "/intQuickTree";

    @GetMapping(QUICK_TREE_VIEW_PATH)
    public String tree(@RequestParam("nodes") final String nodes, Model model) {
        BSTTree<Integer> tree = treeService.createOf(processRequestParams(nodes));
        model.addAttribute("tree", tree.getJs());
        return TREE_VIEW_NAME;
    }

    @GetMapping("/tree")
    public String getCurrentTree(Model model) {
        BSTTree<Integer> tree = getCurrentTree();
        model.addAttribute("tree", tree.getJs());
        return TREE_VIEW_NAME;
    }
}

