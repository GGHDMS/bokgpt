package kr.ac.bokgpt.dto.request;

import kr.ac.bokgpt.domain.Gender;
import lombok.Builder;

@Builder
public record ClassificationRequest(
        Gender gender,
        Long lifeCycleId,
        Long locationId,
        Long homeTypeId
) {
}
