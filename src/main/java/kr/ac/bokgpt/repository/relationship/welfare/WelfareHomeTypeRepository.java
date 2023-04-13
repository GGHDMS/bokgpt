package kr.ac.bokgpt.repository.relationship.welfare;

import kr.ac.bokgpt.domain.classification.HomeType;
import kr.ac.bokgpt.domain.relationship.welfare.WelfareHomeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WelfareHomeTypeRepository extends JpaRepository<WelfareHomeType, Long> {
    @Query("select w.homeType from WelfareHomeType w where w.welfare.id = :welfareId order by w.homeType.id")
    List<HomeType> findAllHomeTypesByWelfareId(@Param("welfareId") Long welfareId);
}
