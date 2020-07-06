package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionArea;

/**
 * Spring Data  repository for the InspectionArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionAreaRepository extends JpaRepository<InspectionArea, Long> {}
