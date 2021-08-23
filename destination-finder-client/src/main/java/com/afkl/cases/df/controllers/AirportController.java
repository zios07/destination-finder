package com.afkl.cases.df.controllers;


import com.afkl.cases.df.clients.ResourceServerClient;
import com.afkl.cases.df.models.LocationResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "airports")
@RequiredArgsConstructor
public class AirportController {

    private final ResourceServerClient resourceServerClient;

    @GetMapping
    public Mono<LocationResource> searchAirportsByTerm(@RequestParam(defaultValue = "en") String lang,
                                                       @RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer size,
                                                       @RequestParam(required = false) String term) {
        return resourceServerClient.searchAirports(lang, term, page, size);
    }

}
