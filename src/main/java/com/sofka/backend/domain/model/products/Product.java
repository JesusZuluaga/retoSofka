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
public class Product implements Serializable {

    private String id;
    private String name;
    private Integer inInventory;
    private Boolean enabled;
    private Integer min;
    private Integer max;


    public boolean maxInventory(int quantity){
        return quantity > max;
    }

    public boolean minInventory(int quantity){
        return quantity < min;
    }

    public boolean exceededLimit(int quantity){
        return quantity > inInventory;
    }
}
