package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.FindingSubCategory;

/**
 * Spring Data  repository for the FindingSubCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FindingSubCategoryRepository extends JpaRepository<FindingSubCategory, Long> {}
