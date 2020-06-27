package com.example.zuul.security;

import com.example.zuul.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers(HttpMethod.GET, "/user/penalty").hasAnyRole("ADMINISTRATOR", "AGENT", "END_USER")
                .antMatchers("/user/userPrivileges").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/catalogue/**").hasAnyRole("ADMINISTRATOR", "AGENT", "END_USER")
                .antMatchers(HttpMethod.PUT, "/catalogue/**").hasAnyRole("ADMINISTRATOR", "AGENT", "END_USER")
                .antMatchers(HttpMethod.DELETE, "/catalogue/**").hasRole("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/catalogue/**").hasAnyRole("ADMINISTRATOR", "AGENT", "END_USER")
                .anyRequest().authenticated()
            .and()
                .addFilterAfter(new JwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }
}
