package com.example.graduation_work.api;

import com.example.graduation_work.api.dto.user.TokenDto;
import com.example.graduation_work.api.dto.user.UserAuthDto;
import com.example.graduation_work.api.dto.user.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * API для работы со файлами.
 *
 * @author Anton Mamakin
 */
@Api(description = "API для работы с пользователями", tags = "user")
@RequestMapping(UserApi.PATH)
public interface UserApi {

    String PATH = "/api/rest/v1/user";


    @ApiOperation(value = "", notes = "",
                  response = TokenDto.class, tags = {"user"})
    @PostMapping(value = "/login",
                 produces = {APPLICATION_JSON_UTF8_VALUE},
                 consumes = {APPLICATION_JSON_UTF8_VALUE})
    ResponseEntity<TokenDto> getFile(@RequestBody UserAuthDto dto);

    @ApiOperation(value = "", notes = "",
                  response = UserDto.class, tags = {"user"})
    @GetMapping(value = "/list",
                produces = {APPLICATION_JSON_UTF8_VALUE})
    ResponseEntity<List<UserDto>> getListUsers();

    @ApiOperation(value = "", notes = "",
                  response = UserDto.class, tags = {"user"})
    @GetMapping(value = "/create",
                produces = {APPLICATION_JSON_UTF8_VALUE})
    ResponseEntity<UserDto> createUser(@RequestBody UserDto dto);
}
