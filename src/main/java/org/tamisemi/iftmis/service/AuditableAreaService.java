package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.AuditableArea;
import org.tamisemi.iftmis.repository.AuditableAreaRepository;
import org.tamisemi.iftmis.service.dto.AuditableAreaDTO;
import org.tamisemi.iftmis.service.mapper.AuditableAreaMapper;

/**
 * Service Implementation for managing {@link AuditableArea}.
 */
@Service
@Transactional
public class AuditableAreaService {
    private final Logger log = LoggerFactory.getLogger(AuditableAreaService.class);

    private final AuditableAreaRepository auditableAreaRepository;

    private final AuditableAreaMapper auditableAreaMapper;

    public AuditableAreaService(AuditableAreaRepository auditableAreaRepository, AuditableAreaMapper auditableAreaMapper) {
        this.auditableAreaRepository = auditableAreaRepository;
        this.auditableAreaMapper = auditableAreaMapper;
    }

    /**
     * Save a auditableArea.
     *
     * @param auditableAreaDTO the entity to save.
     * @return the persisted entity.
     */
    public AuditableAreaDTO save(AuditableAreaDTO auditableAreaDTO) {
        log.debug("Request to save AuditableArea : {}", auditableAreaDTO);
        AuditableArea auditableArea = auditableAreaMapper.toEntity(auditableAreaDTO);
        auditableArea = auditableAreaRepository.save(auditableArea);
        return auditableAreaMapper.toDto(auditableArea);
    }

    /**
     * Get all the auditableAreas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AuditableAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AuditableAreas");
        return auditableAreaRepository.findAll(pageable).map(auditableAreaMapper::toDto);
    }

    /**
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<AuditableArea> findAll() {
        log.debug("Request to get all AuditableAreas");
        return auditableAreaRepository.findAll();
    }

    /**
     * Get one auditableArea by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AuditableAreaDTO> findOne(Long id) {
        log.debug("Request to get AuditableArea : {}", id);
        return auditableAreaRepository.findById(id).map(auditableAreaMapper::toDto);
    }

    /**
     * Delete the auditableArea by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AuditableArea : {}", id);
        auditableAreaRepository.deleteById(id);
    }
}
