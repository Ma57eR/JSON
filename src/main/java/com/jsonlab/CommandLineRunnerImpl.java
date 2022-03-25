package com.jsonlab;


import com.google.gson.Gson;
import com.jsonlab.model.dto.ProductNameAndPriceDTO;
import com.jsonlab.model.dto.ProductWithBuyerDTO;
import com.jsonlab.model.dto.UserSoldDTO;
import com.jsonlab.service.CategoryService;
import com.jsonlab.service.ProductService;
import com.jsonlab.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Controller
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;
    private static final String OUTPUT_PATH = "src/main/resources/files/out/";
    private static final String PRODUCTS_IN_RANGE_FILE_NAME = "products-in-range.json";
    private static final String USER_AND_SOLD_PRODUCT_FILE_NAME = "users-and-sold-products.json";
    private final Gson gson;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        seedData();

        System.out.println("Enter exercise:");
        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum) {
            case 1 -> productsInRange();
            case 2 -> soldProducts();
        }


    }

    private void soldProducts() throws IOException, JAXBException {
     UserSoldDTO userSoldDTOS = userService
                .findAllUsersWithMoreThanOneSoldProduct().stream().limit(1).findAny().get();

        //String content = gson.toJson(userSoldDTOS);

     //writeToFile(OUTPUT_PATH + USER_AND_SOLD_PRODUCT_FILE_NAME, content);


        JAXBContext context = JAXBContext.newInstance(UserSoldDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(userSoldDTOS, new File(OUTPUT_PATH + "test.xml"));
    }


    private void productsInRange() throws IOException {
        List<ProductNameAndPriceDTO> productNameAndPriceDTOS= productService
                .findAllProductsInRangeOrderByPriceAsc(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L));

        String content = gson.toJson(productNameAndPriceDTOS);

        writeToFile(OUTPUT_PATH + PRODUCTS_IN_RANGE_FILE_NAME, content);
    }

    private void writeToFile(String filePath, String content) throws IOException {
        Files
                .write(Path.of(filePath), Collections.singleton(content));
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        userService.seedUsers();
        productService.seedProducts();
    }
}
