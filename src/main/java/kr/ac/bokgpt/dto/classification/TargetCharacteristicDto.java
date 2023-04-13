package kr.ac.bokgpt.dto.classification;

import kr.ac.bokgpt.domain.classification.TargetCharacteristic;
import lombok.Builder;

@Builder
public record TargetCharacteristicDto(
        Long id,
        String characteristic
) {
    public static TargetCharacteristicDto from(TargetCharacteristic entity) {
        return TargetCharacteristicDto.builder()
                .id(entity.getId())
                .characteristic(entity.getCharacteristic())
                .build();
    }
}
