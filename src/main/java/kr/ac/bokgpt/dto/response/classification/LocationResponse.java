package kr.ac.bokgpt.dto.response.classification;

import kr.ac.bokgpt.dto.classification.LocationDto;
import lombok.Builder;

import java.util.List;

@Builder
public record LocationResponse(
        List<LocationDto> locations
) {
    public static LocationResponse from(List<LocationDto> locationDtos) {
        return LocationResponse.builder()
                .locations(locationDtos)
                .build();
    }
}
