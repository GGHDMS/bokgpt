package kr.ac.bokgpt.dto.response.welfare;

import kr.ac.bokgpt.dto.welfare.EnrollWayDto;
import lombok.Builder;

import java.util.List;

@Builder
public record EnrollWayResponse(
        List<EnrollWayDto> enrollWays
) {
    public static EnrollWayResponse from(List<EnrollWayDto> enrollWayDtos) {
        return EnrollWayResponse.builder()
                .enrollWays(enrollWayDtos)
                .build();
    }
}
