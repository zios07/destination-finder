package com.afkl.cases.df.controllers;

import com.afkl.cases.df.clients.ResourceServerClient;
import com.afkl.cases.df.models.FareDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("fares/{origin}/{destination}")
@RequiredArgsConstructor
public class FareController {

    private final ResourceServerClient resourceServerClient;

    @GetMapping
    public ResponseEntity<Mono<FareDetails>> calculateFares(@PathVariable("origin") String origin,
                                                            @PathVariable("destination") String destination,
                                                            @RequestParam(value = "currency", defaultValue = "EUR") String currency) {
        Mono<FareDetails> fareDetails = resourceServerClient.calculateFaresWithDetails(origin, destination, currency);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .body(fareDetails);
    }

}
