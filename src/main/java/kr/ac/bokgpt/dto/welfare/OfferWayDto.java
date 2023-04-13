package kr.ac.bokgpt.dto.welfare;

import kr.ac.bokgpt.domain.welfare.OfferWay;
import lombok.Builder;

@Builder
public record OfferWayDto(
        Long id,
        String way
) {
    public static OfferWayDto from(OfferWay entity) {
        return OfferWayDto.builder()
                .id(entity.getId())
                .way(entity.getWay())
                .build();
    }
}
