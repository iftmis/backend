package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionWorkDone;

/**
 * Spring Data  repository for the InspectionWorkDone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionWorkDoneRepository extends JpaRepository<InspectionWorkDone, Long> {}
