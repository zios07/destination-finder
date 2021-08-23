package com.afkl.cases.df.services;

import com.afkl.cases.df.models.Statistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatisticsService {

    private final MetricsEndpoint metricsEndpoint;

    @Value("${statistics.status.2xx}")
    private List<Integer> _2xxStatus;
    @Value("${statistics.status.4xx}")
    private List<Integer> _4xxStatus;
    @Value("${statistics.status.5xx}")
    private List<Integer> _5xxStatus;
    @Value("${statistics.metric_name}")
    private String metricName;

    public Statistics getStatistics() {
        Statistics statistics = new Statistics();
        MetricsEndpoint.MetricResponse globalMetrics = metricsEndpoint.metric(metricName, null);
        if (globalMetrics != null) {
            globalMetrics.getMeasurements().forEach(measurement -> {
                switch (measurement.getStatistic()) {
                    case MAX:
                        statistics.setMaxResponseTimeMillis(measurement.getValue().floatValue());
                        break;
                    case TOTAL_TIME:
                        statistics.setTotalResponseTimeMillis(measurement.getValue().floatValue());
                        break;
                    case COUNT:
                        statistics.setRequestCount(measurement.getValue().intValue());
                        break;
                }
            });
            statistics.setAvgResponseTimeMillis(statistics.getTotalResponseTimeMillis() / statistics.getRequestCount());
        }

        List<String> _2xxTags = parseStatus(_2xxStatus);
        List<String> _4xxTags = parseStatus(_4xxStatus);
        List<String> _5xxTags = parseStatus(_5xxStatus);

        // 2xx metrics
        MetricsEndpoint.MetricResponse metrics2xx = metricsEndpoint.metric(metricName, _2xxTags);
        // 4xx metrics
        MetricsEndpoint.MetricResponse metrics4xx = metricsEndpoint.metric(metricName, _4xxTags);
        // 5xx metrics
        MetricsEndpoint.MetricResponse metrics5xx = metricsEndpoint.metric(metricName, _5xxTags);

        getRequestCount(statistics, metrics2xx, "2xx");
        getRequestCount(statistics, metrics4xx, "4xx");
        getRequestCount(statistics, metrics5xx, "5xx");

        return statistics;
    }

    private List<String> parseStatus(List<Integer> statusCodes) {
        return statusCodes.stream().map(status -> "status:".concat(status.toString())).collect(Collectors.toList());
    }

    private void getRequestCount(Statistics statistics, MetricsEndpoint.MetricResponse metrics, String tag) {
        if (metrics != null) {
            metrics.getMeasurements().forEach(measurement -> {
                switch (measurement.getStatistic()) {
                    case COUNT:
                        switch (tag) {
                            case "2xx":
                                statistics.setRequest200Count(measurement.getValue().intValue());
                                break;
                            case "4xx":
                                statistics.setRequest4XXCount(measurement.getValue().intValue());
                                break;
                            case "5xx":
                                statistics.setRequest5XXCount(measurement.getValue().intValue());
                                break;
                        }
                }
            });
        }
    }
}
