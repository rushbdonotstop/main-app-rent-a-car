package com.example.zuul.security;

import com.example.zuul.service.MyUserDetailsService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil = new JwtUtil();


    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException, ServletException {
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        System.out.println(authorizationHeader);


        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            //izbacujemo Bearer(i razmak)

            jwt = authorizationHeader.substring(7);
            System.out.println(jwtUtil.toString());
            username = jwtUtil.extractUsername(jwt);
        }else {

            filterChain.doFilter(request, response);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            Claims claims = jwtUtil.extractAllClaims(jwt);


                List<String> authorityList = (List) claims.get("authorities");

            System.out.println(authorityList);


            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    jwtUtil.extractUsername(jwt), null, authorityList.stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList()));
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                    .buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
