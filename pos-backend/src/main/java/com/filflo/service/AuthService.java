package com.filflo.service;

import com.filflo.exception.UserException;
import com.filflo.payload.dto.UserDTO;
import com.filflo.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password) throws UserException;
    AuthResponse signup(UserDTO req) throws UserException;

    void createPasswordResetToken(String email) throws UserException;
    void resetPassword(String token, String newPassword);
}
