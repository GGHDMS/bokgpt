package kr.ac.bokgpt.service;

import kr.ac.bokgpt.dto.classification.InterestThemeDto;
import kr.ac.bokgpt.repository.InterestThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ClassificationService {
    private final InterestThemeRepository interestThemeRepository;

    public List<InterestThemeDto> allInterestTheme() {
        return interestThemeRepository.findAll().stream().map(InterestThemeDto::from).toList();
    }
}
