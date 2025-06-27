package com.spring.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {


    public String extractUserName(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token){
        return extractClaims(token, Claims::getExpiration);
    }
    public String extractRole(String token){
        return extractAllClaims(token).get("roles").toString();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUserName(token)) && !isExpiredToken(token);
    }

    private boolean isExpiredToken(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String generateToken(String username,String role){
        Map<String,Object> claims = new HashMap<>();
        claims.put("roles",role);
        return createToken(username,claims);

    }

    private String createToken(String username, Map<String, Object> claims) {

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .signWith(getKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 1000*60*30))
                .compact();
    }

    private SecretKey getKey() {
        byte[] bytes = Decoders.BASE64.decode("25e197977a315a4273e81ad415269e0083df0c75de4d281d14384dfcbf52d6b626a42cb1141310c0694eb95ac99460a4d4c17bfaaa3a7a33de830f2ce24f6dd2");
        return Keys.hmacShaKeyFor(bytes);
    }

    public static void main(String[] args) {
        JWTService service = new JWTService();
        String token = service.generateToken("sathya", "ROLE_ADMIN");
        System.out.println(token);
        System.out.println(service.extractUserName(token));
        System.out.println(service.extractRole(token));
    }
}
