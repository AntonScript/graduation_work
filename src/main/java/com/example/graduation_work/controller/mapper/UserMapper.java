package com.example.graduation_work.controller.mapper;

import com.example.graduation_work.api.dto.user.UserDto;
import com.example.graduation_work.jpa.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
