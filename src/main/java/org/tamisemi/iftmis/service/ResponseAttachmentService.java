package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.ResponseAttachment;
import org.tamisemi.iftmis.repository.ResponseAttachmentRepository;
import org.tamisemi.iftmis.service.dto.ResponseAttachmentDTO;
import org.tamisemi.iftmis.service.mapper.ResponseAttachmentMapper;

/**
 * Service Implementation for managing {@link ResponseAttachment}.
 */
@Service
@Transactional
public class ResponseAttachmentService {
    private final Logger log = LoggerFactory.getLogger(ResponseAttachmentService.class);

    private final ResponseAttachmentRepository responseAttachmentRepository;

    private final ResponseAttachmentMapper responseAttachmentMapper;

    public ResponseAttachmentService(
        ResponseAttachmentRepository responseAttachmentRepository,
        ResponseAttachmentMapper responseAttachmentMapper
    ) {
        this.responseAttachmentRepository = responseAttachmentRepository;
        this.responseAttachmentMapper = responseAttachmentMapper;
    }

    /**
     * Save a responseAttachment.
     *
     * @param responseAttachmentDTO the entity to save.
     * @return the persisted entity.
     */
    public ResponseAttachmentDTO save(ResponseAttachmentDTO responseAttachmentDTO) {
        log.debug("Request to save ResponseAttachment : {}", responseAttachmentDTO);
        ResponseAttachment responseAttachment = responseAttachmentMapper.toEntity(responseAttachmentDTO);
        responseAttachment = responseAttachmentRepository.save(responseAttachment);
        return responseAttachmentMapper.toDto(responseAttachment);
    }

    /**
     * Get all the responseAttachments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ResponseAttachmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResponseAttachments");
        return responseAttachmentRepository.findAll(pageable).map(responseAttachmentMapper::toDto);
    }

    /**
     * Get one responseAttachment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResponseAttachmentDTO> findOne(Long id) {
        log.debug("Request to get ResponseAttachment : {}", id);
        return responseAttachmentRepository.findById(id).map(responseAttachmentMapper::toDto);
    }

    /**
     * Delete the responseAttachment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResponseAttachment : {}", id);
        responseAttachmentRepository.deleteById(id);
    }
}
