package com.sofka.backend.domain.model.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductBuy implements Serializable {
    private String idProduct;
    private Integer quantity;
}
