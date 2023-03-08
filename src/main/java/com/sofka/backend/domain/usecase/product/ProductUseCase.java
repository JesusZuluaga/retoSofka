package com.sofka.backend.domain.usecase.product;

import com.sofka.backend.domain.generic.BusinessException;
import com.sofka.backend.domain.model.products.Product;
import com.sofka.backend.domain.model.products.gateway.ProductRepository;
import com.sofka.backend.domain.model.paging.Page;
import com.sofka.backend.domain.usecase.product.validator.ValidateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class ProductUseCase extends ValidateProduct {

    @Autowired
    private ProductRepository repository;


    public Mono<Page<Product>> findAllProduct(Integer size, Integer page, String sort) {
        return repository.getAllProducts(size, page, sort);
    }

    public Mono<Product> saveProduct(Product request) {
        if(validateProduct(request)){
            return repository.saveProduct(request);
        }
        return Mono.error(new BusinessException("product with incositent data"));
    }

    public Mono<Void> deleteProduct(String productId) {
        if(isNotBlank(productId)){
            return repository.deleteProduct(productId);
        }
         return Mono.error(new BusinessException("ProductId must not be null"));
    }
}
