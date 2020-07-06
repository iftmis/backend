package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Inspection;

/**
 * Spring Data  repository for the Inspection entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {}
