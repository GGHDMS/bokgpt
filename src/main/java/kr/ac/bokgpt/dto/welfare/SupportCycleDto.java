package kr.ac.bokgpt.dto.welfare;

import kr.ac.bokgpt.domain.welfare.SupportCycle;
import lombok.Builder;

@Builder
public record SupportCycleDto(
        Long id,
        String cycle
) {
    public static SupportCycleDto from(SupportCycle entity) {
        return SupportCycleDto.builder()
                .id(entity.getId())
                .cycle(entity.getCycle())
                .build();
    }
}
