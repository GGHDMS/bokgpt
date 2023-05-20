package kr.ac.bokgpt.repository;

import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.repository.querydsl.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends MemberRepositoryCustom, JpaRepository<Member,Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findOneWithAuthoritiesByEmail(String email);

    Boolean existsMemberByEmail(String email);
}
