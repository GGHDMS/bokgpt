package kr.ac.bokgpt.repository.classification;

import kr.ac.bokgpt.domain.classification.LifeCycle;
import kr.ac.bokgpt.repository.querydsl.LifeCycleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifeCycleRepository extends LifeCycleRepositoryCustom, JpaRepository<LifeCycle, Long> {
}
