package kr.ac.bokgpt.security.service;


import jakarta.persistence.EntityNotFoundException;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.domain.classification.Location;
import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import kr.ac.bokgpt.dto.classification.LocationDto;
import kr.ac.bokgpt.repository.MemberRepository;
import kr.ac.bokgpt.repository.classification.LifeCycleRepository;
import kr.ac.bokgpt.repository.classification.LocationRepository;
import kr.ac.bokgpt.security.domain.Role;
import kr.ac.bokgpt.security.dto.MemberRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRegisterService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final LocationRepository locationRepository;
    private final LifeCycleRepository lifeCycleRepository;


    public Boolean emailCheck(String email){
        if(email==null){
            throw new NullPointerException("이메일을 입력해주세요.");
        }
        return memberRepository.existsMemberByEmail(email);
    }

    public String register(MemberRegisterDto memberRegisterDto){
        Member member = Member.builder()
                .name(memberRegisterDto.name())
                .email(memberRegisterDto.email())
                .gender(memberRegisterDto.gender())
                .location(locationRepository.findById(memberRegisterDto.locationId()).orElseThrow(EntityNotFoundException::new))
                .lifeCycle(lifeCycleRepository.findById(memberRegisterDto.lifeCycleId()).orElseThrow(EntityNotFoundException::new))
                .password(bCryptPasswordEncoder.encode(memberRegisterDto.password()))
                .role(Role.USER)
                .build();
        memberRepository.save(member);
        return member.getName()+"님 회원가입이 완료되었습니다.";
    }
}
