package com.cirruslabs.store.orders;

import com.cirruslabs.store.products.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Product}
 */
@AllArgsConstructor
@Data
public class ProductDto implements Serializable {
    Long id;
    String name;
    String description;
    BigDecimal price;
    Byte categoryId;
}