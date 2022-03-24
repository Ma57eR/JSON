package com.jsonlab.service;

import com.jsonlab.model.dto.UserSoldDTO;
import com.jsonlab.model.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface UserService{
    void seedUsers() throws IOException;

    User findRandomUser();


    List<UserSoldDTO> findAllUsersWithMoreThanOneSoldProduct();
}
