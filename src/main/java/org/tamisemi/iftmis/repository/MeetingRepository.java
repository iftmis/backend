package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Meeting;

/**
 * Spring Data  repository for the Meeting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {}
