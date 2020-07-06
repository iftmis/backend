package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Finding;

/**
 * Spring Data  repository for the Finding entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FindingRepository extends JpaRepository<Finding, Long> {}
