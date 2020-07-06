package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Quarter;

/**
 * Spring Data  repository for the Quarter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuarterRepository extends JpaRepository<Quarter, Long> {}
