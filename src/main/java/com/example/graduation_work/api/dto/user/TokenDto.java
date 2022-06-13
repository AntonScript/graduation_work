package com.example.graduation_work.api.dto.user;

import lombok.Data;

/**
 * дто для токена авторизации.
 *
 * @author Anton Mamakin
 */
@Data
public class TokenDto {

    public TokenDto(String token) {
        this.token = token;
    }

    /**
     * токен авторизированного пользователя
     */
    public String token;


}
