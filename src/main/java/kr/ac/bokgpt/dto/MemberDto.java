package kr.ac.bokgpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.ac.bokgpt.domain.Gender;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import kr.ac.bokgpt.dto.classification.LocationDto;
import kr.ac.bokgpt.security.dto.AuthorityDto;
import lombok.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record MemberDto(
        Long id,
        String email,
        String name,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password,
        Gender gender,
        LifeCycleDto lifeCycle,
        LocationDto location,
        Set<AuthorityDto> authoritiesDtos
) {
    public static MemberDto from(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .password(member.getPassword())
                .gender(member.getGender())
                .lifeCycle(LifeCycleDto.from(member.getLifeCycle()))
                .location(LocationDto.from(member.getLocation()))
                .authoritiesDtos(member.getAuthorities().stream().map(AuthorityDto::from).collect(Collectors.toSet()))
                .build();
    }
}
