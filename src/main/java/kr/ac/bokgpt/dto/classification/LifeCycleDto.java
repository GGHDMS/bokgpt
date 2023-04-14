package kr.ac.bokgpt.dto.classification;

import kr.ac.bokgpt.domain.classification.LifeCycle;
import lombok.Builder;

@Builder
public record LifeCycleDto(
        Long id,
        String lifeTime
) {
    public static LifeCycleDto from(LifeCycle entity) {
        return LifeCycleDto.builder()
                .id(entity.getId())
                .lifeTime(entity.getLifeTime())
                .build();
    }

    public static LifeCycleDto from(LifeCycleWithWelfareIdDto lifeCycleWithWelfareIdDto) {
        return LifeCycleDto.builder()
                .id(lifeCycleWithWelfareIdDto.id())
                .lifeTime(lifeCycleWithWelfareIdDto.lifeTime())
                .build();
    }
}
