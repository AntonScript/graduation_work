package com.example.graduation_work.service.impl.auth;

import com.example.graduation_work.api.dto.user.MessageDto;
import com.example.graduation_work.api.dto.user.TokenDto;
import com.example.graduation_work.api.dto.user.UserAuthDto;
import com.example.graduation_work.api.dto.user.UserDto;
import com.example.graduation_work.config.exception.ApplicationException;
import com.example.graduation_work.jpa.entity.User;
import com.example.graduation_work.jpa.repository.UserRepo;
import com.example.graduation_work.service.AuthService;
import com.example.graduation_work.service.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;

    private final JwtUtil jwtUtil;

    @Override
    public TokenDto logUser(UserAuthDto user) {
        if(user.getLogin() == null){
            throw new ApplicationException("Введите логин");
        }
        if(user.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Введите пароль");
        }
        if(!userRepo.existsByLogin(user.getLogin())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пользователя с таким логином не существует");
        }
        User authUser = userRepo.getUserByLogin(user.getLogin());
        if(authUser.getPassword().equals(user.getPassword())){
            String token = jwtUtil.generateToken(authUser);
            return new TokenDto(token);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверный пароль");
        }
    }

    @Override
    public MessageDto regUser(UserDto userDto) {
        return null;
    }
}
