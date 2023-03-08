package com.sofka.backend.infraestructure.buy.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyDataRepository extends ReactiveCrudRepository<BuyData, String> {
}
