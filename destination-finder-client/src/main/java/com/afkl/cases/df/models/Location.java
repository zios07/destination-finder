package com.afkl.cases.df.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class Location {

    private String code, name, description;
    private Coordinates coordinates;
    private Location parent;
    private Set<Location> children;

}
