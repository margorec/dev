package com.gorecki.controller;

import com.gorecki.tree.BstTree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class ShowTree {

    @RequestMapping("/tree")
    public String tree(@RequestParam(value = "numbers") final String numbers, Model model) {
        BstTree<Integer> tree = feedTree(numbers);
        model.addAttribute("tree", tree.getJs());
        return "Tree";
    }

    BstTree<Integer> feedTree(final String numbers) {
        BstTree tree = new BstTree<Integer>();
        List<String> toAdd = Arrays.asList(numbers.split(","));
        toAdd.stream().forEachOrdered(n->tree.add(Integer.valueOf(n)));
        return tree;
    }


}

