package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionFinding;

/**
 * Spring Data  repository for the InspectionFinding entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionFindingRepository extends JpaRepository<InspectionFinding, Long> {}
