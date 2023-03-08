package com.sofka.backend.infraestructure.buy.data;

import com.sofka.backend.domain.model.buys.IdType;
import com.sofka.backend.domain.model.products.ProductBuy;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
public class BuyData {

    @Id
    private String id;
    private Date date;
    private IdType idType;
    private String idClient;
    private String clientName;
    private List<ProductBuy> products;
}
