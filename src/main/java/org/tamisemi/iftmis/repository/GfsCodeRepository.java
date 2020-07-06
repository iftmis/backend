package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.GfsCode;

/**
 * Spring Data  repository for the GfsCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GfsCodeRepository extends JpaRepository<GfsCode, Long> {}
