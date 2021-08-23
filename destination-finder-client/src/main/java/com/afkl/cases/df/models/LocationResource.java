package com.afkl.cases.df.models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Temp class to deal with hateoas response entities
 */
@Data
@NoArgsConstructor
public class LocationResource {
    private Embedded _embedded;
    private Page page;
}
