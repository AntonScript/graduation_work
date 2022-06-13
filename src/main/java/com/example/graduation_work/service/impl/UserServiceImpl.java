package com.example.graduation_work.service.impl;

import com.example.graduation_work.api.dto.user.UserDto;
import com.example.graduation_work.controller.mapper.UserMapper;
import com.example.graduation_work.jpa.entity.User;
import com.example.graduation_work.jpa.repository.UserRepo;
import com.example.graduation_work.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<User> create(UserDto dto) {
        return Optional.of(userRepo.save(userMapper.toEntity(dto)));
    }
}
