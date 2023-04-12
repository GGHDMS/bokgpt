package kr.ac.bokgpt.dto.classification;

import kr.ac.bokgpt.domain.classification.InterestTheme;
import lombok.Builder;

@Builder
public record InterestThemeDto(
        Long id,
        String theme
) {
    public static InterestThemeDto from(InterestTheme entity) {
        return InterestThemeDto.builder()
                .id(entity.getId())
                .theme(entity.getTheme())
                .build();
    }
}
