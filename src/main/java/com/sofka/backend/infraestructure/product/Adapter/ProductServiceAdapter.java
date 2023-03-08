package com.sofka.backend.infraestructure.product.Adapter;

import com.sofka.backend.domain.model.products.Product;
import com.sofka.backend.domain.model.products.gateway.ProductRepository;
import com.sofka.backend.domain.model.paging.Page;
import com.sofka.backend.infraestructure.product.data.ProductData;
import com.sofka.backend.infraestructure.product.data.ProductDataRepository;
import com.sofka.backend.infraestructure.page.PageRequestBuilder;
import com.sofka.backend.infraestructure.product.MapperProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceAdapter implements ProductRepository {

    private final ProductDataRepository repository;
    private final MapperProduct mapperProduct;
    private final ReactiveMongoTemplate mongoTemplate;
    private final PageRequestBuilder pageRequestBuilder;

    public ProductServiceAdapter(ProductDataRepository repository, MapperProduct mapperProduct, ReactiveMongoTemplate mongoTemplate, PageRequestBuilder pageRequestBuilder) {
        this.repository = repository;
        this.mapperProduct = mapperProduct;
        this.mongoTemplate = mongoTemplate;
        this.pageRequestBuilder = pageRequestBuilder;
    }

    @Override
    public Mono<Page<Product>> getAllProducts(Integer size, Integer page, String sort) {
        Pageable pageRequest = pageRequestBuilder.getPageRequest(size, page, sort);
        return Mono.zip(mongoTemplate.find(new Query().with(pageRequest), ProductData.class)
                        .map(mapperProduct::toProduct).collectList(),
                mongoTemplate.count(new Query(), ProductData.class), Page::new);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return repository.save(mapperProduct.toProductData(product))
                .map(mapperProduct::toProduct);
    }

    @Override
    public Mono<Void> deleteProduct(String productId) {
        return repository.deleteById(productId);
    }
}
