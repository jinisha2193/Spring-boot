package com.example.demo.controller;

import com.example.demo.model.CountryInfo;
import com.example.demo.service.CountryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RestController
public class CountryInfoController {

    @Autowired
    private CountryInfoService countryInfoService;

    @GetMapping("/frenchEuropeanCountries")
    public List<String> getAllEuropeanFrenchSpeakingCountries() throws Throwable {
        CompletableFuture<List<CountryInfo>> countriesByLanguage = countryInfoService.getCountryInfoByLanguage("fr");
        CompletableFuture<List<CountryInfo>> countriesByRegion = countryInfoService.getCountryInfoByRegion("europe");

        List<String> frenchSpeakingEuropeanCountries;

        try {
            frenchSpeakingEuropeanCountries   = new ArrayList<>(countriesByLanguage.get().stream().map(CountryInfo::getName).collect(Collectors.toList()));
            frenchSpeakingEuropeanCountries.retainAll(countriesByRegion.get().stream().map(CountryInfo::getName).collect(Collectors.toList()));

        } catch(Throwable e){
            throw e.getCause();
        }
        return frenchSpeakingEuropeanCountries;
    }
}
