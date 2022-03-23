package com.jsonlab;


import com.jsonlab.service.CategoryService;
import com.jsonlab.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        seedData();

    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        userService.seedUsers();
    }
}
