package com.afkl.cases.df.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FareDetails {

    private double amount;
    private Currency currency;
    private Location origin;
    private Location destination;

}
