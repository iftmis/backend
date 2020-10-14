package org.tamisemi.iftmis.service;

import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.Meeting;
import org.tamisemi.iftmis.domain.enumeration.InspectionType;
import org.tamisemi.iftmis.domain.enumeration.MeetingType;
import org.tamisemi.iftmis.repository.MeetingRepository;
import org.tamisemi.iftmis.service.dto.InspectionDTO;
import org.tamisemi.iftmis.service.dto.MeetingDTO;
import org.tamisemi.iftmis.service.mapper.MeetingMapper;

/**
 * Service Implementation for managing {@link Meeting}.
 */
@Service
@Transactional
public class MeetingService {
    private final Logger log = LoggerFactory.getLogger(MeetingService.class);

    private final MeetingRepository meetingRepository;

    private final MeetingMapper meetingMapper;

    public MeetingService(MeetingRepository meetingRepository, MeetingMapper meetingMapper) {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
    }

    /**
     * Save a meeting.
     *
     * @param meetingDTO the entity to save.
     * @return the persisted entity.
     */
    public MeetingDTO save(MeetingDTO meetingDTO) {
        log.debug("Request to save Meeting : {}", meetingDTO);
        Meeting meeting = meetingMapper.toEntity(meetingDTO);
        meeting = meetingRepository.save(meeting);
        return meetingMapper.toDto(meeting);
    }

    /**
     * Get all the meetings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MeetingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Meetings");
        return meetingRepository.findAll(pageable).map(meetingMapper::toDto);
    }

    /**
     * Get one meeting by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MeetingDTO> findOne(Long id) {
        log.debug("Request to get Meeting : {}", id);
        return meetingRepository.findById(id).map(meetingMapper::toDto);
    }

    /**
     * Delete the meeting by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Meeting : {}", id);
        meetingRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<MeetingDTO> findByInspection_IdAndType(Long inspection_id, @NotNull MeetingType type, Pageable page) {
        log.debug("Request to get all Inspections by Financial Year And Organisation Unit");
        return meetingRepository.findByInspection_IdAndType(inspection_id, type, page).map(meetingMapper::toDto);
    }
}
