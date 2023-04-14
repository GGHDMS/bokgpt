package kr.ac.bokgpt.repository.classification;

import kr.ac.bokgpt.domain.classification.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Location> {
}
