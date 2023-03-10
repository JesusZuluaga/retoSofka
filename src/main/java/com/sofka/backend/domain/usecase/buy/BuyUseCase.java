package com.sofka.backend.domain.usecase.buy;

import com.sofka.backend.domain.generic.BusinessException;
import com.sofka.backend.domain.model.buys.Buy;
import com.sofka.backend.domain.model.buys.gateway.BuyRepository;
import com.sofka.backend.domain.model.paging.Page;
import com.sofka.backend.domain.usecase.buy.validator.ValidatorBuy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class BuyUseCase extends ValidatorBuy {

    @Autowired
    private BuyRepository repository;

    public Mono<Buy> saveBuy(Buy buy) {
        if (buy != null && buy.getProducts() != null && !buy.getProducts().isEmpty()) {
            return Flux.fromIterable(buy.getProducts()).next().flatMap(productBuy ->
                    repository.findProductById(productBuy.getIdProduct())
                            .flatMap(product -> isValidBuy(product, productBuy))
                            .flatMap(repository::saveProduct)
                            .then(repository.saveBuy(buy)));
        }
        return Mono.error(new BusinessException("There are no products in your cart"));
    }

    public Mono<Page<Buy>> findAllBuys(Integer size, Integer page, String sort) {
        try {
            return repository.findAll(size, page, sort);
        } catch (BusinessException e) {
            return Mono.error(new BusinessException("There are no products in your cart"));
        }

    }
}
