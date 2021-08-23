package com.afkl.cases.df.clients;

import com.afkl.cases.df.models.Fare;
import com.afkl.cases.df.models.FareDetails;
import com.afkl.cases.df.models.Location;
import com.afkl.cases.df.models.LocationResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Log4j2
public class ResourceServerClient {

    private final WebClient webClient;

    /**
     * Gets a detailed fares calculation by combining both requests for fares and for airport details
     *
     * @param origin
     * @param destination
     * @param currency
     * @return
     */
    public Mono<FareDetails> calculateFaresWithDetails(String origin, String destination, String currency) {

        return Mono.zip(calculateFares(origin, destination, currency), getAirportDetails(origin), getAirportDetails(destination))
                .flatMap(data -> Mono.just(handleResponses(data)));
    }


    /**
     * Perform a search by term and lang request against the resource server
     *
     * @param lang
     * @param term
     * @param page
     * @param size
     * @return
     */
    public Mono<LocationResource> searchAirports(String lang, String term, Integer page, Integer size) {
        return webClient.get()
                .uri(uriBuilder -> {
                    UriBuilder builder = uriBuilder
                            .path("/airports")
                            .queryParam("lang", lang)
                            .queryParam("term", term);
                    if (page != null && page != null) {
                        builder.queryParam("page", page)
                                .queryParam("size", size);
                    }
                    return builder.build();
                })
                .retrieve()
                .bodyToMono(LocationResource.class)
                .onErrorReturn(new LocationResource());
    }

    /**
     * Send a calculate fares request to the resource server for the given origin, destination and currency
     *
     * @param origin
     * @param destination
     * @param currency
     * @return
     */
    private Mono<Fare> calculateFares(String origin, String destination, String currency) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/fares/{origin}/{destination}")
                        .queryParam("currency", currency)
                        .build(origin, destination))
                .retrieve()
                .bodyToMono(Fare.class);
    }

    /**
     * Get airport details for the given airport code from the resource server
     *
     * @param code
     * @return
     */
    private Mono<Location> getAirportDetails(String code) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/airports/{code}")
                        .build(code))
                .retrieve()
                .bodyToMono(Location.class);
    }

    private FareDetails handleResponses(reactor.util.function.Tuple3<Fare, Location, Location> data) {
        Fare fare = data.getT1();
        Location originAirport = data.getT2();
        Location destinationAirport = data.getT3();
        FareDetails fareDetails = new FareDetails();
        fareDetails.setAmount(fare.getAmount());
        fareDetails.setCurrency(fare.getCurrency());
        fareDetails.setOrigin(originAirport);
        fareDetails.setDestination(destinationAirport);
        return fareDetails;
    }
}
