package com.example.graduation_work.api.dto.user;

import com.example.graduation_work.jpa.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull
    private String login;

    @JsonIgnore
    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String patronymic;

    @NotNull
    private UserRole role;
}
