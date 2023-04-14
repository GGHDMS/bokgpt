package kr.ac.bokgpt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Tag(name="business")
    @Operation(summary = "get home-types", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/home-types")
    public HomeTypeResponse searchHomeTypes() {
        return HomeTypeResponse.from(tableInfoService.allHomeTypes());
    }

    @Tag(name="business")
    @Operation(summary = "get interest-themes", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/interest-themes")
    public InterestThemeResponse searchInterestThemes() {
        return InterestThemeResponse.from(tableInfoService.allInterestThemes());
    }

    @Tag(name="business")
    @Operation(summary = "get life-cycles", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/life-cycles")
    public LifeCycleResponse searchLifeCycles() {
        return LifeCycleResponse.from(tableInfoService.allLifeCycles());
    }

    @Tag(name="business")
    @Operation(summary = "get locations", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/locations")
    public LocationResponse searchLocations() {
        return LocationResponse.from(tableInfoService.allLocations());
    }

    @Tag(name="business")
    @Operation(summary = "get target-characteristics", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/target-characteristics")
    public TargetCharacteristicResponse searchTargetCharacteristics() {
        return TargetCharacteristicResponse.from(tableInfoService.allTargetCharacteristics());
    }

    @Tag(name="business")
    @Operation(summary = "get enroll-ways", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/enroll-ways")
    public EnrollWayResponse searchEnrollWays() {
        return EnrollWayResponse.from(tableInfoService.allEnrollWays());
    }

    @Tag(name="business")
    @Operation(summary = "get offer-ways", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/offer-ways")
    public OfferWayResponse searchOfferWays() {
        return OfferWayResponse.from(tableInfoService.allOfferWays());
    }

    @Tag(name="business")
    @Operation(summary = "get support-cycles", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/support-cycles")
    public SupportCycleResponse searchSupportCycles() {
        return SupportCycleResponse.from(tableInfoService.allSupportCycles());
    }
}
