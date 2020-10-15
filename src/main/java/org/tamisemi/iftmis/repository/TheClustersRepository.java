package org.tamisemi.iftmis.repository;

import org.tamisemi.iftmis.domain.TheClusters;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TheClusters entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TheClustersRepository extends JpaRepository<TheClusters, Long>, JpaSpecificationExecutor<TheClusters> {
}
