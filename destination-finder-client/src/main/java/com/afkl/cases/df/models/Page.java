package com.afkl.cases.df.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Page {

    private int size;
    private int totalElements;
    private int totalPages;
    private int number;
}
