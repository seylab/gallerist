package com.mehmetolgun.service;

import com.mehmetolgun.dto.AuthRequest;
import com.mehmetolgun.dto.AuthResponse;
import com.mehmetolgun.dto.DtoUser;
import com.mehmetolgun.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
}