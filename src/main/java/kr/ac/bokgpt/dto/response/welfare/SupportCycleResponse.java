package kr.ac.bokgpt.dto.response.welfare;

import kr.ac.bokgpt.dto.welfare.SupportCycleDto;
import lombok.Builder;

import java.util.List;

@Builder
public record SupportCycleResponse(
        List<SupportCycleDto> supportCycles
) {
    public static SupportCycleResponse from(List<SupportCycleDto> supportCycleDtos) {
        return SupportCycleResponse.builder()
                .supportCycles(supportCycleDtos)
                .build();
    }
}
