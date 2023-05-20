package kr.ac.bokgpt.dto;

import kr.ac.bokgpt.domain.Gender;
import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import kr.ac.bokgpt.dto.classification.LocationDto;
import lombok.Builder;

@Builder
public record MemberUpdateDto(
        String name,
        Gender gender,
        LifeCycleDto lifeCycle,
        LocationDto location
) {
}
