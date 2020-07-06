package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.MeetingAttachment;
import org.tamisemi.iftmis.repository.MeetingAttachmentRepository;
import org.tamisemi.iftmis.service.dto.MeetingAttachmentDTO;
import org.tamisemi.iftmis.service.mapper.MeetingAttachmentMapper;

/**
 * Service Implementation for managing {@link MeetingAttachment}.
 */
@Service
@Transactional
public class MeetingAttachmentService {
    private final Logger log = LoggerFactory.getLogger(MeetingAttachmentService.class);

    private final MeetingAttachmentRepository meetingAttachmentRepository;

    private final MeetingAttachmentMapper meetingAttachmentMapper;

    public MeetingAttachmentService(
        MeetingAttachmentRepository meetingAttachmentRepository,
        MeetingAttachmentMapper meetingAttachmentMapper
    ) {
        this.meetingAttachmentRepository = meetingAttachmentRepository;
        this.meetingAttachmentMapper = meetingAttachmentMapper;
    }

    /**
     * Save a meetingAttachment.
     *
     * @param meetingAttachmentDTO the entity to save.
     * @return the persisted entity.
     */
    public MeetingAttachmentDTO save(MeetingAttachmentDTO meetingAttachmentDTO) {
        log.debug("Request to save MeetingAttachment : {}", meetingAttachmentDTO);
        MeetingAttachment meetingAttachment = meetingAttachmentMapper.toEntity(meetingAttachmentDTO);
        meetingAttachment = meetingAttachmentRepository.save(meetingAttachment);
        return meetingAttachmentMapper.toDto(meetingAttachment);
    }

    /**
     * Get all the meetingAttachments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MeetingAttachmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MeetingAttachments");
        return meetingAttachmentRepository.findAll(pageable).map(meetingAttachmentMapper::toDto);
    }

    /**
     * Get one meetingAttachment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MeetingAttachmentDTO> findOne(Long id) {
        log.debug("Request to get MeetingAttachment : {}", id);
        return meetingAttachmentRepository.findById(id).map(meetingAttachmentMapper::toDto);
    }

    /**
     * Delete the meetingAttachment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MeetingAttachment : {}", id);
        meetingAttachmentRepository.deleteById(id);
    }
}
