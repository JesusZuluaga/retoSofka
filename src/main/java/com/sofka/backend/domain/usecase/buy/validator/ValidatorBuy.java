package com.sofka.backend.domain.usecase.buy.validator;

import com.sofka.backend.domain.generic.BusinessException;
import com.sofka.backend.domain.model.products.Product;
import com.sofka.backend.domain.model.products.ProductBuy;
import reactor.core.publisher.Mono;

public class ValidatorBuy {

    public Mono<Product> isValidBuy(Product product, ProductBuy productBuy) {
        if (product != null) {
            if (product.exceededLimit(productBuy.getQuantity())) {
                return Mono.error(new BusinessException("The stock limit of the product is exceeded, the present stock is: " + product.getInInventory()));
            }
            if (product.maxInventory(productBuy.getQuantity())) {
                return Mono.error(new BusinessException("Exceeds the maximum number of products to carry: " + product.getMax()));
            }
            if (product.minInventory(productBuy.getQuantity())) {
                return Mono.error(new BusinessException("Exceeds the minimum number of products to be carried: " + product.getMin()));
            }
            var newProduct = product.toBuilder()
                    .inInventory(product.getInInventory() - productBuy.getQuantity())
                    .build();
            if (newProduct.getInInventory() <= 0 && newProduct.getInInventory() < newProduct.getMin()) {
                newProduct.toBuilder()
                        .enabled(false)
                        .build();
            }
            return Mono.just(newProduct);
        }
        return Mono.error(new BusinessException("no product"));
    }

}
