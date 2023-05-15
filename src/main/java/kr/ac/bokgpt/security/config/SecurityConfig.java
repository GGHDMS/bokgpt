package kr.ac.bokgpt.security.config;


import kr.ac.bokgpt.security.exception.MemberAuthenticationFailureHandler;
import kr.ac.bokgpt.security.exception.MemberAuthenticationSuccessHandler;
import kr.ac.bokgpt.security.service.MemberProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final MemberAuthenticationSuccessHandler memberAuthenticationSuccessHandler;
    private final MemberAuthenticationFailureHandler memberAuthenticationFailureHandler;

    public SecurityConfig(MemberAuthenticationSuccessHandler memberAuthenticationSuccessHandler, MemberAuthenticationFailureHandler memberAuthenticationFailureHandler) {
        this.memberAuthenticationSuccessHandler = memberAuthenticationSuccessHandler;
        this.memberAuthenticationFailureHandler = memberAuthenticationFailureHandler;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new MemberProvider();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(request->
                        request
                                .requestMatchers("/user/mypage").hasRole("ROLE_USER")
                                .anyRequest().permitAll()
                )
                .csrf().disable()
                .formLogin()
                .permitAll()
                .defaultSuccessUrl("/",false)
                .successHandler(memberAuthenticationSuccessHandler)
                .failureHandler(memberAuthenticationFailureHandler)
                .and()
                .logout(logout-> logout.logoutSuccessUrl("/"))
                .exceptionHandling(e->e.accessDeniedPage("/access-denied"));

        return http.build();
    }

}
