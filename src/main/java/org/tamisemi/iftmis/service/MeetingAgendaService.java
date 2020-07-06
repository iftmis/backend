package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.MeetingAgenda;
import org.tamisemi.iftmis.repository.MeetingAgendaRepository;
import org.tamisemi.iftmis.service.dto.MeetingAgendaDTO;
import org.tamisemi.iftmis.service.mapper.MeetingAgendaMapper;

/**
 * Service Implementation for managing {@link MeetingAgenda}.
 */
@Service
@Transactional
public class MeetingAgendaService {
    private final Logger log = LoggerFactory.getLogger(MeetingAgendaService.class);

    private final MeetingAgendaRepository meetingAgendaRepository;

    private final MeetingAgendaMapper meetingAgendaMapper;

    public MeetingAgendaService(MeetingAgendaRepository meetingAgendaRepository, MeetingAgendaMapper meetingAgendaMapper) {
        this.meetingAgendaRepository = meetingAgendaRepository;
        this.meetingAgendaMapper = meetingAgendaMapper;
    }

    /**
     * Save a meetingAgenda.
     *
     * @param meetingAgendaDTO the entity to save.
     * @return the persisted entity.
     */
    public MeetingAgendaDTO save(MeetingAgendaDTO meetingAgendaDTO) {
        log.debug("Request to save MeetingAgenda : {}", meetingAgendaDTO);
        MeetingAgenda meetingAgenda = meetingAgendaMapper.toEntity(meetingAgendaDTO);
        meetingAgenda = meetingAgendaRepository.save(meetingAgenda);
        return meetingAgendaMapper.toDto(meetingAgenda);
    }

    /**
     * Get all the meetingAgenda.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MeetingAgendaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MeetingAgenda");
        return meetingAgendaRepository.findAll(pageable).map(meetingAgendaMapper::toDto);
    }

    /**
     * Get one meetingAgenda by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MeetingAgendaDTO> findOne(Long id) {
        log.debug("Request to get MeetingAgenda : {}", id);
        return meetingAgendaRepository.findById(id).map(meetingAgendaMapper::toDto);
    }

    /**
     * Delete the meetingAgenda by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MeetingAgenda : {}", id);
        meetingAgendaRepository.deleteById(id);
    }
}
