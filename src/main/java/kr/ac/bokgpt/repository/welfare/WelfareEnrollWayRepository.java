package kr.ac.bokgpt.repository.welfare;

import kr.ac.bokgpt.domain.welfare.EnrollWay;
import kr.ac.bokgpt.domain.welfare.WelfareEnrollWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WelfareEnrollWayRepository extends JpaRepository<WelfareEnrollWay, Long> {
    @Query("select w.enrollWay from WelfareEnrollWay w where w.welfare.id = :welfareId order by w.enrollWay.id")
    List<EnrollWay> findAllEnrollWaysByWelfareId(@Param("welfareId") Long welfareId);
}
