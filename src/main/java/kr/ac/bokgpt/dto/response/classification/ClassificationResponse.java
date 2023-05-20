package kr.ac.bokgpt.dto.response.classification;

import kr.ac.bokgpt.domain.Gender;
import kr.ac.bokgpt.dto.MemberClassificationDto;
import kr.ac.bokgpt.dto.classification.HomeTypeDto;
import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import kr.ac.bokgpt.dto.classification.LocationDto;
import lombok.Builder;

@Builder
public record ClassificationResponse(
        Gender gender,
        LifeCycleDto lifeCycle,
        LocationDto location,
        HomeTypeDto homeType
) {
    public static ClassificationResponse from(MemberClassificationDto memberClassificationDto, HomeTypeDto homeTypeDto) {
        return ClassificationResponse.builder()
                .gender(memberClassificationDto.gender())
                .lifeCycle(memberClassificationDto.lifeCycle())
                .location(memberClassificationDto.location())
                .homeType(homeTypeDto)
                .build();
    }
}
