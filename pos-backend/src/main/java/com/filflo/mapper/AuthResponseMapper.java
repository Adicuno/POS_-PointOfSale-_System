package com.filflo.mapper;

import com.filflo.modal.User;
import com.filflo.payload.response.AuthResponse;

public class AuthResponseMapper {

    public static AuthResponse toDto(User user, String jwt) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(user));

        return authResponse;
    }
}
