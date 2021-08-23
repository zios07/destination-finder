package com.afkl.cases.df.controllers;

import com.afkl.cases.df.models.Statistics;
import com.afkl.cases.df.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public Statistics getMetrics() {
        return statisticsService.getStatistics();
    }

}
