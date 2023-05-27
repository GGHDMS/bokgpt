package kr.ac.bokgpt.service;

import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.domain.classification.HomeType;
import kr.ac.bokgpt.domain.classification.LifeCycle;
import kr.ac.bokgpt.domain.classification.Location;
import kr.ac.bokgpt.domain.relationship.member.MemberHomeType;
import kr.ac.bokgpt.dto.MemberClassificationDto;
import kr.ac.bokgpt.dto.MemberDto;
import kr.ac.bokgpt.dto.classification.HomeTypeDto;
import kr.ac.bokgpt.dto.request.MemberRequest;
import kr.ac.bokgpt.dto.response.Member.MemberResponse;
import kr.ac.bokgpt.dto.response.classification.ClassificationResponse;
import kr.ac.bokgpt.repository.MemberRepository;
import kr.ac.bokgpt.repository.classification.HomeTypeRepository;
import kr.ac.bokgpt.repository.classification.LifeCycleRepository;
import kr.ac.bokgpt.repository.classification.LocationRepository;
import kr.ac.bokgpt.repository.relationship.member.MemberHomeTypeRepository;
import kr.ac.bokgpt.security.exception.EmailNotFoundException;
import kr.ac.bokgpt.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberHomeTypeRepository memberHomeTypeRepository;
    private final LifeCycleRepository lifeCycleRepository;
    private final LocationRepository locationRepository;
    private final HomeTypeRepository homeTypeRepository;

    public MemberResponse searchMemberInfo() {
        MemberDto memberDto = MemberDto.from(memberRepository.findMemberByEmail(getCurrentEmail()));
        HomeTypeDto homeTypeDto = HomeTypeDto.from(memberHomeTypeRepository.findHomeTypeByMemberId(memberDto.id()));

        return MemberResponse.from(memberDto, homeTypeDto);
    }

    @Transactional
    public void updateMemberInfo(MemberRequest memberRequest) {
        Member member = memberRepository.findMemberByEmail(getCurrentEmail());
        MemberHomeType memberHomeType = memberHomeTypeRepository.findOneByMember_Id(member.getId());

        LifeCycle lifeCycle = lifeCycleRepository.getReferenceById(memberRequest.lifeCycleId());
        Location location = locationRepository.getReferenceById(memberRequest.locationId());
        member.updateMember(memberRequest.name(), memberRequest.gender(), lifeCycle, location);

        HomeType homeType = homeTypeRepository.getReferenceById(memberRequest.homeTypeId());

        memberHomeType.updateHomeType(homeType);
    }

    public ClassificationResponse searchMemberClassification() {
        MemberClassificationDto memberClassificationDto = memberRepository.findClassificationByEmail(getCurrentEmail());
        HomeTypeDto homeTypeDto = HomeTypeDto.from(memberHomeTypeRepository.findHomeTypeByMemberId(memberClassificationDto.id()));

        return ClassificationResponse.from(memberClassificationDto, homeTypeDto);
    }


    private String getCurrentEmail() {
        return SecurityUtil.getCurrentEmail()
                .orElseThrow(() -> new EmailNotFoundException("Email not found"));
    }


}
