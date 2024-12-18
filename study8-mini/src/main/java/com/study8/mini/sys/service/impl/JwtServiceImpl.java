package com.study8.mini.sys.service.impl;

import com.study8.mini.auth.dto.AuthAccountDto;
import com.study8.mini.configuration.constant.SecurityConstant;
import com.study8.mini.configuration.security.UserPrincipal;
import com.study8.mini.sys.constant.SysConstant;
import com.study8.mini.sys.service.JwtService;
import com.study8.mini.sys.service.SysConfigurationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@Transactional
@Slf4j
public class JwtServiceImpl implements JwtService {
    @Autowired
    private CacheManager cacheManager;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(SecurityConstant.AUTHORIZATION);
        if (StringUtils.isNotEmpty(headerAuth)
                && headerAuth.startsWith(SecurityConstant.BEARER)) {
            return headerAuth.substring(7);
        }
        return null;
    }

    @Override
    public String generateJwtToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setId(userPrincipal.getId().toString())
                .claim("username", userPrincipal.getUsername())
                .claim("email", userPrincipal.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()
                        + jwtExpiration))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getUserNameFormToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("username", String.class);
    }


    @Override
    public boolean validateToken(String authToken) {
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("JwtUtils | Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.error("JwtUtils | JWT token is expired", e);
        } catch (UnsupportedJwtException e) {
            log.error("JwtUtils | JWT token is unsupported", e);
        } catch (IllegalArgumentException e) {
            log.error("JwtUtils | JWT claims string is empty", e);
        }
        return false;
    }

    @Override
    public void blackListToken(String token) {
        try {
            Cache cache = cacheManager.getCache(SysConstant.BLACK_LIST_TOKEN);
            if (ObjectUtils.isNotEmpty(cache)) {
                cache.put(SysConstant.BLACK_LIST_TOKEN, token);
            }
        } catch (Exception e) {
            log.error("JwtServiceImpl | blackListToken", e);
        }
    }

    @Override
    public String generateJwtTokenForgotPassword(AuthAccountDto accountDto) {
        return null;
    }
}
