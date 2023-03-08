package com.sofka.backend.infraestructure.buy;

import com.sofka.backend.domain.model.buys.Buy;
import com.sofka.backend.infraestructure.buy.data.BuyData;
import org.springframework.stereotype.Component;

@Component
public class MapperBuy {

    public BuyData toBuyData (Buy buy){
        BuyData buyData =  new BuyData();
        buyData.setId(buy.getId());
        buyData.setClientName(buy.getClientName().trim());
        buyData.setIdType(buy.getIdType());
        buyData.setIdClient(buy.getIdClient());
        buyData.setDate(buy.getDate());
        buyData.setProducts(buy.getProducts().size() != 0 ? buy.getProducts() : null);
        return buyData;
    }

    public Buy toBuy (BuyData buyData){
        Buy buy =  new Buy();
        buy.setId(buyData.getId());
        buy.setClientName(buyData.getClientName().trim());
        buy.setIdType(buyData.getIdType());
        buy.setIdClient(buyData.getIdClient());
        buy.setDate(buyData.getDate());
        buy.setProducts(buyData.getProducts().size() != 0 ? buyData.getProducts() : null);
        return buy;
    }

}
