package com.marcingorecki.ChartAnalysis.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Downloader {

    private static final Logger LOG = LoggerFactory.getLogger(Downloader.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${data.stockSymbols}")
    private List<String> assetSymbols;

    private String ASSET_DATA_URL = "https://stooq.pl/q/d/l/?s=";

    public List<String> download() {
        String symbol = "PLY";
        List<String> result = new LinkedList<>();
        Optional<String> companyRawData = downloadSinglePricelist(symbol);

        if (companyRawData.isPresent()) {
            result.add(companyRawData.get());
        }

        return result;

        /*
        return assetSymbols
                .stream()
                .map(symbol -> downloadSinglePricelist(symbol))
                .collect(Collectors.toList());*/
    }

    private Optional<String> downloadSinglePricelist(String assetSymbol) {
        String fullDataUrl = ASSET_DATA_URL + assetSymbol;
        LOG.info("Fetching data from {}", fullDataUrl);
        ResponseEntity<String> response = restTemplate.getForEntity(fullDataUrl, String.class);
        LOG.info("Response  {}", response.toString());
        if (response == null
                || !response.hasBody()
                || !response.getStatusCode().equals(HttpStatus.OK)) {
            LOG.warn("Could not fetch stock data for symbol : [{}], " +
                    "response was [{}]", assetSymbol, response);
            return Optional.empty();
        }
        return Optional.of(response.getBody());

    }


}
