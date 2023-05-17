package kr.ac.bokgpt.security.service;

import kr.ac.bokgpt.domain.Authority;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.dto.MemberDto;
import kr.ac.bokgpt.repository.MemberRepository;
import kr.ac.bokgpt.repository.classification.LifeCycleRepository;
import kr.ac.bokgpt.repository.classification.LocationRepository;
import kr.ac.bokgpt.security.dto.signupDto;
import kr.ac.bokgpt.security.exception.DuplicateMemberException;
import kr.ac.bokgpt.security.exception.NotFoundMemberException;
import kr.ac.bokgpt.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberSignupService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final LocationRepository locationRepository;
    private final LifeCycleRepository lifeCycleRepository;

    @Transactional
    public MemberDto signup(signupDto signupDto) {
        if (memberRepository.findOneWithAuthoritiesByEmail(signupDto.email()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .email(signupDto.email())
                .name(signupDto.name())
                .password(passwordEncoder.encode(signupDto.password()))
                .gender(signupDto.gender())
                .location(locationRepository.findById(signupDto.locationId()).orElseThrow())
                .lifeCycle(lifeCycleRepository.findById(signupDto.lifeCycleId()).orElseThrow())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return MemberDto.from(memberRepository.save(member));
    }

    public Boolean emailCheck(String email) {
        if (email == null) {
            throw new NotFoundMemberException("이메일을 입력해주세요.");
        }
        return memberRepository.existsMemberByEmail(email);
    }

    public MemberDto getMemberWithAuthorities(String email) {
        Member member = memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null);
        return MemberDto.from(member);
    }

    @Transactional(readOnly = true)
    public MemberDto getMyMemberWithAuthorities() {
        return MemberDto.from(
                SecurityUtil.getCurrentEmail()
                        .flatMap(memberRepository::findOneWithAuthoritiesByEmail)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }
}