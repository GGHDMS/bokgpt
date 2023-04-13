package kr.ac.bokgpt.dto.welfare;

import kr.ac.bokgpt.domain.welfare.EnrollWay;
import lombok.Builder;

@Builder
public record EnrollWayDto(
        Long id,
        String way
) {
    public static EnrollWayDto from(EnrollWay entity) {
        return EnrollWayDto.builder()
                .id(entity.getId())
                .way(entity.getWay())
                .build();
    }
}
