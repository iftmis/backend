package org.tamisemi.iftmis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Finding;
import org.tamisemi.iftmis.domain.enumeration.FindingSource;

import java.util.List;


@Repository
public interface FindingRepository extends JpaRepository<Finding, Long> {
    List<Finding> findAllByOrganisationUnitIdAndSource(@Param("organisationUnit") Long organisationUnit, @Param("source") FindingSource source);

    Page<Finding> findAllByOrganisationUnitIdAndSource(@Param("organisationUnit") Long organisationUnit, @Param("source") FindingSource source, Pageable pageable);
}
