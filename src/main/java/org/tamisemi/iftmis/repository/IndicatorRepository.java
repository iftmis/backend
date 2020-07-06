package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Indicator;

/**
 * Spring Data  repository for the Indicator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {}
