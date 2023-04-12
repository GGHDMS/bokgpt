package kr.ac.bokgpt.dto.classification;

import lombok.Builder;

@Builder
public record LifeCycleDto(
        Long id,
        String lifeTime
) {
    public static LifeCycleDto from(LifeCycleWithWelfareIdDto lifeCycleWithWelfareIdDto) {
        return LifeCycleDto.builder()
                .id(lifeCycleWithWelfareIdDto.id())
                .lifeTime(lifeCycleWithWelfareIdDto.lifeTime())
                .build();
    }
}
