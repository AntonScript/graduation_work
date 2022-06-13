package com.example.graduation_work.service;

import com.example.graduation_work.api.dto.user.MessageDto;
import com.example.graduation_work.api.dto.user.TokenDto;
import com.example.graduation_work.api.dto.user.UserAuthDto;
import com.example.graduation_work.api.dto.user.UserDto;

public interface AuthService {

    TokenDto logUser(UserAuthDto user);

    public MessageDto regUser(UserDto userDto);
}
