package org.tamisemi.iftmis.repository;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Inspection;
import org.tamisemi.iftmis.domain.Meeting;
import org.tamisemi.iftmis.domain.enumeration.MeetingType;
import org.tamisemi.iftmis.service.dto.MeetingDTO;

/**
 * Spring Data  repository for the Meeting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    Page<Meeting> findByInspection_IdAndType(Long inspection_id, @NotNull MeetingType type, Pageable page);
}
