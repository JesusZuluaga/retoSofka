package com.sofka.backend.infraestructure.buy.Adapter;

import com.sofka.backend.domain.model.buys.Buy;
import com.sofka.backend.domain.model.buys.gateway.BuyRepository;
import com.sofka.backend.domain.model.paging.Page;
import com.sofka.backend.domain.model.products.Product;
import com.sofka.backend.infraestructure.buy.MapperBuy;
import com.sofka.backend.infraestructure.buy.data.BuyData;
import com.sofka.backend.infraestructure.buy.data.BuyDataRepository;
import com.sofka.backend.infraestructure.page.PageRequestBuilder;
import com.sofka.backend.infraestructure.product.MapperProduct;
import com.sofka.backend.infraestructure.product.data.ProductDataRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class BuyServiceAdapter implements BuyRepository {

    private final ProductDataRepository productDataRepository;
    private final BuyDataRepository repository;
    private final MapperBuy mapperBuy;
    private final MapperProduct mapperProduct;
    private final ReactiveMongoTemplate mongoTemplate;
    private final PageRequestBuilder pageRequestBuilder;

    public BuyServiceAdapter(ProductDataRepository productDataRepository, BuyDataRepository repository, MapperBuy mapperBuy, MapperProduct mapperProduct, ReactiveMongoTemplate mongoTemplate, PageRequestBuilder pageRequestBuilder) {
        this.productDataRepository = productDataRepository;
        this.repository = repository;
        this.mapperBuy = mapperBuy;
        this.mapperProduct = mapperProduct;
        this.mongoTemplate = mongoTemplate;
        this.pageRequestBuilder = pageRequestBuilder;
    }

    @Override
    public Mono<Product> findProductById(String productId) {
        return productDataRepository.findById(productId)
                .map(mapperProduct::toProduct);
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return productDataRepository.save(mapperProduct.toProductData(product))
                .map(mapperProduct::toProduct);
    }

    @Override
    public Mono<Buy> saveBuy(Buy buy) {
        return repository.save(mapperBuy.toBuyData(buy))
                .map(mapperBuy::toBuy);
    }

    @Override
    public Mono<Page<Buy>> findAll(Integer size, Integer page, String sort) {
        Pageable pageRequest = pageRequestBuilder.getPageRequest(size, page, sort);
        return Mono.zip(mongoTemplate.find(new Query().with(pageRequest), BuyData.class)
                        .map(mapperBuy::toBuy).collectList(),
                mongoTemplate.count(new Query(), BuyData.class), Page::new);
    }
}
