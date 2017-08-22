package com.gorecki.controller;

import com.gorecki.service.TreeService;
import com.gorecki.tree.BSTTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TreeController {

    private static final String TREE_VIEW_NAME = "Tree";
    private static final String QUICK_TREE_VIEW_PATH = "/intQuickTree";

    @Autowired
    TreeService treeService;

    @GetMapping(QUICK_TREE_VIEW_PATH)
    public String tree(@RequestParam("nodes") final String nodes, Model model) {
        BSTTree<Integer> tree = treeService.createOf(processRequestParams(nodes));
        model.addAttribute("tree", tree.getJs());
        return TREE_VIEW_NAME;
    }

    List<Integer> processRequestParams(String params) {
        return Arrays.asList(params.split(",")).stream()
                .map(s->s.trim())
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

}

