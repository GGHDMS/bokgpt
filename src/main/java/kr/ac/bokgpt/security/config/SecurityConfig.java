package kr.ac.bokgpt.security.config;


import kr.ac.bokgpt.security.service.CustomOAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final CustomOAuthService customOAuthService;

    public SecurityConfig(CustomOAuthService customOAuthService) {
        this.customOAuthService = customOAuthService;
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login/**").permitAll()
                .and()
                .oauth2Login().userInfoEndpoint().userService(customOAuthService)
                .and().defaultSuccessUrl("/login/kakao");


        return http.build();
    }

}
