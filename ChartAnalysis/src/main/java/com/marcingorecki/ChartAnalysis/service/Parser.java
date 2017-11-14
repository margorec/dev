package com.marcingorecki.ChartAnalysis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Parser {

    private final String NEW_LINE = "\\r?\\n";

    private final Downloader downloader;

    @Autowired
    public Parser(Downloader downloader) {
        this.downloader = downloader;
    }

    public void parseToTimeseries() {
        String data = downloader.download();
        String[] lines = data.split(NEW_LINE);
        Arrays.stream(lines).forEach(System.out::println);


    }
}
