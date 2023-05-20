package kr.ac.bokgpt.dto;

import kr.ac.bokgpt.domain.Gender;
import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import kr.ac.bokgpt.dto.classification.LocationDto;
import lombok.Builder;

@Builder
public record MemberClassificationDto(
        Long id,
        Gender gender,
        LifeCycleDto lifeCycle,
        LocationDto location
) {

}
