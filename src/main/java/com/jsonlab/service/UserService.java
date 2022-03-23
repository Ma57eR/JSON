package com.jsonlab.service;

import com.jsonlab.model.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UserService{
    void seedUsers() throws IOException;

    User findRandomUser();
}
