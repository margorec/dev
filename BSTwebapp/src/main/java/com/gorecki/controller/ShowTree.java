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
        BstTree tree = new BstTree<Integer>();
        List<String> treeItems = Arrays.asList(numbers.split(","));
        for (String n : treeItems) {
            tree.add(Integer.valueOf(n));
        }

        model.addAttribute("tree", tree.getJs());
        return "Tree";
    }

}

