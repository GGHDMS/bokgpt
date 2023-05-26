package kr.ac.bokgpt.security.service;

import kr.ac.bokgpt.domain.Authority;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.domain.relationship.member.MemberHomeType;
import kr.ac.bokgpt.dto.MemberDto;
import kr.ac.bokgpt.repository.MemberRepository;
import kr.ac.bokgpt.repository.classification.HomeTypeRepository;
import kr.ac.bokgpt.repository.classification.LifeCycleRepository;
import kr.ac.bokgpt.repository.classification.LocationRepository;
import kr.ac.bokgpt.repository.relationship.member.MemberHomeTypeRepository;
import kr.ac.bokgpt.security.dto.signupDto;
import kr.ac.bokgpt.security.exception.DuplicateMemberException;
import kr.ac.bokgpt.security.exception.MemberNotFoundException;
import kr.ac.bokgpt.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberSignupService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final LocationRepository locationRepository;
    private final LifeCycleRepository lifeCycleRepository;
    private final HomeTypeRepository homeTypeRepository;
    private final MemberHomeTypeRepository memberHomeTypeRepository;

    @Transactional
    public Long signup(signupDto signupDto) {
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
                .location(locationRepository.getReferenceById(signupDto.locationId()))
                .lifeCycle(lifeCycleRepository.getReferenceById(signupDto.lifeCycleId()))
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        Member savedMember = memberRepository.save(member);

        MemberHomeType memberHomeType = MemberHomeType.builder()
                .member(savedMember)
                .homeType(homeTypeRepository.getReferenceById(signupDto.homeTypeId()))
                .build();

        memberHomeTypeRepository.save(memberHomeType);
        return member.getId();
    }

    public Boolean emailCheck(String email) {
        if (email == null) {
            throw new MemberNotFoundException("이메일을 입력해주세요.");
        }
        return memberRepository.existsMemberByEmail(email);
    }

    public MemberDto getMemberWithAuthorities(String email) {
        Member member = memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null);
        return MemberDto.from(member);
    }

    public MemberDto getMyMemberWithAuthorities() {
        return MemberDto.from(
                SecurityUtil.getCurrentEmail()
                        .flatMap(memberRepository::findOneWithAuthoritiesByEmail)
                        .orElseThrow(() -> new MemberNotFoundException("Member not found"))
        );
    }
}