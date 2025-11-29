package com.springland365.tracing.product.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    Long id ;
    String name ;
    String description ;
}
