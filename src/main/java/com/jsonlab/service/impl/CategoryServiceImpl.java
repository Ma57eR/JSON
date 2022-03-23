package com.jsonlab.service.impl;

import com.google.gson.Gson;
import com.jsonlab.model.dto.CategorySeedDto;
import com.jsonlab.model.entity.Category;
import com.jsonlab.repository.CategoryRepository;
import com.jsonlab.service.CategoryService;
import com.jsonlab.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static com.jsonlab.constants.GlobalConstants.RESOURCE_FILE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILENAME = "categories.json";
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedCategories() throws IOException {
//        if (categoryRepository.count() > 0) {
//            return;
//        }
        String fileContent = Files
                .readString(Path.of(RESOURCE_FILE_PATH + CATEGORIES_FILENAME));

        CategorySeedDto[] categorySeedDtos = gson.fromJson(fileContent, CategorySeedDto[].class);

        Arrays.stream(categorySeedDtos)
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(categoryRepository::save);
    }
}
