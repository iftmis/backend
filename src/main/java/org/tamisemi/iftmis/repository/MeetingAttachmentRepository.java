package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.MeetingAttachment;

/**
 * Spring Data  repository for the MeetingAttachment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MeetingAttachmentRepository extends JpaRepository<MeetingAttachment, Long> {}
