package com.cirruslabs.store.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemRequest {
    @NotNull(message = "Quantity must be provided")
    @Min(value = 1, message = "Quantity must be larger than 1")
    @Max(value = 100, message = "Quantity must be smaller than 100")
    private Integer quantity;
}
