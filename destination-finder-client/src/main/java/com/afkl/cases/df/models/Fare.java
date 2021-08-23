package com.afkl.cases.df.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Fare {

    private double amount;
    private Currency currency;
    private String origin, destination;

}
