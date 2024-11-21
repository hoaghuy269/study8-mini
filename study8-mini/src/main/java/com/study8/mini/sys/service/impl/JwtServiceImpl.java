package com.study8.mini.sys.service.impl;

import com.study8.mini.configuration.constant.SecurityConstant;
import com.study8.mini.configuration.security.UserPrincipal;
import com.study8.mini.sys.constant.SysConstant;
import com.study8.mini.sys.service.JwtService;
import com.study8.mini.sys.service.SysConfigurationService;
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

    @Autowired
    private SysConfigurationService sysConfigurationService;

    @Value("${jwt.expiration}")
    private int defaultJwtExpiration;

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
        SecretKey secretKey = this.getSecretKey();
        int jwtExpiration = this.getJwtExpiration();

        return Jwts.builder()
                .setId(userPrincipal.getId().toString())
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()
                        + jwtExpiration))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getUserNameFormToken(String token) {
        SecretKey secretKey = this.getSecretKey();
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public boolean validateToken(String authToken) {
        SecretKey secretKey = this.getSecretKey();
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

    private SecretKey getSecretKey() {
        try {
            String jwtSecret;
            Cache cache = cacheManager.getCache(SysConstant.DEFAULT_CACHE);
            if (ObjectUtils.isNotEmpty(cache)) {
                jwtSecret = cache.get(SysConstant.JWT_SECRET_CACHE_KEY, String.class);
                if (StringUtils.isEmpty(jwtSecret)) {
                    jwtSecret = sysConfigurationService.getStringConfig(SysConstant.JWT, SysConstant.JWT_SECRET);

                    cache.put(SysConstant.JWT_SECRET_CACHE_KEY, jwtSecret);
                }
                return Keys.hmacShaKeyFor(jwtSecret
                        .getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            log.error("JwtServiceImpl | getSecretKey", e);
        }
        return null;
    }

    public Integer getJwtExpiration() {
        Integer jwtExpiration = null;
        Cache cache = cacheManager.getCache(SysConstant.DEFAULT_CACHE);
        if (cache != null) {
            jwtExpiration = cache.get(SysConstant.JWT_EXPIRATION_CACHE_KEY, Integer.class);
            if (jwtExpiration == null) {
                jwtExpiration = sysConfigurationService.getIntConfig(SysConstant.JWT, SysConstant.JWT_EXPIRATION);
            } else {
                jwtExpiration = defaultJwtExpiration;
            }
        }
        return jwtExpiration;
    }
}
