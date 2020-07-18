package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionActivity;
import org.tamisemi.iftmis.repository.InspectionActivityRepository;
import org.tamisemi.iftmis.service.dto.InspectionActivityDTO;
import org.tamisemi.iftmis.service.mapper.InspectionActivityMapper;

/**
 * Service Implementation for managing {@link InspectionActivity}.
 */
@Service
@Transactional
public class InspectionActivityService {
    private final Logger log = LoggerFactory.getLogger(InspectionActivityService.class);

    private final InspectionActivityRepository inspectionActivityRepository;

    private final InspectionActivityMapper inspectionActivityMapper;

    public InspectionActivityService(
        InspectionActivityRepository inspectionActivityRepository,
        InspectionActivityMapper inspectionActivityMapper
    ) {
        this.inspectionActivityRepository = inspectionActivityRepository;
        this.inspectionActivityMapper = inspectionActivityMapper;
    }

    /**
     * Save a inspectionActivity.
     *
     * @param inspectionActivityDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionActivityDTO save(InspectionActivityDTO inspectionActivityDTO) {
        log.debug("Request to save InspectionActivity : {}", inspectionActivityDTO);
        InspectionActivity inspectionActivity = inspectionActivityMapper.toEntity(inspectionActivityDTO);
        inspectionActivity = inspectionActivityRepository.save(inspectionActivity);
        return inspectionActivityMapper.toDto(inspectionActivity);
    }

    /**
     * Get all the inspectionActivities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionActivityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionActivities");
        return inspectionActivityRepository.findAll(pageable).map(inspectionActivityMapper::toDto);
    }

    /**
     * Get all the inspectionActivities with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<InspectionActivityDTO> findAllWithEagerRelationships(Pageable pageable) {
        return inspectionActivityRepository.findAllWithEagerRelationships(pageable).map(inspectionActivityMapper::toDto);
    }

    /**
     * Get one inspectionActivity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionActivityDTO> findOne(Long id) {
        log.debug("Request to get InspectionActivity : {}", id);
        return inspectionActivityRepository.findOneWithEagerRelationships(id).map(inspectionActivityMapper::toDto);
    }

    /**
     * Delete the inspectionActivity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionActivity : {}", id);
        inspectionActivityRepository.deleteById(id);
    }
}
