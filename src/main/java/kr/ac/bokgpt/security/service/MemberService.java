package kr.ac.bokgpt.security.service;

import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.repository.MemberRepository;
import kr.ac.bokgpt.security.domain.MemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findMemberByEmail(email).orElseThrow(()->new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(member.getRoleKey()));
        return MemberDetails.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .authorities(roles).build();
    }
}
