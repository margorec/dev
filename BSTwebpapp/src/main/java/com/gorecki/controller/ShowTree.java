package com.gorecki.controller;

import com.gorecki.tree.BstTree;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowTree {

    @RequestMapping
    public String showSomething() {
        BstTree tree = new BstTree();

        tree.add(10);
        tree.add(43);
        tree.add(21);
        tree.add(1);
        tree.add(3);
        tree.add(2);

        return "<h3>Tree inorder printout<h3>" + tree.toString();
    }
}
