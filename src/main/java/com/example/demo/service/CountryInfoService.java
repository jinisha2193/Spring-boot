package com.example.demo.service;

import com.example.demo.model.CountryInfo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CountryInfoService {
    RestTemplate restTemplate = new RestTemplate();
    String resourceURl = "https://restcountries.eu/rest/v2";

    @Async
    public CompletableFuture<List<CountryInfo>> getCountryInfoByLanguage(String language) {
        String languageUrl = resourceURl + "/lang/" + language;
        CountryInfo[] response = restTemplate.getForObject(languageUrl, CountryInfo[].class);
        return CompletableFuture.completedFuture(Arrays.asList(response));
    }

    public CompletableFuture<List<CountryInfo>> getCountryInfoByRegion(String region) {
        String regionUrl = resourceURl + "/region/" + region;
        CountryInfo[] response = restTemplate.getForObject(regionUrl, CountryInfo[].class);
        return CompletableFuture.completedFuture(Arrays.asList(response));
    }
}
