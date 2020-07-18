package org.tamisemi.iftmis.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionActivity;

/**
 * Spring Data  repository for the InspectionActivity entity.
 */
@Repository
public interface InspectionActivityRepository
    extends JpaRepository<InspectionActivity, Long>, JpaSpecificationExecutor<InspectionActivity> {
    @Query(
        value = "select distinct inspectionActivity from InspectionActivity inspectionActivity left join fetch inspectionActivity.risks left join fetch inspectionActivity.organisationUnits",
        countQuery = "select count(distinct inspectionActivity) from InspectionActivity inspectionActivity"
    )
    Page<InspectionActivity> findAllWithEagerRelationships(Pageable pageable);

    @Query(
        "select distinct inspectionActivity from InspectionActivity inspectionActivity left join fetch inspectionActivity.risks left join fetch inspectionActivity.organisationUnits"
    )
    List<InspectionActivity> findAllWithEagerRelationships();

    @Query(
        "select inspectionActivity from InspectionActivity inspectionActivity left join fetch inspectionActivity.risks left join fetch inspectionActivity.organisationUnits where inspectionActivity.id =:id"
    )
    Optional<InspectionActivity> findOneWithEagerRelationships(@Param("id") Long id);
}
