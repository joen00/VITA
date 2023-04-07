package com.ssafy.vitawearable.config;

import com.ssafy.vitawearable.util.UserUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public UserUtil jwtProvider() {
        return new UserUtil(secret);
    }

}
