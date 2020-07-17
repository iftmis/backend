package org.tamisemi.iftmis.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionArea;

/**
 * Spring Data  repository for the InspectionArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionAreaRepository extends JpaRepository<InspectionArea, Long> {
    List<InspectionArea> findByInspection_Id(Long inspectionId);
}
