package kr.ac.bokgpt.security.service;

import kr.ac.bokgpt.security.domain.MemberDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class MemberProvider implements AuthenticationProvider {

    @Autowired
    private MemberService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


//    private MemberAuthenticationToken

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userEmail=authentication.getName();
        String password=(String)authentication.getCredentials();
        MemberDetails memberDetails = (MemberDetails)userDetailsService.loadUserByUsername(userEmail);
        if(!password.matches(memberDetails.getPassword())){
            throw new BadCredentialsException("비밀번호가 틀립니다.");
        }
        return new UsernamePasswordAuthenticationToken(userEmail,password,memberDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == UsernamePasswordAuthenticationToken.class;
    }
}
