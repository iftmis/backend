package org.tamisemi.iftmis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Indicator;
import org.tamisemi.iftmis.domain.SubArea;

import java.util.List;

/**
 * Spring Data  repository for the Indicator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
    Page<Indicator> findAllBySubAreaId(@Param("subAreaId") Long subAreaId, Pageable pageable);
    List<Indicator> findAllBySubAreaId(@Param("subAreaId") Long subAreaId);
}
