package kr.ac.bokgpt.repository;

import kr.ac.bokgpt.domain.Welfare;
import kr.ac.bokgpt.repository.querydsl.WelfareRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WelfareRepository extends WelfareRepositoryCustom, JpaRepository<Welfare, Long> {
}
