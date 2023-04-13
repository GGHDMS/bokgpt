package kr.ac.bokgpt.repository.classification;

import kr.ac.bokgpt.domain.classification.TargetCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetCharacteristicRepository extends JpaRepository<TargetCharacteristic, Long> {
}
