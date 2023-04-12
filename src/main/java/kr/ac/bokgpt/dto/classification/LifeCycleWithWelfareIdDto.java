package kr.ac.bokgpt.dto.classification;

import lombok.Builder;

@Builder
public record LifeCycleWithWelfareIdDto(
        Long id,
        String lifeTime,
        Long welfareId
) {
}
