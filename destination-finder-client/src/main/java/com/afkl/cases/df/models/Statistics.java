package com.afkl.cases.df.models;

import lombok.Data;

@Data
public class Statistics {

    private int requestCount;
    private int request200Count;
    private int request4XXCount;
    private int request5XXCount;
    private float totalResponseTimeMillis;
    private float avgResponseTimeMillis;
    private float maxResponseTimeMillis;

}
