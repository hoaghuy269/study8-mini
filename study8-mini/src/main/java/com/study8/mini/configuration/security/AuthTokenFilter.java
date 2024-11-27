package com.study8.mini.configuration.security;

import com.study8.mini.auth.service.AuthAccountService;
import com.study8.mini.configuration.constant.SecurityConstant;
import com.study8.mini.sys.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * AuthTokenFilter
 * @Date: 2024-11-19
 * @Author: HuyNH
 * @Desc: AuthTokenFilter
 */
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthAccountService authAccountService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        //No filter
        String uri = request.getRequestURI();
        List<String> acceptUriPatterns = List.of(SecurityConstant.CAMUNDA_URL, SecurityConstant.AUTH_URL);

        AntPathMatcher pathMatcher = new AntPathMatcher();
        boolean isExcluded = acceptUriPatterns.stream().anyMatch(pattern -> pathMatcher.match(pattern, uri));
        if (isExcluded) {
            filterChain.doFilter(request, response);
        }

        //Do filter
        try {
            String jwt = jwtService.parseJwt(request);
            if (StringUtils.isNotEmpty(jwt)) {
                boolean tokenValid = jwtService.validateToken(jwt);
                if (tokenValid) {
                    String username = jwtService.getUserNameFormToken(jwt);

                    UserPrincipal userPrincipal = authAccountService.loadUserPrincipal(username);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userPrincipal,
                                    null,
                                    userPrincipal.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            log.error("AuthTokenFilter | doFilterInternal", e);
        }
        filterChain.doFilter(request, response);
    }
}
