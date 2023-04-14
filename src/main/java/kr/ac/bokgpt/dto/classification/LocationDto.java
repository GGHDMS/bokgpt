package kr.ac.bokgpt.dto.classification;

import kr.ac.bokgpt.domain.classification.Location;
import lombok.Builder;

@Builder
public record LocationDto(
        Long id,
        String main,
        String detail
) {
    public static LocationDto from(Location entity) {
        return LocationDto.builder()
                .id(entity.getId())
                .main(entity.getMain())
                .detail(entity.getDetail())
                .build();
    }
}
