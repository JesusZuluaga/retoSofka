package com.sofka.backend.domain.model.products.gateway;

import com.sofka.backend.domain.model.paging.Page;
import com.sofka.backend.domain.model.products.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Page<Product>> getAllProducts(Integer size, Integer page, String sort);
    Mono<Product> saveProduct(Product product);
    Mono<Void> deleteProduct(String productId);
}
