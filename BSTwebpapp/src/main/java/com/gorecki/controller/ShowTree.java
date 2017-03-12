package com.gorecki.controller;

import com.gorecki.tree.BstTree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowTree {

    @RequestMapping("/tree")
    public String tree(Model model) {
        BstTree tree = new BstTree<Integer>();

        tree.add(10);
        tree.add(43);
        tree.add(46);
        tree.add(21);
        tree.add(3);
        tree.add(4);
        tree.add(2);

        model.addAttribute("tree", tree.getJs());
        return "Tree";
    }

}

