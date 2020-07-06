package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionIndicator;

/**
 * Spring Data  repository for the InspectionIndicator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionIndicatorRepository extends JpaRepository<InspectionIndicator, Long> {}
