package kr.ac.bokgpt.repository.relationship.member;

import kr.ac.bokgpt.domain.classification.TargetCharacteristic;
import kr.ac.bokgpt.domain.relationship.member.MemberTargetCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberTargetCharacteristicRepository extends JpaRepository<MemberTargetCharacteristic, Long> {
    @Query("select m.targetCharacteristic from MemberTargetCharacteristic m where m.member.id= :memberId order by m.targetCharacteristic.id")
    TargetCharacteristic findOneByMemberId(@Param("memberId") Long memberId);
}
