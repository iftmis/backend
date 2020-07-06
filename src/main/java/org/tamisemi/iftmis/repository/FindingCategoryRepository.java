package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.FindingCategory;

/**
 * Spring Data  repository for the FindingCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FindingCategoryRepository extends JpaRepository<FindingCategory, Long> {}
