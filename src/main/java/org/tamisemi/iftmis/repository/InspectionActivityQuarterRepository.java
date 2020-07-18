package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionActivityQuarter;

/**
 * Spring Data  repository for the InspectionActivityQuarter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionActivityQuarterRepository extends JpaRepository<InspectionActivityQuarter, Long> {}
