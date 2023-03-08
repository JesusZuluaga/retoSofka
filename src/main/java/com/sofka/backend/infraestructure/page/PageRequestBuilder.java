package com.sofka.backend.infraestructure.page;

import com.sofka.backend.domain.generic.StringOperations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class PageRequestBuilder implements StringOperations {
    public PageRequest getPageRequest(Integer pageSize, Integer pageNumber, String sortingCriteria) {
        return PageRequest.of(pageNumber == null ? 0 : pageNumber, pageSize == null ? 10 : pageSize,
                getSort(sortingCriteria));
    }

    public Sort getSort(String sortingCriteria) {
        List<String> sortingFields = isNotBlank(sortingCriteria) ?
                Arrays.asList(sortingCriteria.split(",")) :
                Collections.emptyList();

        List<Sort.Order> sortingOrders = sortingFields.stream()
                .map(this::getFieldOrder)
                .collect(Collectors.toList());

        return sortingOrders.isEmpty() ? Sort.unsorted() : Sort.by(sortingOrders);
    }

    private Sort.Order getFieldOrder(String value) {
        value = value.replace(' ', '+');
        if (value.startsWith("-")) {
            return new Sort.Order(Sort.Direction.DESC, substringAfter(value, "-"));
        } else if (value.startsWith("+")) {
            return new Sort.Order(Sort.Direction.ASC, substringAfter(value, "+"));
        }

        return Sort.Order.by(value);
    }
}
