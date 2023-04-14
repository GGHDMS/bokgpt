package kr.ac.bokgpt.repository.relationship.welfare;

import kr.ac.bokgpt.domain.classification.TargetCharacteristic;
import kr.ac.bokgpt.domain.relationship.welfare.WelfareTargetCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WelfareTargetCharacteristicRepository extends JpaRepository<WelfareTargetCharacteristic, Long> {
    @Query("select w.targetCharacteristic from WelfareTargetCharacteristic w where w.welfare.id = :welfareId order by w.targetCharacteristic.id")
    List<TargetCharacteristic> findAllTargetCharacteristicsByWelfareId(@Param("welfareId") Long welfareId);
}
