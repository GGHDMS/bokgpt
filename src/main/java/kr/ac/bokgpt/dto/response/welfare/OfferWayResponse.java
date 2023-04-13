package kr.ac.bokgpt.dto.response.welfare;

import kr.ac.bokgpt.dto.welfare.OfferWayDto;
import lombok.Builder;

import java.util.List;

@Builder
public record OfferWayResponse(
        List<OfferWayDto> offerWays
) {
    public static OfferWayResponse from(List<OfferWayDto> offerWayDtos) {
        return OfferWayResponse.builder()
                .offerWays(offerWayDtos)
                .build();
    }
}
