package com.sofka.backend.api;

import com.sofka.backend.domain.model.products.Product;
import com.sofka.backend.domain.usecase.product.ProductUseCase;
import com.sofka.backend.domain.model.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductUseCase useCase;

    @GetMapping("/products")
    public Mono<Page<Product>> findAllProduct(@RequestParam(required = false) Integer size,
                                               @RequestParam(required = false) Integer page,
                                               @RequestParam(required = false) String sort) {

        return useCase.findAllProduct(size, page, sort);
    }

    @PostMapping("/saveProduct")
    public Mono<Product> saveProduct(@RequestBody Product request) {
        return useCase.saveProduct(request);
    }

    @PutMapping("/saveProduct")
    public Mono<Product> editProduct(@RequestBody Product request) {
        return useCase.saveProduct(request);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public Mono<Void> saveProduct(@PathVariable String productId) {
        return useCase.deleteProduct(productId);
    }
    
}
