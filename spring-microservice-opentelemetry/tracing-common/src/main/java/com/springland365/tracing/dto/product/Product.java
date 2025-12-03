package com.springland365.tracing.dto.product;

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
