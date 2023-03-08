package com.sofka.backend.domain.model.buys;

import com.sofka.backend.domain.model.products.ProductBuy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Buy implements Serializable {

    private String id;
    private Date date;
    private IdType idType;
    private String idClient;
    private String clientName;
    private List<ProductBuy> products;

}
