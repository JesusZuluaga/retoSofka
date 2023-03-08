package com.sofka.backend.infraestructure.product.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDataRepository extends ReactiveCrudRepository<ProductData, String> {
}
