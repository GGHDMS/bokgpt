package kr.ac.bokgpt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ac.bokgpt.dto.WelfareAllInfoDto;
import kr.ac.bokgpt.dto.WelfareTitleWithLifeCyclesDto;
import kr.ac.bokgpt.service.WelfareService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/welfares")
@RestController
public class WelfarePageController {

    public final WelfareService welfareService;

    @Tag(name="business")
    @Operation(summary = "get welfare_by_id", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/{welfareId}")
    public WelfareAllInfoDto searchWelfareDetail(@PathVariable Long welfareId) {
        return welfareService.searchWelfareDetail(welfareId);
    }

    @Tag(name="business")
    @Operation(summary = "get welfares", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping
    public  Page<WelfareTitleWithLifeCyclesDto> searchWelfares(Pageable pageable) {
        return welfareService.searchWelfarePages(pageable);
    }

    @Tag(name="business")
    @Operation(summary = "get interest-themes by interestThemeId", description = "간략한 정보의 복지정보 여러개를 가져오는 api입니다. (구현중)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/interest-themes/{interestThemeId}")
    public Page<WelfareTitleWithLifeCyclesDto> searchWelfareByInterestTheme(@PathVariable Long interestThemeId, Pageable pageable) {
        return welfareService.searchWelfareByInterestTheme(interestThemeId, pageable);
    }
}
