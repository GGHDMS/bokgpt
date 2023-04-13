package kr.ac.bokgpt.dto.response.classification;

import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import lombok.Builder;

import java.util.List;

@Builder
public record LifeCycleResponse(
        List<LifeCycleDto> lifecycles
) {
    public static LifeCycleResponse from(List<LifeCycleDto> lifeCycleDtos) {
        return LifeCycleResponse.builder()
                .lifecycles(lifeCycleDtos)
                .build();
    }
}
