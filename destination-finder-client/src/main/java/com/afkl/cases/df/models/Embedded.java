package com.afkl.cases.df.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Embedded {
    private List<Location> locations;
}
