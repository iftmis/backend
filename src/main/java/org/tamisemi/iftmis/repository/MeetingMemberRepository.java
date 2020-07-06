package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.MeetingMember;

/**
 * Spring Data  repository for the MeetingMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {}
