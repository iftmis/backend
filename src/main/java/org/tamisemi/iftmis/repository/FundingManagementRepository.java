package org.tamisemi.iftmis.repository;

import org.tamisemi.iftmis.domain.FundingManagement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FundingManagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundingManagementRepository extends JpaRepository<FundingManagement, Long>, JpaSpecificationExecutor<FundingManagement> {
}
