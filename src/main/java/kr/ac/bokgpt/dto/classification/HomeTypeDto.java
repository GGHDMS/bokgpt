package kr.ac.bokgpt.dto.classification;

import kr.ac.bokgpt.domain.classification.HomeType;
import lombok.Builder;

@Builder
public record HomeTypeDto(
        Long id,
        String type
) {
    public static HomeTypeDto from(HomeType entity) {
        return HomeTypeDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .build();
    }
}
