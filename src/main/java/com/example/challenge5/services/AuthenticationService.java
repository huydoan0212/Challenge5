package com.example.challenge5.services;

import com.example.challenge5.dto.request.AuthenticationRequest;
import com.example.challenge5.dto.response.AuthenticationResponse;
import com.example.challenge5.entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;


import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);


    SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException;

    String generateToken(User user);
}
