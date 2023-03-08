package com.sofka.backend.domain.usecase.product.validator;

import com.sofka.backend.domain.generic.StringOperations;
import com.sofka.backend.domain.model.products.Product;

public class ValidateProduct implements StringOperations {

    public Boolean validateProduct(Product product){
        if (product != null){
            if(!isNotBlank(product.getName())){
                return Boolean.FALSE;
            }
            if (!isValid(product.getMin()) ){
                return Boolean.FALSE;
            }
            if (!isValid(product.getMax())){
                return Boolean.FALSE;
            }
            if (!isValid(product.getInInventory())){
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private boolean isValid(Object object) {
        return object != null;
    }

}
