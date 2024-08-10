package com.eazybytes.global.util;

import com.eazybytes.domain.auth.dto.AccessTokenDto;
import com.eazybytes.domain.customer.domain.CustomerRole;
import com.eazybytes.infra.config.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static com.eazybytes.global.common.constants.SecurityConstants.TOKEN_ROLE_NAME;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;

    public String generateAccessToken(String customerEmail, CustomerRole role) {
        Date issuedAt = new Date();
        Date expiredAt =
                new Date(issuedAt.getTime() + jwtProperties.accessTokenExpirationMilliTime());
        return buildAccessToken(customerEmail, role, issuedAt, expiredAt);
    }

    public AccessTokenDto parseAccessToken(String token) throws ExpiredJwtException {
        try {
            Jws<Claims> claims = getClaims(token, getAccessTokenKey());

            return new AccessTokenDto(
                    claims.getBody().getSubject(),
                    CustomerRole.valueOf(claims.getBody().get(TOKEN_ROLE_NAME, String.class)),
                    token);
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            return null;
        }
    }

    private Key getAccessTokenKey() {
        return Keys.hmacShaKeyFor(jwtProperties.accessTokenSecret().getBytes());
    }

    private Jws<Claims> getClaims(String token, Key key) {
        return Jwts.parserBuilder()
                .requireIssuer(jwtProperties.issuer())
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    private String buildAccessToken(String customerEmail, CustomerRole role, Date issuedAt, Date expiredAt) {
        return Jwts.builder()
                .setIssuer(jwtProperties.issuer())
                .setSubject(customerEmail)
                .claim(TOKEN_ROLE_NAME, role.name())
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(getAccessTokenKey())
                .compact();
    }
}
