package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.AuditableArea;

/**
 * Spring Data  repository for the AuditableArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditableAreaRepository extends JpaRepository<AuditableArea, Long> {}
