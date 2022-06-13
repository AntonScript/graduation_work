package com.example.graduation_work.service;

import com.example.graduation_work.api.dto.user.UserDto;
import com.example.graduation_work.jpa.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllUsers();

    Optional<User> create(UserDto dto);
}
