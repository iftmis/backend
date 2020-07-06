package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.SubArea;

/**
 * Spring Data  repository for the SubArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubAreaRepository extends JpaRepository<SubArea, Long> {}
