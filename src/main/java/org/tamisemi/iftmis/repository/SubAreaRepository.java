package org.tamisemi.iftmis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.SubArea;

import java.util.List;

/**
 * Spring Data  repository for the SubArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubAreaRepository extends JpaRepository<SubArea, Long> {
    Page<SubArea> findAllByAreaId(@Param("areaId") Long areaId, Pageable pageable);
    List<SubArea> findAllByAreaId(@Param("areaId") Long areaId);
}
