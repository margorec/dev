package com.marcingorecki.ChartAnalysis.controller;

import com.marcingorecki.ChartAnalysis.service.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
public class WelcomeController {

    @Autowired
    public WelcomeController(Parser downloader) {
        this.parser = downloader;
    }

    private final Parser parser;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        parser.parseToTimeseries();
        model.put("message", "check console");
        return "welcome";
    }

}
