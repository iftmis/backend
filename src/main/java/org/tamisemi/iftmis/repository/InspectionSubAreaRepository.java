package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionSubArea;

/**
 * Spring Data  repository for the InspectionSubArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionSubAreaRepository extends JpaRepository<InspectionSubArea, Long> {}
