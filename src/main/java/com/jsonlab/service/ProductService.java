package com.jsonlab.service;

import com.jsonlab.model.dto.ProductNameAndPriceDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


public interface ProductService {
    void seedProducts() throws IOException;

    List<ProductNameAndPriceDTO> findAllProductsInRangeOrderByPriceAsc(BigDecimal lower, BigDecimal upper);
}
