package kr.ac.bokgpt.dto.response.classification;

import kr.ac.bokgpt.dto.classification.TargetCharacteristicDto;
import lombok.Builder;

import java.util.List;

@Builder
public record TargetCharacteristicResponse(
        List<TargetCharacteristicDto> targetCharacteristics
) {
    public static TargetCharacteristicResponse from(List<TargetCharacteristicDto> targetCharacteristicDtos) {
        return TargetCharacteristicResponse.builder()
                .targetCharacteristics(targetCharacteristicDtos)
                .build();
    }
}
