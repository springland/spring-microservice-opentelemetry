package com.springland365.tracing.catalog.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Catalog {

    protected Long id ;

    protected String name ;

    protected String description ;

}
