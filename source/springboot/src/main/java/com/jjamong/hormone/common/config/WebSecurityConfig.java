package com.jjamong.hormone.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jjamong.hormone.common.jwt.JwtAuthenticationFilter;
import com.jjamong.hormone.common.jwt.JwtExceptionFilter;
import com.jjamong.hormone.common.jwt.JwtProvider;
import com.jjamong.hormone.common.jwt.RedisProvider;
import com.jjamong.hormone.common.util.CookieUtil;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

    JwtAuthenticationFilter jwtAuthenticationFilter(JwtProvider jwtProvider, CookieUtil cookieUtil,
            RedisProvider redisProvider) {
        return new JwtAuthenticationFilter(jwtProvider, cookieUtil, redisProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtProvider jwtProvider, CookieUtil cookieUtil,
            RedisProvider redisProvider) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests()
                .antMatchers("/api/user/**", "/api/extra/**").permitAll()
                .antMatchers("/api/main/**", "/api/user/login").hasAnyRole("USER")
                .and().addFilterBefore(jwtAuthenticationFilter(jwtProvider, cookieUtil, redisProvider),
                        UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtExceptionFilter(),
                jwtAuthenticationFilter(jwtProvider, cookieUtil, redisProvider).getClass());

        return http.build();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT")
                .maxAge(3000);
    }
}
