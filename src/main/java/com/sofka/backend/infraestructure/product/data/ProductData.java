package com.sofka.backend.infraestructure.product.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ProductData {

    @Id
    private String id;
    private String name;
    private Integer inInventory;
    private Boolean enabled;
    private Integer min;
    private Integer max;
}
