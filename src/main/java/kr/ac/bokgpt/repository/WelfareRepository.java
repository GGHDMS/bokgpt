package kr.ac.bokgpt.repository;

import kr.ac.bokgpt.domain.Welfare;
import kr.ac.bokgpt.repository.querydsl.WelfareRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WelfareRepository extends WelfareRepositoryCustom, JpaRepository<Welfare, Long> {
    @Query("select w from Welfare w join fetch w.location join fetch w.supportCycle where w.id = :id")
    Optional<Welfare> findByIdWithLocationAndSupportCycle(@Param("id") Long id);
}