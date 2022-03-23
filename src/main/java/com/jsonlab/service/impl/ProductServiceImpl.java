package com.jsonlab.service.impl;

import com.google.gson.Gson;
import com.jsonlab.model.dto.ProductNameAndPriceDTO;
import com.jsonlab.model.dto.ProductSeedDto;
import com.jsonlab.model.entity.Product;
import com.jsonlab.repository.ProductRepository;
import com.jsonlab.service.CategoryService;
import com.jsonlab.service.ProductService;
import com.jsonlab.service.UserService;
import com.jsonlab.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.jsonlab.constants.GlobalConstants.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_FILE = "products.json";
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, UserService userService, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }


    @Override
    public void seedProducts() throws IOException {
        String fileContent = Files.readString(Path.of(RESOURCE_FILE_PATH + PRODUCT_FILE));



        ProductSeedDto[] productSeedDtos = gson.fromJson(fileContent, ProductSeedDto[].class);
        if (productRepository.count() > 0) {
            return;
        }

        Arrays.stream(productSeedDtos)
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userService.findRandomUser());

                    if (product.getPrice().compareTo(BigDecimal.valueOf(900L)) > 0) {
                    product.setBuyer(userService.findRandomUser());
                    }

                    product.setCategories(categoryService.findRandomCategories());

                    return product;
                })
                .forEach(productRepository::save);


    }

    @Override
    public List<ProductNameAndPriceDTO> findAllProductsInRangeOrderByPriceAsc(BigDecimal lower, BigDecimal upper) {
        return productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(lower, upper)
                .stream()
                .map(product -> {
                    ProductNameAndPriceDTO productNameAndPriceDTO = modelMapper
                            .map(product, ProductNameAndPriceDTO.class);

                    productNameAndPriceDTO.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));
                    return productNameAndPriceDTO;
                })
                .collect(Collectors.toList());
    }
}
