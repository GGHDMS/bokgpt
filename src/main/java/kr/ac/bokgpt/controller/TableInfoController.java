package kr.ac.bokgpt.controller;

import kr.ac.bokgpt.dto.response.classification.*;
import kr.ac.bokgpt.dto.response.welfare.EnrollWayResponse;
import kr.ac.bokgpt.dto.response.welfare.OfferWayResponse;
import kr.ac.bokgpt.dto.response.welfare.SupportCycleResponse;
import kr.ac.bokgpt.service.TableInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TableInfoController {
    private final TableInfoService tableInfoService;

    @GetMapping("/home-types")
    public HomeTypeResponse searchHomeTypes() {
        return HomeTypeResponse.from(tableInfoService.allHomeTypes());
    }

    @GetMapping("/interest-themes")
    public InterestThemeResponse searchInterestThemes() {
        return InterestThemeResponse.from(tableInfoService.allInterestThemes());
    }

    @GetMapping("/life-cycles")
    public LifeCycleResponse searchLifeCycles() {
        return LifeCycleResponse.from(tableInfoService.allLifeCycles());
    }

    @GetMapping("/locations")
    public LocationResponse searchLocations() {
        return LocationResponse.from(tableInfoService.allLocations());
    }

    @GetMapping("/target-characteristics")
    public TargetCharacteristicResponse searchTargetCharacteristics() {
        return TargetCharacteristicResponse.from(tableInfoService.allTargetCharacteristics());
    }

    @GetMapping("/enroll-ways")
    public EnrollWayResponse searchEnrollWays() {
        return EnrollWayResponse.from(tableInfoService.allEnrollWays());
    }

    @GetMapping("/offer-ways")
    public OfferWayResponse searchOfferWays() {
        return OfferWayResponse.from(tableInfoService.allOfferWays());
    }

    @GetMapping("/support-cycles")
    public SupportCycleResponse searchSupportCycles() {
        return SupportCycleResponse.from(tableInfoService.allSupportCycles());
    }
}
