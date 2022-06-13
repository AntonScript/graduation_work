package com.example.graduation_work.controller;

import com.example.graduation_work.api.UserApi;
import com.example.graduation_work.api.dto.user.TokenDto;
import com.example.graduation_work.api.dto.user.UserAuthDto;
import com.example.graduation_work.api.dto.user.UserDto;
import com.example.graduation_work.controller.mapper.UserMapper;
import com.example.graduation_work.service.AuthService;
import com.example.graduation_work.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final AuthService authService;

    private final UserService userService;

    private final UserMapper userMapper;

    @Override
    public ResponseEntity<TokenDto> getFile(@RequestBody UserAuthDto dto) {
        return ResponseEntity.ok(authService.logUser(dto));
    }

    @Override
    public ResponseEntity<List<UserDto>> getListUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        return userService.create(dto).map(user -> ResponseEntity.ok(userMapper.toDto(user)))
                          .orElse(ResponseEntity.badRequest().build());
    }
}
