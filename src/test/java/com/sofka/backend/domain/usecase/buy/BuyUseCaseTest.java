package com.sofka.backend.domain.usecase.buy;

import com.sofka.backend.domain.model.buys.Buy;
import com.sofka.backend.domain.model.buys.IdType;
import com.sofka.backend.domain.model.buys.gateway.BuyRepository;
import com.sofka.backend.domain.model.products.Product;
import com.sofka.backend.domain.model.products.ProductBuy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
class BuyUseCaseTest {

    @InjectMocks
    private BuyUseCase useCase;

    @Mock
    private BuyRepository repository;

    @Test
    void saveBuyOk() {
        var expectProductId = "6406a5c2374812626d250326";
        var expectProduct = new Product()
                .toBuilder()
                .id("6406a5c2374812626d250326")
                .name("MOUSE LOGIGTED")
                .inInventory(500)
                .enabled(true)
                .min(2)
                .max(300)
                .build();

        var productBuy = new ProductBuy()
                .toBuilder()
                .idProduct("6406a5c2374812626d250326")
                .quantity(50)
                .build();

        var expectProductSave = new Product()
                .toBuilder()
                .id("6406a5c2374812626d250326")
                .name("MOUSE LOGIGTED")
                .inInventory(expectProduct.getInInventory() - productBuy.getQuantity())
                .enabled(true)
                .min(2)
                .max(300)
                .build();

        var listProductRequest = new ArrayList<ProductBuy>();
        listProductRequest.add(productBuy);

        var buyRequest = new Buy()
                .toBuilder()
                .idType(IdType.CC)
                .date(new Date())
                .clientName("DAVID ZULUAGA")
                .products(listProductRequest)
                .build();


        Mockito.when(repository.findProductById(expectProductId)).thenReturn(Mono.just(expectProduct));
        Mockito.when(repository.saveProduct(expectProductSave)).thenReturn(Mono.just(expectProductSave));
        Mockito.when(repository.saveBuy(buyRequest)).thenReturn(Mono.just(buyRequest));

        var result = useCase.saveBuy(buyRequest);

        StepVerifier.create(result)
                .expectNext(buyRequest)
                .expectComplete()
                .verify();
    }

    @Test
    void saveBuyError() {
        var buyRequest = new Buy()
                .toBuilder()
                .idType(IdType.CC)
                .date(new Date())
                .clientName("DAVID ZULUAGA")
                .products(null)
                .build();


        var result = useCase.saveBuy(buyRequest);

        StepVerifier.create(result)
                .expectErrorMessage("There are no products in your cart")
                .verify();
    }
}