package kr.ac.bokgpt.dto.response.Member;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.ac.bokgpt.domain.Gender;
import kr.ac.bokgpt.dto.MemberDto;
import kr.ac.bokgpt.dto.classification.HomeTypeDto;
import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import kr.ac.bokgpt.dto.classification.LocationDto;
import lombok.Builder;

@Builder
public record MemberResponse(
        Long id,
        String email,
        String name,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        Gender gender,
        LifeCycleDto lifeCycle,
        LocationDto location,
        HomeTypeDto homeType
) {
    public static MemberResponse from(MemberDto memberDto, HomeTypeDto homeTypeDto) {
        return MemberResponse.builder()
                .id(memberDto.id())
                .email(memberDto.email())
                .name(memberDto.name())
                .password(memberDto.password())
                .gender(memberDto.gender())
                .lifeCycle(memberDto.lifeCycle())
                .location(memberDto.location())
                .homeType(homeTypeDto)
                .build();
    }
}
