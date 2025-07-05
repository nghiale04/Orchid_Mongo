package com.sum25.orchids.services;

import com.sum25.orchids.entity.Accounts;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(UserDetails userDetails);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration);
    public boolean isTokenExpired(String token);
    public boolean validateToken(String token, UserDetails userDetails);
    long getExpirationTime();
    Accounts getCurrentAccount();
}
