package com.spring.security.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {


    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(username,claims);

    }

    private String createToken(String username, Map<String, Object> claims) {

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 1000*60*30))
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        byte[] bytes = Decoders.BASE64.decode("25e197977a315a4273e81ad415269e0083df0c75de4d281d14384dfcbf52d6b626a42cb1141310c0694eb95ac99460a4d4c17bfaaa3a7a33de830f2ce24f6dd2");
        return Keys.hmacShaKeyFor(bytes);
    }
}
