package com.sofka.backend.domain.model.buys.gateway;

import com.sofka.backend.domain.model.buys.Buy;
import com.sofka.backend.domain.model.paging.Page;
import com.sofka.backend.domain.model.products.Product;
import reactor.core.publisher.Mono;

public interface BuyRepository {

    Mono<Product> findProductById (String productId);
    Mono<Product> saveProduct (Product product);
    Mono<Buy> saveBuy (Buy buy);
    Mono<Page<Buy>> findAll (Integer size, Integer page, String sort);
}
