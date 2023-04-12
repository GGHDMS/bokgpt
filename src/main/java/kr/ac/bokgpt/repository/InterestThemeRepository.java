package kr.ac.bokgpt.repository;

import kr.ac.bokgpt.domain.classification.InterestTheme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestThemeRepository extends JpaRepository<InterestTheme, Long> {
}
