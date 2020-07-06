package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionObjective;
import org.tamisemi.iftmis.repository.InspectionObjectiveRepository;
import org.tamisemi.iftmis.service.dto.InspectionObjectiveDTO;
import org.tamisemi.iftmis.service.mapper.InspectionObjectiveMapper;

/**
 * Service Implementation for managing {@link InspectionObjective}.
 */
@Service
@Transactional
public class InspectionObjectiveService {
    private final Logger log = LoggerFactory.getLogger(InspectionObjectiveService.class);

    private final InspectionObjectiveRepository inspectionObjectiveRepository;

    private final InspectionObjectiveMapper inspectionObjectiveMapper;

    public InspectionObjectiveService(
        InspectionObjectiveRepository inspectionObjectiveRepository,
        InspectionObjectiveMapper inspectionObjectiveMapper
    ) {
        this.inspectionObjectiveRepository = inspectionObjectiveRepository;
        this.inspectionObjectiveMapper = inspectionObjectiveMapper;
    }

    /**
     * Save a inspectionObjective.
     *
     * @param inspectionObjectiveDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionObjectiveDTO save(InspectionObjectiveDTO inspectionObjectiveDTO) {
        log.debug("Request to save InspectionObjective : {}", inspectionObjectiveDTO);
        InspectionObjective inspectionObjective = inspectionObjectiveMapper.toEntity(inspectionObjectiveDTO);
        inspectionObjective = inspectionObjectiveRepository.save(inspectionObjective);
        return inspectionObjectiveMapper.toDto(inspectionObjective);
    }

    /**
     * Get all the inspectionObjectives.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionObjectiveDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionObjectives");
        return inspectionObjectiveRepository.findAll(pageable).map(inspectionObjectiveMapper::toDto);
    }

    /**
     * Get one inspectionObjective by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionObjectiveDTO> findOne(Long id) {
        log.debug("Request to get InspectionObjective : {}", id);
        return inspectionObjectiveRepository.findById(id).map(inspectionObjectiveMapper::toDto);
    }

    /**
     * Delete the inspectionObjective by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionObjective : {}", id);
        inspectionObjectiveRepository.deleteById(id);
    }
}
