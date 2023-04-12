package kr.ac.bokgpt.controller;

import kr.ac.bokgpt.dto.response.InterestThemeResponse;
import kr.ac.bokgpt.service.ClassificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ClassificationController {
    private final ClassificationService classificationService;

    @GetMapping("/interest-themes")
    public InterestThemeResponse searchInterestTheme() {
        return InterestThemeResponse.from(classificationService.allInterestTheme());
    }
}
