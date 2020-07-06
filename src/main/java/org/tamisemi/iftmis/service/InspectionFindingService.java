package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionFinding;
import org.tamisemi.iftmis.repository.InspectionFindingRepository;
import org.tamisemi.iftmis.service.dto.InspectionFindingDTO;
import org.tamisemi.iftmis.service.mapper.InspectionFindingMapper;

/**
 * Service Implementation for managing {@link InspectionFinding}.
 */
@Service
@Transactional
public class InspectionFindingService {
    private final Logger log = LoggerFactory.getLogger(InspectionFindingService.class);

    private final InspectionFindingRepository inspectionFindingRepository;

    private final InspectionFindingMapper inspectionFindingMapper;

    public InspectionFindingService(
        InspectionFindingRepository inspectionFindingRepository,
        InspectionFindingMapper inspectionFindingMapper
    ) {
        this.inspectionFindingRepository = inspectionFindingRepository;
        this.inspectionFindingMapper = inspectionFindingMapper;
    }

    /**
     * Save a inspectionFinding.
     *
     * @param inspectionFindingDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionFindingDTO save(InspectionFindingDTO inspectionFindingDTO) {
        log.debug("Request to save InspectionFinding : {}", inspectionFindingDTO);
        InspectionFinding inspectionFinding = inspectionFindingMapper.toEntity(inspectionFindingDTO);
        inspectionFinding = inspectionFindingRepository.save(inspectionFinding);
        return inspectionFindingMapper.toDto(inspectionFinding);
    }

    /**
     * Get all the inspectionFindings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionFindingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionFindings");
        return inspectionFindingRepository.findAll(pageable).map(inspectionFindingMapper::toDto);
    }

    /**
     * Get one inspectionFinding by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionFindingDTO> findOne(Long id) {
        log.debug("Request to get InspectionFinding : {}", id);
        return inspectionFindingRepository.findById(id).map(inspectionFindingMapper::toDto);
    }

    /**
     * Delete the inspectionFinding by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionFinding : {}", id);
        inspectionFindingRepository.deleteById(id);
    }
}
