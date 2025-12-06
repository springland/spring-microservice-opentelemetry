package com.springland365.tracing.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String sku;

}