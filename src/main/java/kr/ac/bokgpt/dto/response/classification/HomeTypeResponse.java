package kr.ac.bokgpt.dto.response.classification;

import kr.ac.bokgpt.dto.classification.HomeTypeDto;
import lombok.Builder;

import java.util.List;

@Builder
public record HomeTypeResponse(
        List<HomeTypeDto> homeTypeResponses
) {
    public static HomeTypeResponse from(List<HomeTypeDto> homeTypeDtos) {
        return HomeTypeResponse.builder()
                .homeTypeResponses(homeTypeDtos)
                .build();
    }

}
