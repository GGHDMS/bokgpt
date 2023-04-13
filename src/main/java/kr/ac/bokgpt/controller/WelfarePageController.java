package kr.ac.bokgpt.controller;

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

    @GetMapping("/interest-themes/{interestThemeId}")
    public Page<WelfareTitleWithLifeCyclesDto> searchWelfareByInterestTheme(@PathVariable Long interestThemeId, Pageable pageable) {
        return welfareService.searchWelfareByInterestTheme(interestThemeId, pageable);
    }

    @GetMapping("/{welfareId}")
    public WelfareAllInfoDto searchWelfareDetail(@PathVariable Long welfareId) {
        return welfareService.searchWelfareDetail(welfareId);
    }

}
