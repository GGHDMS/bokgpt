package kr.ac.bokgpt.repository.classification;

import kr.ac.bokgpt.domain.classification.HomeType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeTypeRepository extends JpaRepository<HomeType, Long> {
}
