package org.tamisemi.iftmis.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionMember;

/**
 * Spring Data  repository for the InspectionMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionMemberRepository extends JpaRepository<InspectionMember, Long> {
    @Query("select inspectionMember from InspectionMember inspectionMember where inspectionMember.user.login = ?#{principal.username}")
    List<InspectionMember> findByUserIsCurrentUser();

    Page<InspectionMember> findByInspection_Id(Long inspection_id, Pageable pageable);
}
