package kr.ac.bokgpt.dto.response.classification;

import kr.ac.bokgpt.dto.classification.InterestThemeDto;
import lombok.Builder;

import java.util.List;

@Builder
public record InterestThemeResponse(
        List<InterestThemeDto> InterestThemes
) {
    public static InterestThemeResponse from(List<InterestThemeDto> interestThemeDtos) {
        return InterestThemeResponse.builder()
                .InterestThemes(interestThemeDtos)
                .build();
    }
}
