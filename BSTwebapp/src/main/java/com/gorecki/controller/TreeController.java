package com.gorecki.controller;

import com.gorecki.tree.BstTree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller
public class TreeController {

    private String TREE_VIEW = "Tree";

    @GetMapping("/tree")
    public String tree(@RequestParam(value = "numbers") final String numbers, Model model) {
        BstTree<Integer> tree = new BstTree<>();
        tree.addAll(processRequestParams(numbers));
        model.addAttribute("tree", tree.getJs());
        return TREE_VIEW;
    }

    public Collection<Integer> processRequestParams(String params) {
        return Arrays.asList(params.split(",")).stream()
                .map(s->s.trim())
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    @PostMapping("/tree")
    public String addToTree(@ModelAttribute Operation operation) {
        return TREE_VIEW;
    }
}

