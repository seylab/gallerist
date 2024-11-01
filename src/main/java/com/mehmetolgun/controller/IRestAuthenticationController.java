package com.mehmetolgun.controller;

import com.mehmetolgun.dto.AuthRequest;
import com.mehmetolgun.dto.AuthResponse;
import com.mehmetolgun.dto.DtoUser;
import com.mehmetolgun.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {
    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
