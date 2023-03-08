package com.sofka.backend.domain.usecase.product;

import com.sofka.backend.domain.model.paging.Page;
import com.sofka.backend.domain.model.products.Product;
import com.sofka.backend.domain.model.products.gateway.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {

    @InjectMocks
    private ProductUseCase useCase;

    @Mock
    private ProductRepository repository;


    @Test
    void findAllProduct() {
        var size = 10;
        var page = 1;

        var product1 = new Product()
                .toBuilder()
                .id("6406a5c2374812626d250326")
                .name("MOUSE LOGIGTED")
                .inInventory(50)
                .enabled(true)
                .min(2)
                .max(300)
                .build();

        var product2 = new Product()
                .toBuilder()
                .id("6407549d7eeaf74ce2738768")
                .name("MOUSE HP")
                .inInventory(500)
                .enabled(true)
                .min(10)
                .max(600)
                .build();

        var expectListProduc = new ArrayList<Product>();
        expectListProduc.add(product1);
        expectListProduc.add(product2);

        var pagination = new Page<Product>();
        pagination.setContent(expectListProduc);
        pagination.setTotalElements(expectListProduc.stream().count());

       Mockito.when(repository.getAllProducts(size, page, null)).thenReturn(Mono.just(pagination));

       var result = useCase.findAllProduct(size, page, null);

        StepVerifier.create(result)
                .expectNext(pagination)
                .expectComplete()
                .verify();

    }

    @Test
    void saveProductOk() {
        var productRequest = new Product()
                .toBuilder()
                .id("6406a5c2374812626d250326")
                .name("MOUSE LOGIGTED")
                .inInventory(50)
                .enabled(true)
                .min(2)
                .max(300)
                .build();

        var expectProduct = new Product()
                .toBuilder()
                .id("6406a5c2374812626d250326")
                .name("MOUSE LOGIGTED")
                .inInventory(50)
                .enabled(true)
                .min(2)
                .max(300)
                .build();

        Mockito.when(repository.saveProduct(productRequest)).thenReturn(Mono.just(expectProduct));

        var result = useCase.saveProduct(productRequest);

        StepVerifier.create(result)
                .expectNext(expectProduct)
                .expectComplete()
                .verify();
    }

    @Test
    void saveProductError() {
        var productRequest = new Product()
                .toBuilder()
                .inInventory(50)
                .enabled(true)
                .min(2)
                .max(300)
                .build();

        var result = useCase.saveProduct(productRequest);

        StepVerifier.create(result)
                .expectErrorMessage("Product must not be null")
                .verify();
    }

    @Test
    void deleteProductOk() {
        var productId = "6406a5c2374812626d250326";

        Mockito.when(repository.deleteProduct(productId)).thenReturn(Mono.empty());

        var result = useCase.deleteProduct(productId);

        StepVerifier.create(result)
                .expectNext()
                .expectComplete()
                .verify();
    }

    @Test
    void deleteProductError() {
        var productId = "";

        var result = useCase.deleteProduct(productId);

        StepVerifier.create(result)
                .expectErrorMessage("ProductId must not be null")
                .verify();
    }
}