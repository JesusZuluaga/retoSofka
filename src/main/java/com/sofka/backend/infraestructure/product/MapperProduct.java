package com.sofka.backend.infraestructure.product;

import com.sofka.backend.domain.model.products.Product;
import com.sofka.backend.infraestructure.product.data.ProductData;
import org.springframework.stereotype.Component;


@Component
public class MapperProduct {

    public ProductData toProductData (Product product){
        ProductData productData = new ProductData();
        productData.setId(product.getId());
        productData.setName(product.getName().trim());
        productData.setInInventory(product.getInInventory());
        productData.setEnabled(product.getEnabled());
        productData.setMax(product.getMax());
        productData.setMin(product.getMin());
        return productData;
    }

    public Product toProduct (ProductData productData){
        Product product = new Product();
        product.setId(productData.getId());
        product.setName(productData.getName().trim());
        product.setInInventory(productData.getInInventory());
        product.setEnabled(productData.getEnabled());
        product.setMax(productData.getMax());
        product.setMin(productData.getMin());
        return product;
    }

}
