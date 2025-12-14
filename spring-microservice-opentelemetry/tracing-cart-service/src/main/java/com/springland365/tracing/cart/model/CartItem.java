package com.springland365.tracing.cart.model;

import com.springland365.tracing.dto.product.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItem {

    Product product ;

    int     qty ;

    BigDecimal price = new BigDecimal(0.0);
}
