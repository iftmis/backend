package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.MeetingMember;
import org.tamisemi.iftmis.repository.MeetingMemberRepository;
import org.tamisemi.iftmis.service.dto.MeetingMemberDTO;
import org.tamisemi.iftmis.service.mapper.MeetingMemberMapper;

/**
 * Service Implementation for managing {@link MeetingMember}.
 */
@Service
@Transactional
public class MeetingMemberService {
    private final Logger log = LoggerFactory.getLogger(MeetingMemberService.class);

    private final MeetingMemberRepository meetingMemberRepository;

    private final MeetingMemberMapper meetingMemberMapper;

    public MeetingMemberService(MeetingMemberRepository meetingMemberRepository, MeetingMemberMapper meetingMemberMapper) {
        this.meetingMemberRepository = meetingMemberRepository;
        this.meetingMemberMapper = meetingMemberMapper;
    }

    /**
     * Save a meetingMember.
     *
     * @param meetingMemberDTO the entity to save.
     * @return the persisted entity.
     */
    public MeetingMemberDTO save(MeetingMemberDTO meetingMemberDTO) {
        log.debug("Request to save MeetingMember : {}", meetingMemberDTO);
        MeetingMember meetingMember = meetingMemberMapper.toEntity(meetingMemberDTO);
        meetingMember = meetingMemberRepository.save(meetingMember);
        return meetingMemberMapper.toDto(meetingMember);
    }

    /**
     * Get all the meetingMembers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MeetingMemberDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MeetingMembers");
        return meetingMemberRepository.findAll(pageable).map(meetingMemberMapper::toDto);
    }

    /**
     * Get one meetingMember by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MeetingMemberDTO> findOne(Long id) {
        log.debug("Request to get MeetingMember : {}", id);
        return meetingMemberRepository.findById(id).map(meetingMemberMapper::toDto);
    }

    /**
     * Delete the meetingMember by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MeetingMember : {}", id);
        meetingMemberRepository.deleteById(id);
    }
}
